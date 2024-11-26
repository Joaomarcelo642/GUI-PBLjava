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

/**
 * A classe abstrata Pagamento representa um método de pagamento genérico no sistema de venda de ingressos.
 * Cada pagamento possui uma forma de pagamento específica que é representada pela variável {@code forma}.
 * Classes que estendem Pagamento podem representar tipos específicos de pagamento, como Boleto e Cartão.
 */
public abstract class Pagamento {

    /**
     * A forma de pagamento, que indica o tipo de método de pagamento utilizado.
     */
    public String forma;

    /**
     * Construtor para a classe Pagamento.
     *
     * @param forma A forma de pagamento (ex.: "Boleto", "Cartão").
     */
    public Pagamento(String forma) {
        this.forma = forma;
    }

    /**
     * Obtém a forma de pagamento.
     *
     * @return A forma de pagamento.
     */
    public String getForma() {
        return forma;
    }

    /**
     * Define a forma de pagamento.
     *
     * @param forma A nova forma de pagamento.
     */
    public void setForma(String forma) {
        this.forma = forma;
    }
}



