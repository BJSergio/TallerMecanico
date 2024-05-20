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

    // Atributo creado para asignar el cliente que me pasan de la vista y ponerlo en el modificar
    private Cliente clienteEncontrado;


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
        ModificarCliente modificarCliente = (ModificarCliente) Controladores.get("/fxml/modificarCliente.fxml", "Modificar un cliente", getEscenario());
        modificarCliente.limpiarCampos();
        modificarCliente.setTfDni(clienteEncontrado.getDni());
        modificarCliente.setTfNombre(clienteEncontrado.getNombre());
        modificarCliente.setTfTelefono(clienteEncontrado.getTelefono());
        modificarCliente.getEscenario().showAndWait();
        if(!modificarCliente.isCancelado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.MODIFICAR_CLIENTE);
            getEscenario().close();
        }
    }

    public void setCliente(Cliente cliente) { // Asigna el atributo para el modificar
        this.clienteEncontrado = cliente;
    }

    public void asignarCampos(Cliente cliente) { // Este método asigna los campos a la ventana de buscar
        tfDni.setText(cliente.getDni());
        tfNombre.setText(cliente.getNombre());
        tfTelefono.setText(cliente.getTelefono());
    }
}
