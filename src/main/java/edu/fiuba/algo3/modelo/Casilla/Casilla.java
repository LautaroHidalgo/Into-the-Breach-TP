package edu.fiuba.algo3.modelo.Casilla;

import edu.fiuba.algo3.modelo.unidades.Unidad;

/**
 * Casilla
 */
public class Casilla {
    private Terreno terreno;

    private Unidad unidad;

    public Casilla(Terreno terreno){
        this.terreno = terreno;
    }

    public boolean esIgual(Casilla otraCasilla){
        return otraCasilla.compararme(terreno);
    } 

    private boolean compararme(Terreno otroTerreno){
        return terreno.esIgual(otroTerreno);
    }

    public void recibirUnidad(Unidad unidadRecibida){
        this.unidad = unidadRecibida;
    }
}
