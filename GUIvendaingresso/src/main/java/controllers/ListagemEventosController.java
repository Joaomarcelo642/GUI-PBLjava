package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Evento;
import model.Controller;

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
    private DatePicker dataInicialPicker;

    @FXML
    private DatePicker dataFinalPicker;

    @FXML
    private TableView<Evento> eventosTable;

    @FXML
    private TableColumn<Evento, String> colNome;

    @FXML
    private TableColumn<Evento, String> colData;

    @FXML
    private TableColumn<Evento, String> colDescricao;

    private Controller controller = new Controller();

    private ObservableList<Evento> eventosList = FXCollections.observableArrayList();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    public void initialize() {
        // Configurar colunas
        colNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome()));
        colData.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(dateFormat.format(cellData.getValue().getData())));
        colDescricao.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescricao()));

        // Carregar eventos
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
        LocalDate dataInicial = dataInicialPicker.getValue();
        LocalDate dataFinal = dataFinalPicker.getValue();

        try {
            List<Evento> eventosFiltrados = filtrarEventos(nome, dataInicial, dataFinal);
            eventosList.setAll(eventosFiltrados);
        } catch (Exception e) {
            mostrarAlerta("Erro ao aplicar filtro", e.getMessage());
        }
    }

    private List<Evento> filtrarEventos(String nome, LocalDate dataInicial, LocalDate dataFinal) {
        return controller.listarEventosDisponiveis().stream()
                .filter(evento -> nome == null || nome.isEmpty() || evento.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(evento -> dataInicial == null || !evento.getData().before(convertToDate(dataInicial)))
                .filter(evento -> dataFinal == null || !evento.getData().after(convertToDate(dataFinal)))
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

        // Abrir tela de detalhes (implementar em uma próxima etapa)
        System.out.println("Evento selecionado: " + eventoSelecionado.getNome());
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
