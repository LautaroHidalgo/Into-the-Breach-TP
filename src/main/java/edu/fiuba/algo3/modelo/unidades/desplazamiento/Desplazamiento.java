package edu.fiuba.algo3.modelo.unidades.desplazamiento;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public interface Desplazamiento {

    void terminarEnAcido(Unidad unidad);

    void terminarEnAgua(Unidad unidad);

    boolean puedeAtacarDesdeElAgua();
}
