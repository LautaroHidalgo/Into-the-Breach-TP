package edu.fiuba.algo3.modelo.Tablero;

public class PosicionFueraDelTableroException extends RuntimeException {
    public PosicionFueraDelTableroException(Posicion posicion) {
        super("La posicion" + posicion + " esta fuera del tablero");
    }
}
