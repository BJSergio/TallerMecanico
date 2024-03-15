package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class Mecanico extends Trabajo {

    private static final float FACTOR_HORA = 30f;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5f;
    protected float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        this.precioMaterial = mecanico.precioMaterial;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrado()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        }
        this.precioMaterial += precioMaterial;
    }

    @Override
    public float getPrecioEspecifico() {
        return (getHoras() * FACTOR_HORA + precioMaterial * FACTOR_PRECIO_MATERIAL);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        String cadenaADevolver;
        if (!estaCerrado()) {
            cadenaADevolver = String.format("Mecánico -> %s - %s (%s - ): %d horas, %.2f € en material", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getHoras(), precioMaterial);
        } else {
            cadenaADevolver = String.format("Mecánico -> %s - %s (%s - %s): %d horas, %.2f € en material, %.2f € total", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getFechaFin().format(FORMATO_FECHA), getHoras(), precioMaterial, getPrecio());
        }
        return cadenaADevolver;
    }
}
