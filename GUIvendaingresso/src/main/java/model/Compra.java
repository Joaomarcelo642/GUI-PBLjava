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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A classe Compra representa uma compra de ingresso feita por um usuário no sistema de venda de ingressos.
 * Ela armazena informações sobre o ingresso adquirido, o usuário que fez a compra e o estado de confirmação
 * da compra.
 */
public class Compra {

    /**
     * O ingresso adquirido na compra.
     */
    private Ingresso ingresso;

    /**
     * O usuário que realizou a compra.
     */
    private Usuario usuario;

    /**
     * Indica se a compra foi confirmada.
     */
    private Boolean confirmacao;

    /**
     * Construtor da classe Compra.
     *
     * @param ingresso O ingresso adquirido.
     * @param usuario  O usuário que realizou a compra.
     */
    public Compra(Ingresso ingresso, Usuario usuario) {
        this.ingresso = ingresso;
        this.usuario = usuario;
        this.confirmacao = true;
    }

    /**
     * Obtém o ingresso adquirido na compra.
     *
     * @return O ingresso adquirido.
     */
    public Ingresso getIngresso() {
        return ingresso;
    }

    /**
     * Define o ingresso adquirido na compra.
     *
     * @param ingresso O ingresso a ser definido.
     */
    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    /**
     * Obtém o usuário que realizou a compra.
     *
     * @return O usuário da compra.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Define o usuário que realizou a compra.
     *
     * @param usuario O usuário a ser definido.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtém o estado de confirmação da compra.
     *
     * @return true se a compra estiver confirmada, false caso contrário.
     */
    public Boolean getConfirmacao() {
        return confirmacao;
    }

    /**
     * Define o estado de confirmação da compra.
     *
     * @param confirmacao O novo estado de confirmação.
     */
    public void setConfirmacao(Boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    /**
     * Confirma a compra caso ainda não esteja confirmada, enviando um email de confirmação.
     */
    public void confirmacaoCompra() {
        if (!confirmacao) {
            this.confirmacao = true;
            emailConfirmacao();
        }
    }

    /**
     * Envia um email de confirmação para o usuário, gerando um arquivo de texto com os detalhes da compra.
     */
    public void emailConfirmacao() {
        String nomeArquivo = "confirmacao_compra_" + usuario.getLogin() + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println("Olá " + usuario.getNome() + ",");
            writer.println("Sua compra foi confirmada com sucesso!");
            writer.println("Detalhes da compra:");
            writer.println("Evento: " + ingresso.getEvento().getNome());
            writer.println("Assento: " + ingresso.getAssento());
            writer.println("Preço: R$" + ingresso.getPreco());
            writer.println("Data do evento: " + ingresso.getEvento().getData());
            writer.println("Obrigado por sua compra!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cancela a compra, definindo o estado de confirmação como falso.
     */
    public void cancelarCompra() {
        if (confirmacao) {
            this.confirmacao = false;
        }
    }
}
