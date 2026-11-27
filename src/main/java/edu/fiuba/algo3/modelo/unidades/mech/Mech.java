package edu.fiuba.algo3.modelo.unidades.mech;

import edu.fiuba.algo3.modelo.armas.Arma;
import edu.fiuba.algo3.modelo.unidades.UnidadDeCombate;
import edu.fiuba.algo3.modelo.unidades.desplazamiento.Terrestre;


public abstract class Mech extends UnidadDeCombate {

    protected Mech(int vida, int movimiento, Arma arma) {
        super(vida, movimiento, arma, new Terrestre());

    }

}
