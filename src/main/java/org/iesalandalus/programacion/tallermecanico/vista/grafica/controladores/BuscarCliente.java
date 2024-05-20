package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

public class BuscarCliente extends Controlador {

    @FXML
    private TextField tfDni;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfTelefono;

    @FXML
    void cerrarBusqueda() {
        getEscenario().close();
    }

    @FXML
    void borrarCliente() {
        if (Dialogos.mostrarDialogoConfirmacion("Borrar cliente", "¿Quieres borrar este cliente?", getEscenario())) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BORRAR_CLIENTE);
            getEscenario().close();
        }
    }

    @FXML
    void modificarCliente() {
        ModificarCliente vistaModificarCliente = (ModificarCliente) Controladores.get("/fxml/modificarCliente.fxml", "Modificar un cliente", getEscenario());
        vistaModificarCliente.limpiarCampos();
        vistaModificarCliente.getTfDni().setText(tfDni.getText());
        vistaModificarCliente.getTfNombre().setText(tfNombre.getText());
        vistaModificarCliente.getTfTelefono().setText(tfTelefono.getText());
        vistaModificarCliente.getEscenario().showAndWait();
        if(!vistaModificarCliente.isCancelado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.MODIFICAR_CLIENTE);
            getEscenario().close();
        }
    }

    public void setCliente(Cliente cliente) {
        tfDni.setText(cliente.getDni());
        tfNombre.setText(cliente.getNombre());
        tfTelefono.setText(cliente.getTelefono());
    }
}
