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

import java.io.Serializable;
import java.util.Objects;

/**
 * A classe Ingresso representa um ingresso para um evento, contendo informações
 * como evento associado, preço, assento e estado (ativo ou inativo).
 */
public class Ingresso implements Serializable {
    // Atributos
    private Evento evento;
    private double preco;
    private String assento;
    private Boolean isAtivo;

    /**
     * Construtor da classe Ingresso.
     *
     * @param evento O evento associado ao ingresso.
     * @param preco O preço do ingresso.
     * @param assento O assento associado ao ingresso.
     */
    public Ingresso(Evento evento, double preco, String assento) {
        this.evento = evento;
        this.preco = preco;
        this.assento = assento;
        this.isAtivo = true;
    }

    // Métodos GETTERs e SETTERs

    /**
     * Obtém o evento associado ao ingresso.
     *
     * @return O evento do ingresso.
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Define o evento associado ao ingresso.
     *
     * @param evento O novo evento do ingresso.
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * Obtém o preço do ingresso.
     *
     * @return O preço do ingresso.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do ingresso.
     *
     * @param preco O novo preço do ingresso.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Obtém o assento associado ao ingresso.
     *
     * @return O assento do ingresso.
     */
    public String getAssento() {
        return assento;
    }

    /**
     * Define o assento associado ao ingresso.
     *
     * @param assento O novo assento do ingresso.
     */
    public void setAssento(String assento) {
        this.assento = assento;
    }

    /**
     * Verifica se o ingresso está ativo.
     *
     * @return true se o ingresso estiver ativo, false caso contrário.
     */
    public Boolean getIsAtivo() {
        return isAtivo;
    }

    /**
     * Define se o ingresso está ativo.
     *
     * @param isAtivo O novo estado do ingresso.
     */
    public void setIsAtivo(Boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    // Métodos para os testes

    /**
     * Verifica se o ingresso está ativo.
     *
     * @return true se o ingresso estiver ativo, false caso contrário.
     */
    public Boolean isAtivo() {
        return isAtivo;
    }

    /**
     * Cancela o ingresso, desativando-o, se o evento estiver ativo.
     *
     * @return true se o ingresso foi cancelado com sucesso, false caso contrário.
     */
    public Boolean cancelar() {
        if (isAtivo && evento.isAtivo()) {
            isAtivo = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reativa o ingresso, ativando-o novamente, se o evento estiver ativo.
     *
     * @return true se o ingresso foi reativado com sucesso, false caso contrário.
     */
    public Boolean reativar() {
        if (!isAtivo && evento.isAtivo()) {
            isAtivo = true;
            return true;
        } else {
            throw new IllegalStateException("O ingresso não pode ser reativado.");
        }
    }

    // Metodo para comparar objetos

    /**
     * Compara este ingresso com outro objeto para verificar se são iguais.
     *
     * @param o O objeto a ser comparado.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return Double.compare(preco, ingresso.preco) == 0 &&
                Objects.equals(evento, ingresso.evento) &&
                Objects.equals(assento, ingresso.assento);
    }

    /**
     * Gera um código hash para o ingresso.
     *
     * @return O código hash do ingresso.
     */
    @Override
    public int hashCode() {
        return Objects.hash(evento, preco, assento);
    }

    @Override
    public String toString() {
        return "Ingresso{" +
                "evento=" + evento.getNome() +
                ", preço=" + preco +
                ", assento='" + assento +
                ", Status=" + isAtivo + '\'' +
                '}';
    }
}
