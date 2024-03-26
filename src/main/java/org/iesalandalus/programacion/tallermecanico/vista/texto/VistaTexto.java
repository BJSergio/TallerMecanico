package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.util.*;

public class VistaTexto implements Vista {

    private final GestorEventos gestorEventos;

    public VistaTexto() {
        this.gestorEventos = new GestorEventos(Evento.values());
    }

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        Evento opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Evento.SALIR);
    }

    private void ejecutar(Evento evento) {
        Consola.mostrarCabecera(evento.toString());
        gestorEventos.notificar(evento);
    }

    @Override
    public void terminar() {
        System.out.println("Usted ha cerrado la aplicación por lo tanto, ha finalizado su ejecución.");
    }

    @Override
    public Cliente leerCliente() {
        String nombre = Consola.leerCadena("Introduce un nombre:");
        String dni = Consola.leerCadena("Introduce un dni:");
        String telefono = Consola.leerCadena("Introduce un teléfono:");
        return new Cliente(nombre, dni, telefono);
    }

    @Override
    public Cliente leerClienteDni() {
        return Cliente.get(Consola.leerCadena("Introduce un dni:"));
    }

    @Override
    public String leerNuevoNombre() {
        return Consola.leerCadena("Introduce un nuevo nombre:");
    }

    @Override
    public String leerNuevoTelefono() {
        return Consola.leerCadena("Introduce un nuevo teléfono:");
    }

    @Override
    public Vehiculo leerVehiculo() {
        String marca = Consola.leerCadena("Introduce una marca:");
        String modelo = Consola.leerCadena("Introduce un modelo:");
        String matricula = Consola.leerCadena("Introduce una matrícula:");
        return new Vehiculo(marca, modelo, matricula);
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(Consola.leerCadena("Introduce una matrícula:"));
    }

    @Override
    public Trabajo leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = Consola.leerFecha("Introduce una fecha de inicio:");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    @Override
    public Trabajo leerMecanico() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = Consola.leerFecha("Introduce una fecha de inicio:");
        return new Mecanico(cliente, vehiculo, fechaInicio);
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        Vehiculo vehiculo = leerVehiculoMatricula();
        return Trabajo.get(vehiculo);
    }

    @Override
    public int leerHoras() {
        return Consola.leerEntero("Introduce el número de horas para un trabajo:");
    }

    @Override
    public float leerPrecioMaterial() {
        return Consola.leerReal("Introduce el precio de material para un trabajo mecánico:");
    }

    @Override
    public LocalDate leerFechaCierre() {
        return Consola.leerFecha("Introduce la fecha de cierre para dar por finalizado el servicio:");
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        Objects.requireNonNull(evento, "No puedo notificar un evento nulo.");
        Objects.requireNonNull(texto, "No puedo mostrar un texto nulo.");
        if (exito) {
            System.out.printf("%s%n", texto);
        } else {
            System.out.printf("ERROR: %s%n", texto);
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "No puedo mostrar un cliente que no existe.");
        System.out.println(cliente);
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No puedo mostrar un vehículo que no existe.");
        System.out.println(vehiculo);
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No puedo mostrar un trabajo que no existe.");
        System.out.println(trabajo);
    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        Objects.requireNonNull(clientes, "No puedo mostrar clientes nulos.");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes para mostrar.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        Objects.requireNonNull(vehiculos, "No puedo mostrar vehiculos nulos.");
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos para mostrar.");
        } else {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        }
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        Objects.requireNonNull(trabajos, "No puedo mostrar trabajos nulos.");
        if (trabajos.isEmpty()) {
            System.out.println("No hay trabajos para mostrar.");
        } else {
            for (Trabajo trabajo : trabajos) {
                System.out.println(trabajo);
            }
        }
    }
}
