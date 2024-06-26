package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements IClientes {

    private final List<Cliente> coleccionClientes;

    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    @Override
    public List<Cliente> get() {
        return new ArrayList<>(coleccionClientes); // Devuelvo una copia de esa misma lista
    }

    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (buscar(cliente) != null) { // Significa que ya está en la lista
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        Cliente clienteBuscado;
        boolean esModificado = false;
        if (buscar(cliente) == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        } else {
            clienteBuscado = buscar(cliente);
        }
        if (nombre != null && !nombre.isBlank()) {
            clienteBuscado.setNombre(nombre);
            esModificado = true;
        }
        if (telefono != null && !telefono.isBlank()) {
            clienteBuscado.setTelefono(telefono);
            esModificado = true;
        }
        return esModificado;
    }

    @Override
    public Cliente buscar(Cliente cliente) { // Antes devolvía el cliente que me pasaban por parámetro
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente); // Dame el índice que ocupa el mismo dni
        return (indice == -1) ? null : coleccionClientes.get(indice); // Después devuélvelo
    }

    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (buscar(cliente) == null) { // El cliente a borrar no existe
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        coleccionClientes.remove(cliente);
    }
}
