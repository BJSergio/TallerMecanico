package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;

public class Main {

    public static void main(String[] args) {

        // El controlador crea el modelo usando la FabricaFuenteDatos ficheros y la vista
        FabricaModelo modelo = FabricaModelo.CASCADA;
        FabricaFuenteDatos fuenteDatos = FabricaFuenteDatos.FICHEROS;
        FabricaVista vista = FabricaVista.TEXTO;
        Controlador controlador = new Controlador(modelo, fuenteDatos, vista);
        controlador.comenzar();
        controlador.terminar();
    }
}
