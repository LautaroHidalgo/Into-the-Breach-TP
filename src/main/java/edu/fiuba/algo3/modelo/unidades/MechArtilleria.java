package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.armas.Artilleria;

public class MechArtilleria extends Mech{

    public static final int VIDA = 2;
    public static final int MOVIMIENTO = 3;

    public MechArtilleria() {
        super(VIDA, MOVIMIENTO, new Artilleria());
    }
}
