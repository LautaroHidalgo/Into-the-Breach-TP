package edu.fiuba.algo3.modelo.unidades;


import edu.fiuba.algo3.modelo.armas.AtaqueAdyacente;

public class Saltador extends Vek {

    private static final int VIDA = 2;
    private static final int MOVIMIENTO = 3;
    private static final int DANIO_MORDIDA = 2;

    public Saltador() {
        super(VIDA, MOVIMIENTO, new AtaqueAdyacente(DANIO_MORDIDA), new Terrestre());
    }
}
