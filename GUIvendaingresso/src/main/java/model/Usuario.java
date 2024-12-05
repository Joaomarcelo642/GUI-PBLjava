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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A classe Usuario representa um usuário no sistema de venda de ingressos.
 * Um usuário pode ser um administrador ou um cliente normal, e possui
 * um conjunto de atributos como login, senha, nome, CPF e email, além de listas
 * de ingressos e formas de pagamento
 */
public class Usuario {
    // Atributos e lista de ingressos
    private String login;
    private String senha;
    private String nome;
    private String cpf;
    private String email;
    private Boolean admin;
    private List<Ingresso> ingressos = new ArrayList<>();
    private List<Cartao> cartoes = new ArrayList<>();
    private List<Boleto> boletos = new ArrayList<>();


    /**
     * Construtor da classe Usuario.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @param nome O nome do usuário.
     * @param cpf O CPF do usuário.
     * @param email O email do usuário.
     * @param admin Indica se o usuário é administrador (true) ou não (false).
     */
    public Usuario(String login, String senha, String nome, String cpf, String email, Boolean admin) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.admin = admin;
    }

    // Métodos GETTERs e SETTERs

    /**
     * Obtém o login do usuário.
     *
     * @return O login do usuário.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define o login do usuário.
     *
     * @param login O novo login do usuário.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return A senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha A nova senha do usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Obtém o nome do usuário.
     *
     * @return O nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do usuário.
     *
     * @param nome O novo nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o CPF do usuário.
     *
     * @return O CPF do usuário.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do usuário.
     *
     * @param cpf O novo CPF do usuário.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o email do usuário.
     *
     * @return O email do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do usuário.
     *
     * @param email O novo email do usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Verifica se o usuário é um administrador.
     *
     * @return true se o usuário for administrador, false caso contrário.
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * Define se o usuário é um administrador.
     *
     * @param admin Indica se o usuário será administrador.
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     * Obtém a lista de ingressos do usuário.
     *
     * @return A lista de ingressos do usuário.
     */
    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    /**
     * Define a lista de ingressos do usuário.
     *
     * @param ingressos A nova lista de ingressos.
     */
    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public List<Cartao> getCartoes() {return cartoes;}

    public void setCartoes(List<Cartao> cartoes) {this.cartoes = cartoes;}

    public List<Boleto> getBoletos() {return boletos;}

    public void setBoletos(List<Boleto> boletos) {this.boletos = boletos;}

    // Métodos para os testes

    /**
     * Verifica se o usuário é administrador.
     *
     * @return true se o usuário for administrador, false caso contrário.
     */
    public Boolean isAdmin() {
        return admin;
    }

    /**
     * Realiza o login do usuário.
     *
     * @param login O login fornecido.
     * @param senha A senha fornecida.
     * @return true se o login e senha forem válidos, false caso contrário.
     */
    public Boolean login(String login, String senha) {
        if (login.equals(this.login) && this.senha.equals(senha)) {
            return true;
        } else {
            return false;
        }
    }


    // Métodos para comparar objetos

    /**
     * Compara este usuário com outro objeto para verificar se são iguais.
     *
     * @param o O objeto a ser comparado.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(login, usuario.login) &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(cpf, usuario.cpf) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(admin, usuario.admin);
    }

    /**
     * Gera um código hash para o usuário.
     *
     * @return O código hash do usuário.
     */
    @Override
    public int hashCode() {
        return Objects.hash(login, nome, cpf, email, admin);
    }

    public Cartao getCartao() {
        for (Cartao cartao : cartoes) {
            if (cartao.getNome().equals(this.nome)) {
                return cartao;
            }
        }return null;
    }
}
