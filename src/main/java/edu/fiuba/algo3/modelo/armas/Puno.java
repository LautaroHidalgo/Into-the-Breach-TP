package edu.fiuba.algo3.modelo.armas;


import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class Puno extends AtaqueAdyacente {

    private static final int DANIO = 2;

    public Puno() {
        super(DANIO);
    }

    @Override
    protected void disparar(Tablero tablero, Posicion origen, Posicion objetivo, Direccion direccion) {
        super.disparar(tablero, origen, objetivo, direccion);
        tablero.empujarUnidad(objetivo, direccion);
    }
}
