/*******************************************************************************
 Sistema Operacional: Windows 10 - 64 Bits
 Linguagem: JAVA 21.0.4
 Autor: João Marcelo Nascimento Fernandes
 Componente Curricular: EXA 863 - MI Programção
 Concluido em: 28/10/2024
 Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************************************************************************/

package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A classe Controller gerencia as operações relacionadas a usuários, eventos e ingressos,
 * permitindo o cadastro de usuários, eventos, compra e cancelamento de ingressos.
 */
public class Controller {
    public List<Usuario> usuarios;
    private List<Evento> eventos;
    private List<Compra> compras;
    public DataStore dataStore;

    /**
     * Construtor da classe Controller. Inicializa as listas de usuários, eventos e compras,
     * carregando os dados do armazenamento persistente.
     */
    public Controller() {
        dataStore = new DataStore();
        usuarios = dataStore.carregarUsuarios();
        eventos = dataStore.carregarEventos();
        compras = dataStore.carregarCompras();
    }

    /**
     * Cadastra um novo usuário no sistema.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @param nome O nome do usuário.
     * @param cpf O CPF do usuário.
     * @param email O email do usuário.
     * @param admin Define se o usuário é administrador.
     * @return O usuário cadastrado.
     */
    public Usuario cadastrarUsuario(String login, String senha, String nome, String cpf, String email, Boolean admin) {
        Usuario usuario = new Usuario(login, senha, nome, cpf, email, admin);
        usuarios.add(usuario);
        dataStore.salvarUsuarios(usuarios);
        return usuario;
    }

    /**
     * Cadastra um novo evento, caso o usuário seja administrador.
     *
     * @param nome O nome do evento.
     * @param descricao A descrição do evento.
     * @param data A data do evento.
     * @return O evento cadastrado, se o usuário for administrador.
     * @throws SecurityException Se o usuário não for administrador.
     */
    public Evento cadastrarEvento(String nome, String descricao, Date data) {
            Evento evento = new Evento(nome, descricao, data);
            eventos.add(evento);
            dataStore.salvarEventos(eventos);
            return evento;
    }

    /**
     * Adiciona um assento a um evento específico.
     *
     * @param nomeEvento O nome do evento.
     * @param assento O assento a ser adicionado ao evento.
     */
    public void adicionarAssentoEvento(String nomeEvento, String[] assento) {
        for (Evento evento : eventos) {
            if (evento.getNome().equals(nomeEvento)) {
                evento.adicionarVariosAssentos(assento);
                dataStore.salvarEventos(eventos);
                return;
            }
        }
        throw new IllegalArgumentException("Evento " + nomeEvento + " não encontrado.");
    }

    /**
     * Realiza a compra de um ingresso para um evento e assento específicos.
     *
     * @param usuario O usuário que está comprando o ingresso.
     * @param nomeEvento O nome do evento.
     * @param assento O assento a ser comprado.
     * @return O ingresso comprado.
     * @throws IllegalArgumentException Se o evento ou assento não estiverem disponíveis.
     */
    public Ingresso comprarIngresso(Usuario usuario, String nomeEvento, String assento) {
        for (Evento evento : eventos) {
            if (evento.getNome().equals(nomeEvento) && evento.isAtivo()) {
                if (evento.getAssentosDisponiveis().contains(assento)) {
                    Ingresso ingresso = new Ingresso(evento, 1, assento);
                    usuario.getIngressos().add(ingresso);
                    dataStore.salvarUsuarios(usuarios);
                    Compra compra = new Compra(ingresso, usuario);
                    compras.add(compra);
                    compra.emailConfirmacao();
                    evento.removerAssento(assento);
                    dataStore.salvarCompras(compras);
                    dataStore.salvarEventos(eventos);
                    return ingresso;
                } else {
                    throw new IllegalArgumentException("Assento " + assento + " não disponível.");
                }
            }
        }
        throw new IllegalArgumentException("Evento " + nomeEvento + " não encontrado ou inativo.");
    }

