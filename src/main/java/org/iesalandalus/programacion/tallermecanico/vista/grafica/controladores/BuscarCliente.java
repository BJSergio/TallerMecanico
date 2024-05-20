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

    private Cliente clienteEncontrado; // Atributo creado para quedarme con el cliente de la vista


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
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.MODIFICAR_CLIENTE);
        vistaModificarCliente.getEscenario().close();

    }

    public void setCliente(Cliente cliente) { // Asigna el atributo para el modificar
        this.clienteEncontrado = cliente;
    }

    public void asignarCampos() { // Este método asigna los campos a la ventana de buscar
        tfDni.setText(clienteEncontrado.getDni());
        tfNombre.setText(clienteEncontrado.getNombre());
        tfTelefono.setText(clienteEncontrado.getTelefono());
    }
}
