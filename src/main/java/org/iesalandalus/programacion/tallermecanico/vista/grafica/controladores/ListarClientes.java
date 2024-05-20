package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

import java.util.List;

public class ListarClientes extends Controlador {

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> columnaDni = new TableColumn<>("dni");
    @FXML
    private TableColumn<Cliente, String> columnaNombre = new TableColumn<>("nombre");
    @FXML
    private TableColumn<Cliente, String> columnaTelefono = new TableColumn<>("telefono");


    public void setListaClientes(List<Cliente> listCliente) {
        tablaClientes.setItems(FXCollections.observableArrayList(listCliente));
    }

    @FXML
    void initialize() {
        columnaDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

}
