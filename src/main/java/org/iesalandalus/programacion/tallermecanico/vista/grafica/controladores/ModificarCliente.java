package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

public class ModificarCliente extends Controlador {

    @FXML
    private TextField tfDni;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfTelefono;

    private boolean isCancelado;

    public String getNuevoNombre() {
        return tfNombre.getText();
    }

    public String getNuevoTelefono() {
        return tfTelefono.getText();
    }

    public void setTfDni(String dni) {
        tfDni.setText(dni);
    }

    public void setTfNombre(String nombre) {
        tfNombre.setText(nombre);
    }

    public void setTfTelefono(String telefono) {
        tfTelefono.setText(telefono);
    }

    public boolean isCancelado() {
        return isCancelado;
    }

    @FXML
    void aceptarModificar() {
        isCancelado = false;
        getEscenario().close();
    }

    @FXML
    void cancelarModificar() {
        isCancelado = true;
        getEscenario().close();
    }

    public void limpiarCampos() {
        isCancelado = true;
        tfNombre.clear();
        tfTelefono.clear();
    }


}
