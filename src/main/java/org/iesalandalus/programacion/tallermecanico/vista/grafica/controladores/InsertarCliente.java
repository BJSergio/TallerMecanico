package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controles;

public class InsertarCliente extends Controlador {

    @FXML
    private TextField tfDni;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfTelefono;

    private boolean esCancelado;

    public Cliente getCliente() {
        String nombre = tfNombre.getText();
        String dni = tfDni.getText();
        String telefono = tfTelefono.getText();
        return new Cliente(nombre, dni, telefono);
    }

    public boolean isCancelado() {
        return esCancelado;
    }

    public void limpiarCampos() {
        esCancelado = true;
        Controles.limpiarCamposTexto(tfDni,tfNombre,tfTelefono);
    }

    @FXML
    void aceptar() {
        esCancelado = false;
        getEscenario().close();
    }

    @FXML
    void cancelar() {
        esCancelado = true;
        getEscenario().close();
    }
}

