package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;

public class Main {

    public static void main(String[] args) {
        // El controlador crea el modelo usando la FabricaFuenteDatos ficheros y la vista
        FabricaVista fabricaVista = FabricaVista.GRAFICA;
        FabricaFuenteDatos fabricaFuenteDatos = FabricaFuenteDatos.FICHEROS;
        FabricaModelo modelo = FabricaModelo.CASCADA;
        Controlador controlador = new Controlador(modelo,fabricaFuenteDatos,fabricaVista);
        controlador.comenzar();
    }

}

