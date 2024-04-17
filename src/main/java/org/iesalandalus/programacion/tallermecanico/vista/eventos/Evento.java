package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {

    INSERTAR_CLIENTE(1, "Insertar un cliente"),
    BUSCAR_CLIENTE(2, "Buscar un cliente"),
    BORRAR_CLIENTE(3, "Borrar un cliente"),
    LISTAR_CLIENTES(4, "Listar todos los clientes"),
    MODIFICAR_CLIENTE(5, "Modificar un cliente"),
    INSERTAR_VEHICULO(6, "Insertar un vehículo"),
    BUSCAR_VEHICULO(7, "Buscar un vehículo"),
    BORRAR_VEHICULO(8, "Borrar un vehículo"),
    LISTAR_VEHICULOS(9, "Listar todos los vehículos"),
    INSERTAR_REVISION(10, "Insertar una revisión"),
    INSERTAR_MECANICO(11, "Insertar un trabajo mecánico"),
    BUSCAR_TRABAJO(12, "Buscar un trabajo"),
    BORRAR_TRABAJO(13, "Borrar un trabajo"),
    LISTAR_TRABAJOS(14, "Listar todos los trabajos"),
    LISTAR_TRABAJOS_CLIENTE(15, "Listar trabajos de un cliente"),
    LISTAR_TRABAJOS_VEHICULO(16, "Listar trabajos de un vehículo"),
    ANADIR_HORAS_TRABAJO(17, "Añadir horas a un trabajo"),
    ANADIR_PRECIO_MATERIAL_TRABAJO(18, "Añadir precio material a un trabajo"),
    CERRAR_TRABAJO(19, "Cerrar un trabajo"),
    MOSTRAR_ESTADISTICAS_MENSUALES(20, "Mostrar estadísticas de un mes"),
    SALIR(21, "Salir de la aplicación");

    private final int codigo;
    private final String texto;
    private static final Map<Integer, Evento> eventos;

    static {
        eventos = new HashMap<>();
        for (Evento evento : Evento.values()) {
            eventos.put(evento.codigo, evento); // Así es más claro
        }
    }

    Evento(int codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    public static boolean esValido(int codigo) {
        return (eventos.containsKey(codigo));
    }

    public static Evento get(int codigo) { // Me devuelve el valor del mapa
        if (!esValido(codigo)) {
            throw new IllegalArgumentException("La opción introducida no es válida, inténtelo de nuevo.");
        }
        return eventos.get(codigo);
    }

    @Override
    public String toString() {
        return String.format("%d. %s", codigo, texto);
    }
}
