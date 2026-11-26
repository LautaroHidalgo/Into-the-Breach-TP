package edu.fiuba.algo3.modelo.unidades;

public interface Desplazamiento {

    void terminarEnAcido(Unidad unidad);

    void terminarEnAgua(Unidad unidad);

    boolean puedeAtacarDesdeElAgua();
}
