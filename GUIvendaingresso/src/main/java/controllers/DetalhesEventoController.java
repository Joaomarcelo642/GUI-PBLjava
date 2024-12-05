package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Evento;
import model.Ingresso;
import model.Usuario;
import model.*;

import java.io.IOException;
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

    private Usuario usuarioLogado;

    private Controller controller;

    private Ingresso ingressoSelecionado; // Variável para armazenar o ingresso selecionado

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Inicializa os detalhes do evento selecionado.
     * Este método será chamado ao carregar a tela de detalhes.
     */
    public void setDados(Evento evento, Usuario usuarioLogado, Controller controller) {
        this.evento = evento;
        this.usuarioLogado = usuarioLogado;
        this.controller = controller;

        nomeLabel.setText(evento.getNome());
        descricaoTextArea.setText(evento.getDescricao());
        dataLabel.setText(dateFormat.format(evento.getData()));

        assentosListView.getItems().setAll(evento.getAssentosDisponiveis());

        assentosListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String assentoSelecionado = newValue;
            ingressoSelecionado = new Ingresso(evento, 20, assentoSelecionado);
        });

        // Feedbacks (formatados como texto)
        feedbacksListView.getItems().setAll(evento.getFeedbacks().stream()
                .map(this::formatarFeedback)
                .toList());
    }

    /**
     * Formata o feedback para exibição no ListView.
     */
    private String formatarFeedback(Feedback feedback) {
        return String.format("Usuário: %s - Avaliação: %s - Comentário: %s",
                feedback.getUsuario().getNome(),
                feedback.getAvaliacao(),
                feedback.getComentario());
    }

    /**
     * Abre a tela de compra de ingresso.
     */
    @FXML
    private void abrirTelaCompra() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CompraIngresso.fxml"));
            Parent root = loader.load();

            CompraIngressoController compraController = loader.getController();
            compraController.setDados(ingressoSelecionado, usuarioLogado, controller);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            exibirErro("Erro ao abrir tela de compra", "Não foi possível abrir a tela de compra de ingresso. " + e.getMessage());
        }
    }

    @FXML
    private void abrirTelaFeedback() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EnviarFeedback.fxml"));
            Parent root = loader.load();

            EnviarFeedbackController feedbackController = loader.getController();
            feedbackController.setDados(evento, usuarioLogado, controller);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            exibirErro("Erro ao abrir tela de feedback", "Não foi possível abrir a tela de feedback. " + e.getMessage());
        }
    }

    /**
     * Fecha a tela atual.
     */
    @FXML
    private void handleVoltar() {
        nomeLabel.getScene().getWindow().hide();
    }

    /**
     * Exibe uma mensagem de erro em um diálogo.
     */
    private void exibirErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}