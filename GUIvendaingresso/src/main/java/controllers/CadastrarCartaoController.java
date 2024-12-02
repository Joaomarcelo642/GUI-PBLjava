package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Controller;
import model.Usuario;
import model.Cartao;

public class CadastrarCartaoController {
    @FXML
    private TextField nomeTitularField;

    @FXML
    private TextField numeroCartaoField;

    private Usuario usuario;
    private Controller controller;

    public void setDados(Controller controller, Usuario usuario) {
        this.controller = controller;
        this.usuario = usuario;
    }

    @FXML
    private void salvarCartao() {
        String nomeTitular = nomeTitularField.getText();
        String numeroCartao = numeroCartaoField.getText();

        if (!nomeTitular.isEmpty() && !numeroCartao.isEmpty()) {
            controller.adicionarCartao(usuario, numeroCartao, nomeTitular);
        }

        fecharJanela();
    }

    @FXML
    private void cancelar() {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) nomeTitularField.getScene().getWindow();
        stage.close();
    }
}
