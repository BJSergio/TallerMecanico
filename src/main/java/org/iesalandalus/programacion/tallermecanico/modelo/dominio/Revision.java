package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Revision extends Trabajo {

    private static final float FACTOR_HORA = 35f;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
    }

    public Revision(Revision revision) {
        super(revision); // Llamada al constructor copia de la clase padre
    }

    @Override
    public float getPrecioEspecifico() {
        return getHoras() * FACTOR_HORA;
    }

    @Override
    public String toString() {
        String cadenaADevolver;
        if (!estaCerrado()) {
            cadenaADevolver = String.format("Revisión -> %s - %s (%s - ): %d horas", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getHoras());
        } else {
            cadenaADevolver = String.format("Revisión -> %s - %s (%s - %s): %d horas, %.2f € total", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getFechaFin().format(FORMATO_FECHA), getHoras(), getPrecio());
        }
        return cadenaADevolver;
    }
}
