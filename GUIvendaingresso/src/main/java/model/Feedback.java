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
 * A classe Feedback representa uma avaliação realizada por um usuário sobre um evento
 * ou serviço, contendo uma avaliação textual e um comentário adicional.
 */
public class Feedback {

    /**
     * O usuário que realizou o feedback.
     */
    private Usuario usuario;

    /**
     * A avaliação realizada pelo usuário (ex.: "Bom", "Ruim", "Excelente").
     */
    private String avaliacao;

    /**
     * Um comentário adicional fornecido pelo usuário.
     */
    private String comentario;

    /**
     * Construtor para a classe Feedback.
     *
     * @param usuario    O usuário que realizou o feedback.
     * @param avaliacao  A avaliação fornecida pelo usuário.
     * @param comentario Um comentário adicional do usuário.
     */
    public Feedback(Usuario usuario, String avaliacao, String comentario) {
        this.usuario = usuario;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
    }

    /**
     * Obtém o usuário que realizou o feedback.
     *
     * @return O usuário que realizou o feedback.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Define o usuário que realizou o feedback.
     *
     * @param usuario O usuário que realizou o feedback.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtém a avaliação fornecida pelo usuário.
     *
     * @return A avaliação fornecida pelo usuário.
     */
    public String getAvaliacao() {
        return avaliacao;
    }

    /**
     * Define a avaliação fornecida pelo usuário.
     *
     * @param avaliacao A nova avaliação fornecida pelo usuário.
     */
    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    /**
     * Obtém o comentário adicional fornecido pelo usuário.
     *
     * @return O comentário adicional fornecido pelo usuário.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Define o comentário adicional fornecido pelo usuário.
     *
     * @param comentario O novo comentário fornecido pelo usuário.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
