package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.armas.Picadura;

public class Avispon extends Vek {

    public static final int VIDA = 2;
    public static final int MOVIMIENTO = 4;

    public Avispon() {
        super(VIDA, MOVIMIENTO, new Picadura(), new Volador());
    }

}
