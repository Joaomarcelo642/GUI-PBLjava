package controllers;

/*import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import java.util.List;
import java.util.Random;

public class CompraIngressoController {

    @FXML
    private TextField txtEvento;

    @FXML
    private RadioButton rbCartao;

    @FXML
    private RadioButton rbBoleto;

    @FXML
    private ComboBox<String> comboCartoes;

    @FXML
    private VBox boxCartao;

    @FXML
    private VBox boxBoleto;

    @FXML
    private TextField txtCodigoBoleto;

    private Ingresso ingresso;
    private Usuario usuario;
    private Stage stageAnterior;

    public void setDados(Ingresso ingresso, Usuario usuario, Stage stageAnterior) {
        this.ingresso = ingresso;
        this.usuario = usuario;
        this.stageAnterior = stageAnterior;

        // Inicializa os campos de texto com as informações do ingresso
        txtEvento.setText(ingresso.getEvento().getNome());

        // Preenche a ComboBox com os cartões do usuário
        List<Pagamento> formasDePagamento = usuario.getFormasDePagamento();
        for (Pagamento forma : formasDePagamento) {
            if (forma instanceof Cartao) {
                Cartao cartao = (Cartao) forma;
                comboCartoes.getItems().add(cartao.getNumero());
            }
        }
    }

    @FXML
    private void initialize() {
        // Alterna entre as opções de pagamento
        rbCartao.selectedProperty().addListener((obs, oldVal, newVal) -> {
            boxCartao.setVisible(newVal);
            boxCartao.setManaged(newVal);
            boxBoleto.setVisible(!newVal);
            boxBoleto.setManaged(!newVal);
        });
    }

    @FXML
    private void gerarBoleto() {
        // Gera um código aleatório para o boleto
        String codigoBoleto = "BOLETO-" + new Random().nextInt(999999);
        txtCodigoBoleto.setText(codigoBoleto);
    }

    @FXML
    private void confirmarCompra() {
        try {
            Compra compra;

            if (rbCartao.isSelected()) {
                // Compra com cartão
                String cartaoSelecionado = comboCartoes.getSelectionModel().getSelectedItem();
                if (cartaoSelecionado == null) {
                    exibirErro("Erro de Pagamento", "Por favor, selecione um cartão.");
                    return;
                }
                compra = new Compra(ingresso, usuario);
            } else {
                // Compra com boleto
                String codigoBoleto = txtCodigoBoleto.getText();
                if (codigoBoleto.isEmpty()) {
                    exibirErro("Erro de Pagamento", "Por favor, gere um boleto antes de confirmar a compra.");
                    return;
                }
                Boleto boleto = new Boleto(codigoBoleto);
                compra = new Compra(ingresso, usuario);
            }

            // Confirmação da compra
            compra.confirmacaoCompra();

            // Mensagem de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Compra Confirmada");
            alert.setHeaderText("Compra realizada com sucesso!");
            alert.setContentText("Detalhes da compra foram enviados por email.");
            alert.showAndWait();

            // Fecha a janela atual
            fecharJanela();
        } catch (Exception e) {
            exibirErro("Erro ao confirmar a compra", e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stageAtual = (Stage) txtEvento.getScene().getWindow();
        stageAtual.close();
        if (stageAnterior != null) {
            stageAnterior.show(); // Mostra a tela anterior, se existir
        }
    }

    private void exibirErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}*/

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
    private Button btnComprar;

    @FXML
    private Label statusLabel;

    private Controller controller;

    private Ingresso ingresso;
    private Usuario usuario;

    public void setDados(Ingresso ingresso, Usuario usuario, Controller controller) {
        this.ingresso = ingresso;
        this.usuario = usuario;
        this.controller = controller;

        eventoNomeText.setText("Evento: " + ingresso.getEvento().getNome());
        assentoText.setText("Assento: " + ingresso.getAssento());
        precoText.setText("Preço: R$ " + String.format("%.2f", ingresso.getPreco()));
    }

    @FXML
    private void realizarCompra() {
        String formaPagamento = formaPagamentoComboBox.getValue();

        if (formaPagamento == null) {
            statusLabel.setText("Por favor, selecione uma forma de pagamento.");
            return;
        }

        if ("Cartão de Crédito".equals(formaPagamento)) {
            if (usuario.getCartoes() != null) {
                controller.comprarIngresso(usuario, ingresso.getEvento().getNome(), ingresso.getAssento());
                statusLabel.setText("Compra realizada com sucesso! Confirmação enviada por e-mail.");
            } else {
                statusLabel.setText("Você não possui um cartão cadastrado.");
            }
        } else if ("Boleto".equals(formaPagamento)) {
            Compra compra = new Compra(ingresso, usuario);
            compra.confirmacaoCompra();
            statusLabel.setText("Compra realizada com sucesso! Boleto gerado.");
            gerarBoleto(compra);
        }
    }

    private void gerarBoleto(Compra compra) {
        String codigoBoleto = "BOLETO-" + new Random().nextInt(999999);
        controller.adicionarBoleto(usuario, codigoBoleto);
        System.out.println("Boleto gerado " +codigoBoleto);
        System.out.println("para o usuário " + usuario.getNome());
    }
}
