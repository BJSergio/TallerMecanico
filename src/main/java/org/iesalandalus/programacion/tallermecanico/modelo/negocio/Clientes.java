package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {

    private final List<Cliente> coleccionClientes;

    public Clientes() {
        coleccionClientes = new ArrayList<>();
    } // Inicializa la lista

    public List<Cliente> get() {
        return new ArrayList<>(coleccionClientes); // Lista independiente del atributo
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (buscar(cliente) != null) { // Significa que ya está en la lista
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        Cliente clienteBuscado;
        boolean esModificado = false;
        if (buscar(cliente) == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        } else {
            clienteBuscado = buscar(cliente);
        }
        if (nombre != null && telefono != null) {
            clienteBuscado.setNombre(nombre);
            clienteBuscado.setTelefono(telefono);
            esModificado = true;
        }
        if (telefono == null && nombre != null) {
            clienteBuscado.setNombre(nombre);
            esModificado = true;
        } else if (nombre == null && telefono != null) {
            clienteBuscado.setTelefono(telefono);
            esModificado = true;
        }
        return esModificado;
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        // Devuelve el cliente y si no está en la colección devuelve null
        if (!coleccionClientes.contains(cliente)) {
            cliente = null;
        }
        return cliente;
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (buscar(cliente) == null) { // El cliente a borrar no existe
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        coleccionClientes.remove(cliente);
    }
}
