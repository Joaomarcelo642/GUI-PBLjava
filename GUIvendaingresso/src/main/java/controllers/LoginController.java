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

import java.io.IOException;

public class LoginController {
    @FXML private TextField fieldLogin;
    @FXML private PasswordField fieldSenha;

    private Controller controller = new Controller();

    @FXML
    public void handleLogin() {
        String login = fieldLogin.getText();
        String senha = fieldSenha.getText();

        if (login.isEmpty() || senha.isEmpty()) {
            exibirErro("Erro", "Login e senha não podem estar vazios!");
            return;
        }

        try {
            if (controller.loginAdmin(login, senha)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHome.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) fieldLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Admin - Cadastro de Eventos");
                stage.show();
            } else if (controller.loginUsuario(login, senha) != null){
                Usuario usuarioAtual = controller.loginUsuario(login, senha);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ListagemEventos.fxml"));
                Parent root = loader.load();

                ListagemEventosController listagemEventosController = loader.getController();
                listagemEventosController.setDados(controller, usuarioAtual);

                Stage stage = (Stage) fieldLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Listagem de Eventos");
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            exibirErro("Erro de login", "redenciais inválidas" + e.getMessage());
        }
    }

    @FXML
    public void handleGoToCadastro() {
        Stage stage = (Stage) fieldLogin.getScene().getWindow();
        SceneManager.changeScene(stage, "Cadastro.fxml");
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
