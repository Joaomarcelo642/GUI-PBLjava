package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Usuario;
import model.Controller;

public class EditarUsuarioController {
    @FXML private TextField nomeField;
    @FXML private TextField cpfField;
    @FXML private TextField emailField;
    @FXML private TextField loginField;
    @FXML private TextField senhaField;

    private Usuario usuario;
    private Controller controller;

    public void initialize(Usuario usuario, Controller controller) {
        this.usuario = usuario;
        this.controller = controller;

        // Preenche os campos com os dados do usuário
        nomeField.setText(usuario.getNome());
        cpfField.setText(usuario.getCpf());
        emailField.setText(usuario.getEmail());
        loginField.setText(usuario.getLogin());
        senhaField.setText(usuario.getSenha());
    }

    @FXML
    private void salvarDados() {
        // Atualiza os dados do usuário
        usuario.setNome(nomeField.getText());
        usuario.setCpf(cpfField.getText());
        usuario.setEmail(emailField.getText());
        usuario.setLogin(loginField.getText());
        usuario.setSenha(senhaField.getText());

        // Salva no sistema
        controller.dataStore.salvarUsuarios(controller.usuarios);
    }
}
