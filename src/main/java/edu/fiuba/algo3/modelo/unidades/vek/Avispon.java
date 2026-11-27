package edu.fiuba.algo3.modelo.unidades.vek;

import edu.fiuba.algo3.modelo.armas.Picadura;
import edu.fiuba.algo3.modelo.unidades.desplazamiento.Volador;

public class Avispon extends Vek {

    public static final int VIDA = 2;
    public static final int MOVIMIENTO = 4;

    public Avispon() {
        super(VIDA, MOVIMIENTO, new Picadura(), new Volador());
    }

}
