package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Controller;
import model.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private TextField fieldLogin;
    @FXML private PasswordField fieldSenha;
    @FXML private Label labelTitle;
    @FXML private Button buttonLogin;
    @FXML private Hyperlink linkCadastro;

    private Controller controller = new Controller();
    private ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializa o bundle com o idioma padrão
        this.bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
        atualizarTextos();
    }


    @FXML
    public void handleLogin() {
        String login = fieldLogin.getText();
        String senha = fieldSenha.getText();

        if (login.isEmpty() || senha.isEmpty()) {
            mostrarAlerta("Erro", "É necessário preencher todos os campos");
            return;
        }

        try {
            if (controller.loginAdmin(login, senha)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHome.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) fieldLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle(bundle.getString("admin_home_title"));
                stage.show();
            } else if (controller.loginUsuario(login, senha) != null){
                Usuario usuarioAtual = controller.loginUsuario(login, senha);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ListagemEventos.fxml"));
                loader.setResources(bundle);
                Parent root = loader.load();

                ListagemEventosController listagemEventosController = loader.getController();
                listagemEventosController.setDados(controller, usuarioAtual);

                Stage stage = (Stage) fieldLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle(bundle.getString("event_list_title"));
                stage.show();
            } else{
                mostrarAlerta("Erro", "Login ou senha incorretos");
            }
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro no login", "Credenciais inválidas" + e.getMessage());
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
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Altera o idioma da aplicação.
     */
    public void changeLanguage(Locale locale) {
        this.bundle = ResourceBundle.getBundle("messages", locale);
        atualizarTextos();
    }

    @FXML
    public void switchToEnglish() {
        changeLanguage(new Locale("en", "US"));
    }
    @FXML
    public void switchToPortuguese() {
        changeLanguage(new Locale("pt", "BR"));
    }

    private void atualizarTextos() {
        try {
            labelTitle.setText(bundle.getString("login_title"));
            fieldLogin.setPromptText(bundle.getString("login_prompt"));
            fieldSenha.setPromptText(bundle.getString("password_prompt"));
            buttonLogin.setText(bundle.getString("login_button"));
            linkCadastro.setText(bundle.getString("signup_link"));
        } catch (MissingResourceException e) {
            System.err.println("Erro ao carregar o texto: " + e.getMessage());
        }
    }
}
