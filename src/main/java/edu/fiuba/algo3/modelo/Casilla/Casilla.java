package edu.fiuba.algo3.modelo.Casilla;

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

    public void recibirUnidad(Unidad unidad);
}
