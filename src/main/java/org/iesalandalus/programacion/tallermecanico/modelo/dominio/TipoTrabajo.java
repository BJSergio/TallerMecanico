package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {

    MECANICO("mecanico"),
    REVISION("revision");

    final String nombre;

    TipoTrabajo(String nombre) {
        this.nombre = nombre;
    }

    public static TipoTrabajo get(Trabajo trabajo) {
        return trabajo instanceof Mecanico ? MECANICO : REVISION;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
