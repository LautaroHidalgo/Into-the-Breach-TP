package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Armas.Artilleria;

public class MechArtilleria extends Mech{

    public static final int VIDA = 2;

    public MechArtilleria() {
        super(VIDA, new Artilleria());
    }
}
