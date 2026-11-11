package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.armas.Arma;

public abstract class Mech extends Unidad {

    private final Arma arma;

    protected Mech(int vida, int movimiento, Arma arma) {
        super(vida, movimiento);
        this.arma = arma;
    }


}
