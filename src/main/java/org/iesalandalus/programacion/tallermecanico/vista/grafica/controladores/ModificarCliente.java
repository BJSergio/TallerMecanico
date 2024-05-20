package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controles;

public class ModificarCliente extends Controlador {

    @FXML
    private TextField tfDni;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfTelefono;

    private boolean isCancelado;

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
        Controles.limpiarCamposTexto(tfDni,tfNombre,tfTelefono);
    }

    public boolean isCancelado() {
        return isCancelado;
    }

    public String getNuevoNombre() {
        return tfNombre.getText();
    }

    public String getNuevoTelefono() {
        return tfTelefono.getText();
    }

    public TextField getTfDni() {
        return tfDni;
    }

    public TextField getTfNombre() {
        return tfNombre;
    }

    public TextField getTfTelefono() {
        return tfTelefono;
    }
}
