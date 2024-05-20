package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controles;

public class LeerClienteDni extends Controlador {

    @FXML
    private TextField tfClienteDni;
    private boolean isLecturaCancelada;

    @FXML
    void aceptarLecturaDni() {
        isLecturaCancelada = false;
        getEscenario().close();
    }

    @FXML
    void cancelarLecturaDni() {
        isLecturaCancelada = true;
        getEscenario().close();
        Controles.limpiarCamposTexto(tfClienteDni);
    }

    public boolean isLecturaCancelada() {
        return isLecturaCancelada;
    }
    public void limpiarCampo() {
        isLecturaCancelada = true;
        tfClienteDni.clear();
    }

    public String getDniLeido() {
        return tfClienteDni.getText();
    }
}
