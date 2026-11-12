package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Armas.Puno;

public class MechCombate extends Mech {

    public static final int VIDA = 3;

    public MechCombate() {
        super(VIDA, new Puno());
    }
}
