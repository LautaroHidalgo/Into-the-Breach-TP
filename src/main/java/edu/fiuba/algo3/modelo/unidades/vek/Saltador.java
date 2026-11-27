package edu.fiuba.algo3.modelo.unidades.vek;


import edu.fiuba.algo3.modelo.armas.Mordida;
import edu.fiuba.algo3.modelo.unidades.desplazamiento.Terrestre;

public class Saltador extends Vek {

    private static final int VIDA = 2;
    private static final int MOVIMIENTO = 3;

    public Saltador() {
        super(VIDA, MOVIMIENTO, new Mordida(), new Terrestre());
    }
}
