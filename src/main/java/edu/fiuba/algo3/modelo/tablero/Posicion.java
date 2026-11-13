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

    public Posicion derecha(){
        if (columna == 7){return null;}
        return new Posicion(fila,columna+1);
    }

    public Posicion izquierda(){
        if (columna == 0){return null;}
        return new Posicion(fila,columna-1);
    }

    public Posicion arriba(){
        if (fila == 0){return null;}
        return new Posicion(fila-1,columna);
    }

    public Posicion abajo(){
        if (fila == 7){return null;}
        return new Posicion(fila+1,columna);
    }

    // no chequea los bordes eso lo hace el tablero
    public Posicion obtenerAdyacente(Direccion direccion){
        return direccion.desplazar(fila , columna);
    }

    //misma fila o misma columna pero no la misma posición
    public boolean estaAlineadaCon(Posicion otra) {
        return otra.alineadaConCoordenadas(fila, columna);
    }

    private boolean alineadaConCoordenadas(int fil, int col) {
        return (fila == fil) != (columna == col);
    }

    public Direccion direccionHacia(Posicion otra) {
        return otra.direccionDesde(fila, columna);
    }

    private Direccion direccionDesde(int fil, int col) {
        return Direccion.entre(fil, col, fila, columna);
    }

    @Override
    public String toString() {
        return "(" + fila + ", " + columna + ")";
    }
}