    /**
     * Cancela a compra de um ingresso e adiciona o assento de volta ao evento.
     *
     * @param usuario O usuário que deseja cancelar o ingresso.
     * @param ingresso O ingresso a ser cancelado.
     * @return true se o ingresso foi cancelado com sucesso, false caso contrário.
     */
    public Boolean cancelarCompra(Usuario usuario, Ingresso ingresso) {
        if (!usuarios.contains(usuario)) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (!usuario.getIngressos().contains(ingresso)) {
            throw new IllegalArgumentException("Ingresso não encontrado para este usuário.");
        }

        boolean compraEncontrada = false;

        for (Compra compra : compras) {
            if (compra.getUsuario().equals(usuario) && compra.getIngresso().equals(ingresso)) {
                compraEncontrada = true;
                boolean cancelado = ingresso.cancelar();
                if (cancelado) {
                    usuario.getIngressos().remove(ingresso);
                    dataStore.salvarUsuarios(usuarios);
                    ingresso.getEvento().adicionarAssento(ingresso.getAssento());
                    compra.cancelarCompra();
                    dataStore.salvarCompras(compras);
                    dataStore.salvarEventos(eventos);
                    return true;
                } else {
                    return false;
                }
            }
        }

        if (!compraEncontrada) {
            throw new RuntimeException("Erro no cancelamento da compra: compra não encontrada para o usuário e ingresso especificados.");
        }

        return null;
    }

    /**
     * Lista todos os eventos disponíveis no sistema.
     *
     * @return Uma lista de eventos disponíveis.
     */
    public List<Evento> listarEventosDisponiveis() {
        return eventos;
    }

    /**
     * Lista todos os ingressos comprados por um usuário.
     *
     * @param usuario O usuário para o qual listar os ingressos.
     * @return Uma lista de ingressos comprados pelo usuário.
     */
    public List<Ingresso> listarIngressosComprados(Usuario usuario) {
        return usuario.getIngressos();
    }

    /**
     * Adiciona um feedback para um evento, caso o evento já tenha ocorrido.
     *
     * @param evento O evento para o qual adicionar o feedback.
     * @param usuario O usuário que fornece o feedback.
     * @param avaliacao A avaliação dada pelo usuário.
     * @param comentario O comentário do usuário.
     * @throws IllegalArgumentException Se o evento não for encontrado ou estiver inativo.
     * @throws RuntimeException Se o evento ainda estiver ativo.
     */
    public void adicionarFeedback(Evento evento, Usuario usuario, String avaliacao, String comentario) {
        boolean eventoEncontrado = false;

        for (Evento evento2 : eventos) {
            if (evento2.getNome().equals(evento.getNome())) {
                eventoEncontrado = true;
                    Feedback feedback = new Feedback(usuario, avaliacao, comentario);
                    evento.getFeedbacks().add(feedback);
                    dataStore.salvarEventos(eventos);
                break;
            }
        }
        if (!eventoEncontrado) {
            throw new IllegalArgumentException("Evento " + evento.getNome() + " não encontrado ou inativo.");
        }
    }

    /**
     * Adiciona um cartão de crédito como forma de pagamento para um usuário.
     *
     * @param usuario O usuário que deseja adicionar o cartão.
     * @param numeroCartao O número do cartão de crédito.
     * @param nome O nome no cartão de crédito.
     */
    public void adicionarCartao(Usuario usuario, String numeroCartao, String nome) {
        Cartao cartao = new Cartao(numeroCartao, nome);
        usuario.getCartoes().add(cartao);
        dataStore.salvarUsuarios(usuarios);
    }

    /**
     * Adiciona um boleto como forma de pagamento para um usuário.
     *
     * @param usuario O usuário que deseja adicionar o boleto.
     * @param codigoBoleto O código do boleto bancário.
     */
    public void adicionarBoleto(Usuario usuario, String codigoBoleto) {
        Boleto boleto = new Boleto(codigoBoleto);
        usuario.getBoletos().add(boleto);
        dataStore.salvarUsuarios(usuarios);
    }

    /**
     * Realiza o login de um usuário com base nas credenciais fornecidas.
     *
     * @param login O nome de usuário para autenticação.
     * @param senha A senha correspondente ao nome de usuário.
     * @return O objeto {@link Usuario} correspondente ao login e senha fornecidos,
     *         ou {@code null} se não encontrar um usuário com as credenciais informadas.
     */
    public Usuario loginUsuario(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        } return null;
    }

    /**
     * Realiza o login de um administrador com base nas credenciais fornecidas.
     *
     * @param login O nome de usuário para autenticação.
     * @param senha A senha correspondente ao nome de usuário.
     * @return {@code true} se o login for bem-sucedido e o usuário for um administrador,
     *         {@code false} caso contrário (se as credenciais forem inválidas ou o usuário não for administrador).
     */
    public Boolean loginAdmin(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha) && usuario.getAdmin()) {
                return true;
            }
        } return false;
    }


    /**
     * Atualiza as informações de um usuário.
     *
     * @param usuario O usuário a ser atualizado.
     */
    public void atualizarUsuario(Usuario usuario) {
        dataStore.salvarUsuarios(usuarios);
    }
}
