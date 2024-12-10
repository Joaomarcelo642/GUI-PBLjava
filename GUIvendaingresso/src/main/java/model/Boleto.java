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
 * A classe Boleto representa uma forma de pagamento através de boleto bancário.
 * Ela armazena o código do boleto utilizado para pagamento.
 */
public class Boleto extends Pagamento {

    /**
     * O código do boleto bancário.
     */
    private String codigoBoleto;

    /**
     * Construtor da classe Boleto.
     *
     * @param codigoBoleto O código do boleto bancário.
     */
    public Boleto(String codigoBoleto) {
        super("Boleto Bancário");
        this.codigoBoleto = codigoBoleto;
    }

    /**
     * Obtém o código do boleto bancário.
     *
     * @return O código do boleto.
     */
    public String getCodigoBoleto() {
        return codigoBoleto;
    }

    /**
     * Define o código do boleto bancário.
     *
     * @param codigoBoleto O novo código do boleto.
     */
    public void setCodigoBoleto(String codigoBoleto) {
        this.codigoBoleto = codigoBoleto;
    }

    @Override
    public String toString() {
        return codigoBoleto;
    }
}

