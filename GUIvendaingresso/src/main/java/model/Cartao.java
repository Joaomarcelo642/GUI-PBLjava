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
 * A classe Cartao representa uma forma de pagamento através de cartão de crédito.
 * Ela armazena informações sobre o número do cartão e o nome do titular.
 */
public class Cartao extends Pagamento {

    /**
     * O número do cartão de crédito.
     */
    private String numeroCartao;

    /**
     * O nome do titular do cartão.
     */
    private String nome;

    /**
     * Construtor da classe Cartao.
     *
     * @param numeroCartao O número do cartão de crédito.
     * @param nome         O nome do titular do cartão.
     */
    public Cartao(String numeroCartao, String nome) {
        super("Cartão de Crédito");
        this.numeroCartao = numeroCartao;
        this.nome = nome;
    }

    /**
     * Obtém o número do cartão de crédito.
     *
     * @return O número do cartão.
     */
    public String getNumero() {
        return numeroCartao;
    }

    /**
     * Define o número do cartão de crédito.
     *
     * @param numeroCartao O novo número do cartão.
     */
    public void setNumero(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    /**
     * Obtém o nome do titular do cartão.
     *
     * @return O nome do titular do cartão.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do titular do cartão.
     *
     * @param nome O novo nome do titular do cartão.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return numeroCartao;  // Exibe apenas o número do cartão
    }
}

