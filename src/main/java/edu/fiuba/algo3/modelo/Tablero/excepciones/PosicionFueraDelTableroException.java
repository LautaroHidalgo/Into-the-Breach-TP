package edu.fiuba.algo3.modelo.tablero.excepciones;

import edu.fiuba.algo3.modelo.tablero.Posicion;

public class PosicionFueraDelTableroException extends RuntimeException {
    public PosicionFueraDelTableroException(Posicion posicion) {
        super("La posicion" + posicion + " esta fuera del tablero");
    }
}
