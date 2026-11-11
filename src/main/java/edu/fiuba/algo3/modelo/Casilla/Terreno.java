package edu.fiuba.algo3.modelo.Casilla;

/**
 * Terreno
 */
public class Terreno {

    public boolean esIgual(Terreno otroTerreno) {
        return this.getClass() == otroTerreno.getClass();
    }

    public boolean esTransitable() {
        return false;
    }

}
