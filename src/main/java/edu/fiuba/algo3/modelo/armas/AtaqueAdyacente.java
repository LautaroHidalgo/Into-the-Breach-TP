package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class AtaqueAdyacente extends Arma {

    private static final int DISTANCIA = 1;

    public AtaqueAdyacente(int danio) {
        super(danio);
    }

    @Override
    protected boolean alcanza(Posicion origen, Posicion objetivo){
        return origen.distanciaACelda(objetivo) == DISTANCIA;
    }

    @Override
    protected void disparar(Tablero tablero, Posicion origen, Posicion objetivo, Direccion direccion) {
        tablero.atacar(objetivo, danio);
    }
}
