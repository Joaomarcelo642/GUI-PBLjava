package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import model.Controller;
import model.Usuario;

public class EditarUsuarioController {

    @FXML
    private TextField nomeField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField emailField;

    private Controller controller;
    private Usuario usuarioAtual;

    public void setDados(Controller controller, Usuario usuario) {
        this.controller = controller;
        this.usuarioAtual = usuario;

        nomeField.setText(usuario.getNome());
        senhaField.setText(usuario.getSenha());
        cpfField.setText(usuario.getCpf());
        emailField.setText(usuario.getEmail());
    }

    @FXML
    public void salvarEdicoes() {
        usuarioAtual.setNome(nomeField.getText());
        usuarioAtual.setSenha(senhaField.getText());
        usuarioAtual.setCpf(cpfField.getText());
        usuarioAtual.setEmail(emailField.getText());

        controller.atualizarUsuario(usuarioAtual);
    }
}
