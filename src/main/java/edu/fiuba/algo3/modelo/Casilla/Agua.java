package edu.fiuba.algo3.modelo.casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Agua extends TerrenoTransitable {

    @Override
    public void aplicarEfecto(Unidad unidad) {
        unidad.terminarEnAgua();
    }

    @Override
    public boolean permiteAtacar(Unidad unidad) {
        return unidad.puedeAtacarDesdeElAgua();
    }
}
