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
import java.util.Date;
import java.util.List;

/**
 * A classe Evento representa um evento com informações como nome, descrição,
 * data,uma lista de assentos disponíveis para venda e uma lista de feedbacks.
 */
public class Evento {
    // Atributos e lista de assentos disponíveis
    private String nome;
    private String descricao;
    private Date data;
    private List<String> assentosdisponiveis = new ArrayList<>();
    private List<Feedback> feedbacks = new ArrayList<>();

    /**
     * Construtor da classe Evento.
     *
     * @param nome O nome do evento.
     * @param descricao A descrição do evento.
     * @param data A data do evento.
     */
    public Evento(String nome, String descricao, Date data) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
    }

    // Métodos GETTERs e SETTERs

    /**
     * Obtém o nome do evento.
     *
     * @return O nome do evento.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do evento.
     *
     * @param nome O novo nome do evento.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do evento.
     *
     * @return A descrição do evento.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do evento.
     *
     * @param descricao A nova descrição do evento.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém a data do evento.
     *
     * @return A data do evento.
     */
    public Date getData() {
        return data;
    }

    /**
     * Define a data do evento.
     *
     * @param data A nova data do evento.
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Obtém a lista de assentos disponíveis para o evento.
     *
     * @return A lista de assentos disponíveis.
     */
    public List<String> getAssentosDisponiveis() {
        return assentosdisponiveis;
    }

    /**
     * Define a lista de assentos disponíveis para o evento.
     *
     * @param assentosdisponiveis A nova lista de assentos disponíveis.
     */
    public void setAssentosDisponiveis(List<String> assentosdisponiveis) {
        this.assentosdisponiveis = assentosdisponiveis;
    }

    /**
     * Obtém a lista de feedbacks do evento.
     *
     * @return A lista de feedbacks.
     */
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    /**
     * Define a lista de feedbacks do evento.
     *
     * @param feedbacks A nova lista de feedbacks.
     */
    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    // Métodos para os testes

    /**
     * Adiciona um assento à lista de assentos disponíveis do evento.
     *
     * @param assento O assento a ser adicionado.
     */
    public void adicionarAssento(String assento) {
        assentosdisponiveis.add(assento);
    }

    /**
     * Remove um assento da lista de assentos disponíveis do evento.
     *
     * @param assento O assento a ser removido.
     */
    public void removerAssento(String assento) {
        assentosdisponiveis.remove(assento);
    }

    /**
     * Verifica se o evento está ativo, ou seja, se a data do evento
     * ainda não passou ou é igual à data atual.
     *
     * @return true se o evento estiver ativo, false caso contrário.
     */
    public Boolean isAtivo() {
        return data.after(new Date()) || data.equals(new Date());
    }
}
