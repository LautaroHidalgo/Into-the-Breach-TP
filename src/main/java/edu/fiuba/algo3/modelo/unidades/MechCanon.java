package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.armas.Canon;

public class MechCanon extends Mech {

    public static final int VIDA = 3;
    public static final int MOVIMIENTO = 3;

    public MechCanon() {
        super(VIDA, MOVIMIENTO, new Canon());
    }
}
