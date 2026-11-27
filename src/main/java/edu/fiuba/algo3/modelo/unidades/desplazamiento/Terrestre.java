package edu.fiuba.algo3.modelo.unidades.desplazamiento;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Terrestre implements Desplazamiento {

    @Override
    public void terminarEnAcido(Unidad unidad) {
        unidad.recibirContaminacion();
    }

    @Override
    public void terminarEnAgua(Unidad unidad) {
        unidad.caerAlAgua();
    }

    @Override
    public boolean puedeAtacarDesdeElAgua() {
        return false;
    }
}