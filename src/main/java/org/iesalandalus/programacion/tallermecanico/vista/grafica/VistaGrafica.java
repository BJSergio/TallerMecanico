package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores.*;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VistaGrafica implements Vista {

    private static VistaGrafica vistaGrafica;
    private Controlador ventanaPrincipal;
    private final GestorEventos gestorEventos;

    private VistaGrafica() {
        // Inicializo el gestor con todos los eventos
        this.gestorEventos = new GestorEventos(Evento.values());
    }

    public static VistaGrafica getInstancia() {
        if (vistaGrafica == null) {
            vistaGrafica = new VistaGrafica();
        }
        return vistaGrafica;
    }

    void setVentanaPrincipal(Controlador ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public Controlador getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        LanzadoraVentanaPrincipal.comenzar();
    }

    @Override
    public void terminar() {

    }

    @Override
    public Cliente leerCliente() {
        InsertarCliente insertarCliente = (InsertarCliente) Controladores.get("/fxml/insertarCliente.fxml", "Insertar cliente", ventanaPrincipal.getEscenario());
        return insertarCliente.getCliente();
    }

    @Override
    public Cliente leerClienteDni() {
        LeerClienteDni vistaLeerDni = (LeerClienteDni) Controladores.get("/fxml/leerClienteDni.fxml", "Leer dni", ventanaPrincipal.getEscenario());
        return Cliente.get(vistaLeerDni.getDniLeido());
    }

    @Override
    public String leerNuevoNombre() {
        ModificarCliente modificarCliente = (ModificarCliente) Controladores.get("/fxml/modificarCliente.fxml","Modificar un cliente",ventanaPrincipal.getEscenario());
        return modificarCliente.getNuevoNombre();
    }

    @Override
    public String leerNuevoTelefono() {
        ModificarCliente modificarCliente = (ModificarCliente) Controladores.get("/fxml/modificarCliente.fxml","Modificar un cliente",ventanaPrincipal.getEscenario());
        return modificarCliente.getNuevoTelefono();
    }

    @Override
    public Vehiculo leerVehiculo() {
        return null;
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return null;
    }

    @Override
    public Trabajo leerRevision() {
        return null;
    }

    @Override
    public Trabajo leerMecanico() {
        return null;
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return null;
    }

    @Override
    public int leerHoras() {
        return 0;
    }

    @Override
    public float leerPrecioMaterial() {
        return 0;
    }

    @Override
    public LocalDate leerFechaCierre() {
        return null;
    }

    @Override
    public LocalDate leerMes() {
        return null;
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        if (exito) {
            Dialogos.mostrarDialogoInformacion(evento.toString(), texto, ventanaPrincipal.getEscenario());
        } else {
            Dialogos.mostrarDialogoError(evento.toString(), texto, ventanaPrincipal.getEscenario());
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente) {
        // Una vez cargada la ventana le asigno el cliente que me pasan, el cual el modelo busca
        // Si salta alguna excepción en el buscar no llama a la clase
        BuscarCliente vistaBuscarCliente = (BuscarCliente) Controladores.get("/fxml/buscarCliente.fxml", "Buscar cliente", ventanaPrincipal.getEscenario());
        vistaBuscarCliente.setCliente(cliente);
        vistaBuscarCliente.getEscenario().showAndWait();
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

    }

    @Override
    public void mostrarTrabajosCliente(List<Trabajo> trabajos) {
        Vista.super.mostrarTrabajosCliente(trabajos);
    }

    @Override
    public void mostrarTrabajosVehiculo(List<Trabajo> trabajos) {
        Vista.super.mostrarTrabajosVehiculo(trabajos);
    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        ListarClientes listarClientes = (ListarClientes) Controladores.get("/fxml/listarClientes.fxml", "Listar clientes", ventanaPrincipal.getEscenario());
        listarClientes.setListaClientes(clientes);
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {

    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {

    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas) {

    }
}
