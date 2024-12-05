package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Evento;
import model.Controller;
import controllers.DetalhesEventoController;
import model.Usuario;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ListagemEventosController {

    @FXML
    private TextField nomeEventoField;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TableView<Evento> eventosTable;

    @FXML
    private TableColumn<Evento, String> colNome;

    @FXML
    private TableColumn<Evento, String> colData;

    @FXML
    private TableColumn<Evento, String> colDescricao;

    private Controller controller = new Controller();

    private Usuario usuarioAtual;

    private ObservableList<Evento> eventosList = FXCollections.observableArrayList();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome()));
        colData.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(dateFormat.format(cellData.getValue().getData())));
        colDescricao.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescricao()));
        carregarEventos();
    }

    public void setDados(Controller controller, Usuario usuarioAtual) {
        this.controller = controller;
        this.usuarioAtual = usuarioAtual;
        System.out.println("Usuário atual configurado na tela de eventos: " + (usuarioAtual != null ? usuarioAtual.getNome() : "null"));
        carregarEventos();
    }


    private void carregarEventos() {
        try {
            List<Evento> eventos = controller.listarEventosDisponiveis();
            eventosList.setAll(eventos);
            eventosTable.setItems(eventosList);
        } catch (Exception e) {
            mostrarAlerta("Erro ao carregar eventos", e.getMessage());
        }
    }

    @FXML
    private void handleFiltrar() {
        String nome = nomeEventoField.getText();
        LocalDate data = dataPicker.getValue();

        try {
            List<Evento> eventosFiltrados = filtrarEventos(nome, data);
            eventosList.setAll(eventosFiltrados);
        } catch (Exception e) {
            mostrarAlerta("Erro ao aplicar filtro", e.getMessage());
        }
    }

    private List<Evento> filtrarEventos(String nome, LocalDate data) {
        return controller.listarEventosDisponiveis().stream()
                .filter(evento -> nome == null || nome.isEmpty() || evento.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(evento -> data == null || !evento.getData().before(convertToDate(data)))
                .collect(Collectors.toList());
    }

    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @FXML
    private void handleVerDetalhes() {
        Evento eventoSelecionado = eventosTable.getSelectionModel().getSelectedItem();
        if (eventoSelecionado == null) {
            mostrarAlerta("Nenhum evento selecionado", "Por favor, selecione um evento na tabela para ver os detalhes.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetalhesEvento.fxml"));
            Parent detalhesRoot = loader.load();

            DetalhesEventoController controllerDetalhes = loader.getController();
            controllerDetalhes.setDados(eventoSelecionado, usuarioAtual, controller);

            Stage detalhesStage = new Stage();
            detalhesStage.setTitle("Detalhes do Evento");
            detalhesStage.setScene(new Scene(detalhesRoot));
            detalhesStage.show();
        } catch (Exception e) {
            mostrarAlerta("Erro ao abrir detalhes", "Não foi possível abrir a tela de detalhes: " + e.getMessage());
        }
    }

    @FXML
    public void handleVoltar() {
        Stage stage = (Stage) nomeEventoField.getScene().getWindow();
        SceneManager.changeScene(stage, "Login.fxml");
    }

    @FXML
    private void handleEditarUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditarUsuario.fxml"));
            Parent root = loader.load();

            EditarUsuarioController editarUsuarioController = loader.getController();
            editarUsuarioController.setDados(controller, usuarioAtual);

            Stage stage = new Stage();
            stage.setTitle("Editar Dados de Usuário");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            mostrarAlerta("Erro", "Não foi possível abrir a tela de edição de dados do usuário.");
        }
    }

    @FXML
    private void handleCadastrarCartao() {
        if (usuarioAtual == null) { // Adiciona validação
            mostrarAlerta("Erro", "Usuário atual não está configurado. Tente fazer login novamente.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastrarCartao.fxml"));
            Parent root = loader.load();

            CadastrarCartaoController cadastrarCartaoController = loader.getController();
            cadastrarCartaoController.setDados(controller, usuarioAtual);

            Stage stage = new Stage();
            stage.setTitle("Cadastrar Cartão de Crédito");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            mostrarAlerta("Erro", "Não foi possível abrir a tela de cadastro de cartão de crédito.");
        }
    }


    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
