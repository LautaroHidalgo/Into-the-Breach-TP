package edu.fiuba.algo3.modelo.Tablero;

public class PosicionesNoAlineadasException extends RuntimeException {
    public PosicionesNoAlineadasException() {
        super("Las posiciones no están en la misma fila o columna");
    }
}
