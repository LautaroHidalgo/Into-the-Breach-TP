package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.armas.Arma;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public abstract class Mech extends Unidad {

    private final Arma arma;

    protected Mech(int vida, int movimiento, Arma arma) {
        super(vida, movimiento);
        this.arma = arma;
    }

    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero){
        this.registrarAtaque();
        arma.atacar(tablero, origen, objetivo);
    }
}
