package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.armas.Arma;


public abstract class Mech extends UnidadDeCombate {

    protected Mech(int vida, int movimiento, Arma arma) {
        super(vida, movimiento, arma, new Terrestre());

    }

}
