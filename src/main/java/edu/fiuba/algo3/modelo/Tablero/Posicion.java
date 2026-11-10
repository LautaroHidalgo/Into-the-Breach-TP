package edu.fiuba.algo3.modelo.Tablero;

import java.util.Objects;

public class Posicion {

    private final int fila;
    private final int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int distanciaACelda(Posicion otra) {
        return otra.distanciaACoordenadas(this.fila, this.columna);
    }

    private int distanciaACoordenadas(int fil, int col) {
        return Math.abs(fila - fil) + Math.abs(columna - col);
    }

    public boolean esIgualA(Posicion otra){
        return otra.compararme(fila, columna);
    }

    private boolean compararme(int fil, int col){
        return fila == fil && columna == col;
    }

    @Override
    public boolean equals(Object otro) {
        if (!(otro instanceof Posicion)) {
            return false;
        }

        return esIgualA((Posicion) otro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }
}