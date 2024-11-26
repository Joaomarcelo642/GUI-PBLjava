package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Controller;

public class LoginController {
    @FXML private TextField fieldLogin;
    @FXML private PasswordField fieldSenha;

    private Controller controller = new Controller();

    @FXML
    public void handleLogin() {
        String login = fieldLogin.getText();
        String senha = fieldSenha.getText();

        if (login.isEmpty() || senha.isEmpty()) {
            showAlert("Erro", "Login e senha não podem estar vazios!", AlertType.ERROR);
            return;
        }

        try {
            controller.loginUsuario(login, senha);
            // Redirecionar para a próxima tela (ex.: listagem de eventos)
            showAlert("Sucesso", "Login realizado com sucesso!", AlertType.INFORMATION);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Login");
            alert.setHeaderText("Credenciais inválidas");
            alert.setContentText("Por favor, verifique seu login e senha.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleGoToCadastro() {
        Stage stage = (Stage) fieldLogin.getScene().getWindow();
        SceneManager.changeScene(stage, "Cadastro.fxml");
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
