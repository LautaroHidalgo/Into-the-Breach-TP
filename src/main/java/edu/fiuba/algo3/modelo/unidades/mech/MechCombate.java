package edu.fiuba.algo3.modelo.unidades.mech;

import edu.fiuba.algo3.modelo.armas.Puno;

public class MechCombate extends Mech {

    private static final int VIDA = 3;
    private static final int MOVIMIENTO = 3;

    public MechCombate() {
        super(VIDA, MOVIMIENTO, new Puno());
    }
}
