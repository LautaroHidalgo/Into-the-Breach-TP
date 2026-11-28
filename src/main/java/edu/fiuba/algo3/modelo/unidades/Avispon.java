package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.armas.AtaqueAdyacente;

public class Avispon extends Vek {

    public static final int VIDA = 2;
    public static final int MOVIMIENTO = 4;
    public static final int DANIO_PICADURA = 1;

    public Avispon() {
        super(VIDA, MOVIMIENTO, new AtaqueAdyacente(DANIO_PICADURA), new Volador());
    }

}
