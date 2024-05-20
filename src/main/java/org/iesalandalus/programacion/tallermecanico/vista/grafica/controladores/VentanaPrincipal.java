package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

public class VentanaPrincipal extends Controlador {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private HBox hboxCliente;
    @FXML
    private HBox hboxTrabajo;

    @FXML
    private HBox hboxVehiculo;

    @FXML
    void buscarCliente() {
        LeerClienteDni vistaClienteDni = (LeerClienteDni) Controladores.get("/fxml/leerClienteDni.fxml","Leer dni",getEscenario());
        BuscarCliente vistaBuscarCliente = (BuscarCliente) Controladores.get("/fxml/buscarCliente.fxml","Buscar cliente",getEscenario());
        vistaClienteDni.limpiarCampo(); // Cancelado siempre es true
        vistaClienteDni.getEscenario().showAndWait();
        if(!vistaClienteDni.isCancelado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BUSCAR_CLIENTE);
            vistaBuscarCliente.asignarCampos();
            vistaBuscarCliente.getEscenario().showAndWait();
        }
    }

    @FXML
    void insertarCliente() {
       InsertarCliente vistaInsertar = (InsertarCliente) Controladores.get("/fxml/insertarCliente.fxml","Insertar cliente",getEscenario());
       vistaInsertar.limpiarCampos(); // Para que siempre sea true antes de aparecer
       vistaInsertar.getEscenario().showAndWait();
       if(!vistaInsertar.isCancelado()) {
           VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
       }
    }

    @FXML
    void listarCliente() {
        ListarClientes vistaListar = (ListarClientes) Controladores.get("/fxml/listarClientes.fxml","Listar clientes",getEscenario());
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_CLIENTES);
        vistaListar.getEscenario().showAndWait();
    }

}
