package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Trabajos implements ITrabajos {

    private static final String FICHERO_TRABAJOS = String.format("%s%s%s", "datos", File.separator, "trabajos.xml");
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String RAIZ = "trabajos";
    private static final String TRABAJO = "trabajo";
    private static final String CLIENTE = "cliente";
    private static final String VEHICULO = "vehiculo";
    private static final String FECHA_INICIO = "fechaInicio";
    private static final String FECHA_FIN = "fechaFin";
    private static final String HORAS = "horas";
    private static final String PRECIO_MATERIAL = "precioMaterial";
    private static final String TIPO = "tipo";
    private static final String REVISION = TipoTrabajo.REVISION.toString();
    private static final String MECANICO = TipoTrabajo.MECANICO.toString();

    private final List<Trabajo> coleccionTrabajos;

    private static Trabajos instancia;

    private Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }

    public static Trabajos getInstancia() {
        if (instancia == null) {
            instancia = new Trabajos();
        }
        return instancia;
    }

    @Override
    public void comenzar() {
        Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_TRABAJOS);
        System.out.println("El fichero trabajos.xml se ha leído correctamente.");
        procesarDocumentoXml(documentoXml);
    }

    private void procesarDocumentoXml(Document documentoXml) {
        Objects.requireNonNull(documentoXml, "No puedo procesar un documento nulo.");
        NodeList listaTrabajos = documentoXml.getElementsByTagName(TRABAJO);
        for (int i = 0; i < listaTrabajos.getLength(); i++) {
            Element elementoTrabajo = (Element) listaTrabajos.item(i);
            try {
                insertar(getTrabajo(elementoTrabajo));
            } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
                System.out.println("ERROR: Al procesar el trabajo número: " + i + ", " + e.getMessage().toLowerCase());
            }
        }
    }

    private Trabajo getTrabajo(Element elemento) throws OperationNotSupportedException {
        Objects.requireNonNull(elemento, "El elemento no puede ser nulo.");
        Trabajo trabajoADevolver = null;
        Cliente clienteEncontrado = Clientes.getInstancia().buscar(Cliente.get(elemento.getAttribute(CLIENTE)));
        Vehiculo vehiculoEncontrado = Vehiculos.getInstancia().buscar(Vehiculo.get(elemento.getAttribute(VEHICULO)));
        LocalDate fechaInicio = LocalDate.parse(elemento.getAttribute(FECHA_INICIO), FORMATO_FECHA);
        if (elemento.getAttribute(TIPO).equals(MECANICO)) {
            trabajoADevolver = new Mecanico(clienteEncontrado, vehiculoEncontrado, fechaInicio);
            if (elemento.hasAttribute(PRECIO_MATERIAL)) {
                float precioMaterial = Float.parseFloat(elemento.getAttribute(PRECIO_MATERIAL));
                ((Mecanico) trabajoADevolver).anadirPrecioMaterial(precioMaterial);
            }
        } else if (elemento.getAttribute(TIPO).equals(REVISION)) {
            trabajoADevolver = new Revision(clienteEncontrado, vehiculoEncontrado, fechaInicio);
        }
        if (trabajoADevolver != null) {
            if (elemento.hasAttribute(HORAS)) {
                int horas = Integer.parseInt(elemento.getAttribute(HORAS));
                trabajoADevolver.anadirHoras(horas);
            }
            if (elemento.hasAttribute(FECHA_FIN)) {
                LocalDate fechaFin = LocalDate.parse(elemento.getAttribute(FECHA_FIN), FORMATO_FECHA);
                trabajoADevolver.cerrar(fechaFin);
            }
        }
        return trabajoADevolver;
    }

    @Override
    public void terminar() {
        Document documentoXml = crearDocumentoXml();
        if (documentoXml != null) {
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for (Trabajo trabajo : coleccionTrabajos) {
                Element elementoTrabajo = getElemento(documentoXml, trabajo);
                documentoXml.getDocumentElement().appendChild(elementoTrabajo);
            }
        }
        UtilidadesXml.escribirDocumentoXml(documentoXml, FICHERO_TRABAJOS);
    }

    private Document crearDocumentoXml() {
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null) {
            documentoXml = constructor.newDocument();
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml, Trabajo trabajo) {
        Objects.requireNonNull(documentoXml, "El documento xml no puede ser nulo.");
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        Element elementoTrabajo = documentoXml.createElement(TRABAJO);
        if (trabajo instanceof Revision) {
            elementoTrabajo.setAttribute(TIPO, REVISION);
        } else if (trabajo instanceof Mecanico mecanico) {
            elementoTrabajo.setAttribute(TIPO, MECANICO);
            if (mecanico.getPrecioMaterial() != 0) {
                elementoTrabajo.setAttribute(PRECIO_MATERIAL, String.valueOf(mecanico.getPrecioMaterial()).replace(',', '.'));
            }
        }
        elementoTrabajo.setAttribute(CLIENTE, trabajo.getCliente().getDni());
        elementoTrabajo.setAttribute(VEHICULO, trabajo.getVehiculo().matricula());
        elementoTrabajo.setAttribute(FECHA_INICIO, trabajo.getFechaInicio().format(FORMATO_FECHA));

        if (trabajo.getHoras() != 0) {
            elementoTrabajo.setAttribute(HORAS, String.valueOf(trabajo.getHoras()));
        }
        if (trabajo.estaCerrado()) {
            elementoTrabajo.setAttribute(FECHA_FIN, trabajo.getFechaFin().format(FORMATO_FECHA));
        }
        return elementoTrabajo;
    }

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) { // Los trabajos para ese cliente
        List<Trabajo> revisionTrabajo = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                revisionTrabajo.add(trabajo);
            }
        }
        return revisionTrabajo;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) { // Los trabajos para vehículo
        List<Trabajo> revisionTrabajo = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                revisionTrabajo.add(trabajo);
            }
        }
        return revisionTrabajo;
    }

    @Override
    public Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes) {
        Objects.requireNonNull(mes, "El mes no puede ser nulo.");
        Map<TipoTrabajo, Integer> estadisticas = inicializarEstadisticas();
        // Antes inicializaba mal las estadísticas, cuando no había trabajos salía null
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getFechaInicio().getMonth().equals(mes.getMonth()) && trabajo.getFechaInicio().getYear() == mes.getYear()) {
                TipoTrabajo tipoTrabajo = TipoTrabajo.get(trabajo);
                estadisticas.put(tipoTrabajo, estadisticas.get(tipoTrabajo) + 1);
            }
        }
        return estadisticas;
    }

    private Map<TipoTrabajo, Integer> inicializarEstadisticas() {
        Map<TipoTrabajo, Integer> mapaDevolver = new EnumMap<>(TipoTrabajo.class);
        mapaDevolver.put(TipoTrabajo.MECANICO, 0);
        mapaDevolver.put(TipoTrabajo.REVISION, 0);
        return mapaDevolver;
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajos.add(trabajo);
    }

    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente) && !trabajo.estaCerrado()) {
                throw new OperationNotSupportedException("El cliente tiene otro trabajo en curso.");
            }
            if (trabajo.getVehiculo().equals(vehiculo) && !trabajo.estaCerrado()) {
                throw new OperationNotSupportedException("El vehículo está actualmente en el taller.");
            }
            if (trabajo.estaCerrado() && trabajo.getCliente().equals(cliente) && !fechaRevision.isAfter(trabajo.getFechaFin())) {
                throw new OperationNotSupportedException("El cliente tiene otro trabajo posterior.");
            }
            if (trabajo.estaCerrado() && trabajo.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(trabajo.getFechaFin())) {
                throw new OperationNotSupportedException("El vehículo tiene otro trabajo posterior.");
            }
        }
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo añadir horas a un trabajo nulo.");
        getTrabajoAbierto(trabajo.getVehiculo()).anadirHoras(horas);
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws OperationNotSupportedException { // Busca para ese vehículo su último trabajo abierto
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        boolean trabajoEncontrado = false;
        Trabajo trabajoADevolver = null;
        for (int i = 0; i < coleccionTrabajos.size() && !trabajoEncontrado; i++) {
            if (!coleccionTrabajos.get(i).estaCerrado() && coleccionTrabajos.get(i).getVehiculo().equals(vehiculo)) {
                trabajoADevolver = coleccionTrabajos.get(i);
                trabajoEncontrado = true;
            }
        }
        if (trabajoADevolver == null) {
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        return trabajoADevolver;
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo añadir precio del material a un trabajo nulo.");
        if (getTrabajoAbierto(trabajo.getVehiculo()) instanceof Revision) {
            throw new OperationNotSupportedException("No se puede añadir precio al material para este tipo de trabajos.");
        } else if (getTrabajoAbierto(trabajo.getVehiculo()) instanceof Mecanico mecanico) {
            mecanico.anadirPrecioMaterial(precioMaterial); // Convierte el trabajo que me pasan en uno mecánico
        }
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo cerrar un trabajo nulo.");
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        getTrabajoAbierto(trabajo.getVehiculo()).cerrar(fechaFin);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        int indice = coleccionTrabajos.indexOf(trabajo);
        return (indice == -1) ? null : coleccionTrabajos.get(indice);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
        if (buscar(trabajo) == null) {
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
        coleccionTrabajos.remove(trabajo);
    }
}
