package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Controller;
import model.Evento;
import model.Feedback;
import model.Usuario;

public class EnviarFeedbackController {

    @FXML
    private ComboBox<String> avaliacaoComboBox;

    @FXML
    private TextArea comentarioTextArea;

    private Evento evento;

    private Usuario usuarioLogado;

    private Controller controller;

    private DetalhesEventoController detalhesEventoController;

    /**
     * Inicializa os dados da tela de feedback.
     * @param evento Evento para o qual o feedback será enviado.
     * @param usuarioLogado Usuário logado que está enviando o feedback.
     */
    public void setDados(Evento evento, Usuario usuarioLogado, Controller controller, DetalhesEventoController detalhesEventoController) {
        this.evento = evento;
        this.usuarioLogado = usuarioLogado;
        this.controller = controller;
        this.detalhesEventoController = detalhesEventoController;
    }

    /**
     * Envia o feedback para o evento.
     */
    @FXML
    private void enviarFeedback() {
        String avaliacao = avaliacaoComboBox.getValue();
        String comentario = comentarioTextArea.getText();

        if (avaliacao == null || avaliacao.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Avaliação não selecionada", "Por favor, selecione uma avaliação.");
            return;
        }

        if (comentario == null || comentario.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Comentário vazio", "Por favor, insira um comentário.");
            return;
        }

        controller.adicionarFeedback(evento, usuarioLogado, comentario, avaliacao);
        detalhesEventoController.atualizarFeedbacks();

        mostrarAlerta(Alert.AlertType.INFORMATION, "Feedback Enviado", "Seu feedback foi enviado com sucesso!");
        voltar();
    }

    /**
     * Volta para a tela anterior.
     */
    @FXML
    private void voltar() {
        avaliacaoComboBox.getScene().getWindow().hide();
    }

    /**
     * Exibe um alerta para o usuário.
     */
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
