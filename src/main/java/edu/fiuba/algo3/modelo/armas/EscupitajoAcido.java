package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class EscupitajoAcido extends DisparoDirecto {

    private static final int DANIO = 1;

    public EscupitajoAcido() {
        super(DANIO);
    }

    @Override
    protected void aplicarEfectoSobre(Tablero tablero, Posicion impacto, Direccion direccion) {
        tablero.contaminar(impacto);
    }
}
