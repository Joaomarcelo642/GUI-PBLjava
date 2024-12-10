package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Ingresso;
import model.Usuario;

public class IngressosCompradosController {

    @FXML
    private ListView<String> ingressosListView;

    private Usuario usuario;

    public void setDados(Usuario usuario) {
        this.usuario = usuario;

        ingressosListView.getItems().setAll(usuario.getIngressos().stream()
                .map(this::formatarIngresso)
                .toList());
    }

    private String formatarIngresso(Ingresso ingresso) {
        return String.format("Evento: %s - Assento: %s",
                ingresso.getEvento().getNome(),
                ingresso.getAssento());
    }

}
