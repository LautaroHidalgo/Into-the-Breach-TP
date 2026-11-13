package edu.fiuba.algo3.modelo.Tablero;

public enum Direccion {
    ARRIBA(-1, 0),
    ABAJO(1, 0),
    IZQUIERDA(0, -1),
    DERECHA(0, 1);

    private final int deltaFila;
    private final int deltaColumna;

    Direccion(int deltaFila, int deltaColumna) {
        this.deltaFila = deltaFila;
        this.deltaColumna = deltaColumna;
    }

    Posicion desplazar(int fila, int columna) {
        return new Posicion(fila + deltaFila, columna + deltaColumna);
    }

    static Direccion entre(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        int sentidoFila = Integer.signum(filaDestino - filaOrigen);
        int sentidoColumna = Integer.signum(columnaDestino - columnaOrigen);
        for (Direccion direccion : values()) {
            if (direccion.deltaFila == sentidoFila && direccion.deltaColumna == sentidoColumna) {
                return direccion;
            }
        }
        throw new PosicionesNoAlineadasException();
    }
}
