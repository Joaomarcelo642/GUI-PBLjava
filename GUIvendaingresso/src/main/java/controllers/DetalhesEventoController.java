package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Evento;
import model.Feedback;

import java.text.SimpleDateFormat;

public class DetalhesEventoController {

    @FXML
    private Label nomeLabel;

    @FXML
    private TextArea descricaoTextArea;

    @FXML
    private Label dataLabel;

    @FXML
    private ListView<String> assentosListView;

    @FXML
    private ListView<String> feedbacksListView;

    private Evento evento;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Inicializa os detalhes do evento selecionado.
     * Este método será chamado ao carregar a tela de detalhes.
     */
    public void setEvento(Evento evento) {
        this.evento = evento;

        // Preencher os campos com as informações do evento
        nomeLabel.setText(evento.getNome());
        descricaoTextArea.setText(evento.getDescricao());
        dataLabel.setText(dateFormat.format(evento.getData()));

        // Assentos disponíveis
        assentosListView.getItems().setAll(evento.getAssentosDisponiveis());

        // Feedbacks (formatados como texto)
        feedbacksListView.getItems().setAll(evento.getFeedbacks().stream()
                .map(feedback -> formatarFeedback(feedback))
                .toList());
    }

    /**
     * Formata o feedback para exibição no ListView.
     */
    private String formatarFeedback(Feedback feedback) {
        return String.format("Usuário: %s - Nota: %d - Comentário: %s",
                feedback.getUsuario().getNome(),
                feedback.getAvaliacao(),
                feedback.getComentario());
    }

    @FXML
    private void handleVoltar() {
        // Fechar a tela atual
        nomeLabel.getScene().getWindow().hide();
    }
}
