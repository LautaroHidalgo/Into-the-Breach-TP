package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class Artilleria extends Arma {

    public static final int DANIO = 1;

    public Artilleria() {
        super(DANIO);
    }

    @Override
    protected boolean alcanza(Posicion origen, Posicion objetivo) {
        throw new UnsupportedOperationException("La Artillería todavía no está implementada");
    }

    @Override
    protected void disparar(Tablero tablero, Posicion origen, Posicion objetivo, Direccion direccion) {
        throw new UnsupportedOperationException("La Artillería todavía no está implementada");
    }

}
