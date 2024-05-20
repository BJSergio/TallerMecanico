package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controles;

public class LeerClienteDni extends Controlador {

    @FXML
    private TextField tfClienteDni;

    private boolean isCancelado;

    @FXML
    void aceptarLecturaDni() {
        isCancelado = false;
        getEscenario().close();
    }

    @FXML
    void cancelarLecturaDni() {
        isCancelado = true;
        getEscenario().close();
        Controles.limpiarCamposTexto(tfClienteDni);
    }

    public boolean isCancelado() {
        return isCancelado;
    }

    public void limpiarCampo() {
        isCancelado = true;
        tfClienteDni.clear();
    }

    public String getDniLeido() {
        return tfClienteDni.getText();
    }
}
