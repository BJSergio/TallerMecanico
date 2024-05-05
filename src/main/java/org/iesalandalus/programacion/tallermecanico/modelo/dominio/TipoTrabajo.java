package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {

    MECANICO("mecanico"),
    REVISION("revision");

    final String nombre;

    TipoTrabajo(String nombre) {
        this.nombre = nombre;
    }

    public static TipoTrabajo get(Trabajo trabajo) {
        TipoTrabajo tipoTrabajo = null;
        if (trabajo instanceof Mecanico) {
            tipoTrabajo = MECANICO;
        } else if (trabajo instanceof Revision) {
            tipoTrabajo = REVISION;
        }
        return tipoTrabajo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
