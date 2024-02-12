package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {

    List<Revision> coleccionRevisiones;

    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> revisionCliente = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getCliente().equals(cliente)) {
                revisionCliente.add(revision);
            }
        }
        return revisionCliente;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> revisionVehiculo = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionVehiculo.add(revision);
            }
        }
        return revisionVehiculo;
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        coleccionRevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for (Revision revision : coleccionRevisiones) {
            if (revision.getCliente().equals(cliente) && !revision.estaCerrada()) {
                throw new OperationNotSupportedException("El cliente tiene otra revisión en curso.");
            }
            if (revision.getVehiculo().equals(vehiculo) && !revision.estaCerrada()) {
                throw new OperationNotSupportedException("El vehículo está actualmente en revisión.");
            }
            if (revision.estaCerrada() && revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())) {
                throw new OperationNotSupportedException("El cliente tiene una revisión posterior.");
            }
            if (revision.estaCerrada() && revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())) {
                throw new OperationNotSupportedException("El vehículo tiene una revisión posterior.");
            }

        }
    }

    private Revision getRevision(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        if (buscar(revision) == null) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        return revision;
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        getRevision(revision).anadirPrecioMaterial(precioMaterial);

    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        getRevision(revision).anadirHoras(horas);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        getRevision(revision).cerrar(fechaFin);
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        if (!coleccionRevisiones.contains(revision)) {
            revision = null;
        }
        return revision;
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
        if (buscar(revision) == null) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        coleccionRevisiones.remove(revision);
    }
}
