package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class Mordida extends Arma {

    private static final int DANIO = 2;

    public Mordida() {
        super(DANIO);
    }

    @Override
    protected boolean alcanza(Posicion origen, Posicion objetivo) {
        return origen.distanciaACelda(objetivo) == 1;
    }

    @Override
    protected void disparar(Tablero tablero, Posicion origen, Posicion objetivo, Direccion direccion) {
        tablero.atacar(objetivo, danio);
    }
}
