package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements IClientes {

    private final List<Cliente> coleccionClientes;

    private static Clientes instancia;

    private static final String FICHERO_CLIENTES = "clientes.xml";
    private static final String RAIZ = "clientes";
    private static final String CLIENTE = "cliente";
    private static final String NOMBRE = "nombre";
    private static final String DNI = "dni";
    private static final String TELEFONO = "telefono";

    private Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    static Clientes getInstancia() {
        if (instancia == null) {
            instancia = new Clientes();
        }
        return instancia;
    }

    @Override
    public void comenzar() {
        Document documentoXml = crearDocumentoXml();
        


    }

    private Cliente getCliente(Element elemento) {
        String dni = elemento.getAttribute(DNI);
        String nombre = elemento.getAttribute(NOMBRE);
        String telefono = elemento.getAttribute(TELEFONO);
        return new Cliente(dni, nombre, telefono);
    }

    @Override
    public void terminar() {

    }

    private Document crearDocumentoXml() {
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null) {
            documentoXml = constructor.newDocument();
        }
        return documentoXml; // Devuelvo ese documentoXml con un árbol DOM vacío
    }

    private Element getElemento(Document documentoXml, Cliente cliente) {

        boolean esMismoCliente = false;
        Element clienteADevolver = null;
        NodeList clientes = documentoXml.getElementsByTagName(CLIENTE);

        for (int i = 0; i < clientes.getLength() && !esMismoCliente; i++) {
            Element iteradorCliente = (Element) clientes.item(i);
            if (iteradorCliente.getAttribute(DNI).equals(cliente.getDni())) {
                clienteADevolver = iteradorCliente;
                esMismoCliente = true;
            }
        }
        return clienteADevolver;
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
