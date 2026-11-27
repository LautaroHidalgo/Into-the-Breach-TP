package edu.fiuba.algo3.modelo.unidades.mech;

import edu.fiuba.algo3.modelo.armas.Canon;

public class MechCanon extends Mech {

    private static final int VIDA = 3;
    private static final int MOVIMIENTO = 3;

    public MechCanon() {
        super(VIDA, MOVIMIENTO, new Canon());
    }
}
