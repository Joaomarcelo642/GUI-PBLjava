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
import model.Usuario;

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
            Usuario usuarioAtual = controller.loginUsuario(login, senha);

            if (controller.loginAdmin(login, senha)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHome.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) fieldLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Admin - Cadastro de Eventos");
                stage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ListagemEventos.fxml"));
                Parent root = loader.load();

                ListagemEventosController listagemEventosController = loader.getController();
                listagemEventosController.setDados(controller, usuarioAtual);

                Stage stage = (Stage) fieldLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Listagem de Eventos");
                stage.show();
            }
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
