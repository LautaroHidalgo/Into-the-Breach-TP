package edu.fiuba.algo3.modelo.unidades.desplazamiento;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Volador implements Desplazamiento {

    @Override
    public void terminarEnAcido(Unidad unidad) {
        // Vuela por encima no se contamina
    }

    @Override
    public void terminarEnAgua(Unidad unidad) {
        // Vuela por encima no se muere
    }

    @Override
    public boolean puedeAtacarDesdeElAgua() {
        return true;
    }
}
