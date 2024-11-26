package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Controller;

public class CadastroController {
    @FXML
    private TextField fieldLogin;
    @FXML
    private PasswordField fieldSenha;
    @FXML
    private TextField fieldNome;
    @FXML
    private TextField fieldCpf;
    @FXML
    private TextField fieldEmail;
    @FXML
    private CheckBox checkBoxAdmin;

    private Controller controller = new Controller();

    @FXML
    public void handleCadastro() {
        String login = fieldLogin.getText();
        String senha = fieldSenha.getText();
        String nome = fieldNome.getText();
        String cpf = fieldCpf.getText();
        String email = fieldEmail.getText();
        boolean isAdmin = checkBoxAdmin.isSelected();

        if (login.isEmpty() || senha.isEmpty() || nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
            showAlert("Erro", "Todos os campos são obrigatórios!", AlertType.ERROR);
            return;
        }

        try {
            controller.cadastrarUsuario(login, senha, nome, cpf, email, isAdmin);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro Realizado");
            alert.setHeaderText(null);
            alert.setContentText("Usuário cadastrado com sucesso!");
            alert.showAndWait();

            // Voltar para tela de login
            Stage stage = (Stage) fieldLogin.getScene().getWindow();
            SceneManager.changeScene(stage, "Login.fxml");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Cadastro");
            alert.setHeaderText("Não foi possível realizar o cadastro");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        //showAlert("Sucesso", "Usuário cadastrado com sucesso!", AlertType.INFORMATION);
    }


    @FXML
    public void handleGoToLogin() {
        Stage stage = (Stage) fieldLogin.getScene().getWindow();
        SceneManager.changeScene(stage, "Login.fxml");
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


