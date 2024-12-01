package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Evento;
import model.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class AdminHomeController {

    @FXML
    private TextField fieldNome;

    @FXML
    private TextField fieldDescricao;

    @FXML
    private TextField fieldData;

    @FXML
    private TextField fieldAssentos;

    private Controller controller = new Controller();

    @FXML
    public void handleCadastrarEvento() {
        String nome = fieldNome.getText();
        String descricao = fieldDescricao.getText();
        String dataTexto = fieldData.getText();
        String assentosTexto = fieldAssentos.getText();

        if (nome.isEmpty() || descricao.isEmpty() || dataTexto.isEmpty() || assentosTexto.isEmpty()) {
            showAlert("Erro", "Todos os campos devem ser preenchidos.", Alert.AlertType.ERROR);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = sdf.parse(dataTexto);

            String[] assentosArray = assentosTexto.split(",");
            Evento evento = controller.cadastrarEvento(nome, descricao, data);
            controller.adicionarAssentoEvento(nome, assentosArray);
            //evento.setAssentosDisponiveis(Arrays.asList(assentosArray));

            showAlert("Sucesso", "Evento cadastrado com sucesso!", Alert.AlertType.INFORMATION);

            fieldNome.clear();
            fieldDescricao.clear();
            fieldData.clear();
            fieldAssentos.clear();

        } catch (ParseException e) {
            showAlert("Erro", "Formato de data inválido. Use dd/MM/yyyy.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Erro", "Erro ao cadastrar evento: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleVoltar() {
        Stage stage = (Stage) fieldNome.getScene().getWindow();
        SceneManager.changeScene(stage, "Login.fxml");
    }

    private void showAlert(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
