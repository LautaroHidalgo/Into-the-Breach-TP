package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.armas.Puno;

public class MechCombate extends Mech {

    public static final int VIDA = 3;
    public static final int MOVIMIENTO = 3;

    public MechCombate() {
        super(VIDA, MOVIMIENTO, new Puno());
    }
}
