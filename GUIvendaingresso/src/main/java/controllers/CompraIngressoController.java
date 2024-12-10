package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.*;

import java.util.Optional;
import java.util.Random;

public class CompraIngressoController {

    @FXML
    private Text eventoNomeText;

    @FXML
    private Text assentoText;

    @FXML
    private Text precoText;

    @FXML
    private ComboBox<String> formaPagamentoComboBox;

    @FXML
    private ComboBox<Cartao> cartaoComboBox; // Novo ComboBox para cartões

    @FXML
    private Button btnComprar;

    @FXML
    private Label statusLabel;

    private Controller controller;

    private Ingresso ingresso;
    private Usuario usuario;
    private DetalhesEventoController detalhesEventoController;

    public void setDados(Ingresso ingresso, Usuario usuario, Controller controller, DetalhesEventoController detalhesEventoController) {
        this.ingresso = ingresso;
        this.usuario = usuario;
        this.controller = controller;
        this.detalhesEventoController = detalhesEventoController;

        eventoNomeText.setText("Evento: " + ingresso.getEvento().getNome());
        assentoText.setText("Assento: " + ingresso.getAssento());
        precoText.setText("Preço: R$ " + String.format("%.2f", ingresso.getPreco()));

        cartaoComboBox.getItems().setAll(usuario.getCartoes());
    }

    @FXML
    private void atualizarFormaPagamento() {
        String formaPagamento = formaPagamentoComboBox.getValue();

        if ("Cartão de Crédito".equals(formaPagamento)) {
            cartaoComboBox.setVisible(true);
        } else {
            cartaoComboBox.setVisible(false);
        }
    }

    @FXML
    private void realizarCompra() {
        String formaPagamento = formaPagamentoComboBox.getValue();

        if (formaPagamento == null) {
            statusLabel.setText("Por favor, selecione uma forma de pagamento.");
            return;
        }

        if ("Cartão de Crédito".equals(formaPagamento)) {
            Cartao cartaoSelecionado = cartaoComboBox.getValue();

            if (cartaoSelecionado == null) {
                statusLabel.setText("Por favor, selecione um cartão.");
                return;
            }

            controller.comprarIngresso(usuario, ingresso.getEvento().getNome(), ingresso.getAssento());
            detalhesEventoController.atualizarAssentos();
            statusLabel.setText("Compra realizada com sucesso no cartão " + cartaoSelecionado.getNumero() + "!");
        } else if ("Boleto".equals(formaPagamento)) {
            controller.comprarIngresso(usuario, ingresso.getEvento().getNome(), ingresso.getAssento());
            gerarBoleto();
            detalhesEventoController.atualizarAssentos();
            statusLabel.setText("Compra realizada com sucesso! Boleto gerado." + usuario.getUltimoBoleto());
        }
    }

    private void gerarBoleto() {
        String codigoBoleto = "BOLETO-" + new Random().nextInt(999999);
        controller.adicionarBoleto(usuario, codigoBoleto);
    }
}
