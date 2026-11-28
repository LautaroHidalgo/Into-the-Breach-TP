package edu.fiuba.algo3.modelo.casilla;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Acido extends TerrenoTransitable {

    @Override
    public void aplicarEfecto(Unidad unidad) {
        unidad.terminarEnAcido();
    }
}