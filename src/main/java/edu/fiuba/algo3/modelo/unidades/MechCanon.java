package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Armas.Canon;

public class MechCanon extends Mech {

    public static final int VIDA = 3;

    public MechCanon() {
        super(VIDA, new Canon());
    }
}
