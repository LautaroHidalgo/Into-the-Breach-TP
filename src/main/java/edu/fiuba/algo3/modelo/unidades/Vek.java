package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.armas.Arma;

public abstract class Vek extends UnidadDeCombate {

    protected Vek(int vida, int movimiento, Arma arma, Desplazamiento desplazamiento) {
        super(vida, movimiento, arma, desplazamiento);
    }

    @Override
    public void caerAlAgua() {
        morir();
    }
}
