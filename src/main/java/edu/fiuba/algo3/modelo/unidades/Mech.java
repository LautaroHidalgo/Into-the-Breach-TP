package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Armas.Arma;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public abstract class Mech extends Unidad {

    private final Arma arma;

    protected Mech(int vida, Arma arma) {
        super(vida);
        this.arma = arma;
    }
    public void atacar(Posicion posicionMech, Posicion objetivo, Tablero tablero) {
        arma.atacar(posicionMech, objetivo, tablero);
    }
}
