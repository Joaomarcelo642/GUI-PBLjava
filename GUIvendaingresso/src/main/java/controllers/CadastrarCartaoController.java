package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Cartao;
import model.Usuario;
import model.Controller;

public class CadastrarCartaoController {
    @FXML private TextField numeroCartaoField;
    @FXML private TextField nomeCartaoField;

    private Usuario usuario;
    private Controller controller;

    public void initialize(Usuario usuario, Controller controller) {
        this.usuario = usuario;
        this.controller = controller;
    }

    @FXML
    private void cadastrarCartao() {
        // Cria o novo cartão
        String numeroCartao = numeroCartaoField.getText();
        String nomeCartao = nomeCartaoField.getText();
        Cartao cartao = new Cartao(numeroCartao, nomeCartao);

        // Adiciona ao usuário e salva
        usuario.getFormasDePagamento().add(cartao);
        controller.dataStore.salvarUsuarios(controller.usuarios);
    }
}
