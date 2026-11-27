package edu.fiuba.algo3.modelo.Tablero.excepciones;

import edu.fiuba.algo3.modelo.Tablero.Posicion;

public class PosicionFueraDelTableroException extends RuntimeException {
    public PosicionFueraDelTableroException(Posicion posicion) {
        super("La posicion" + posicion + " esta fuera del tablero");
    }
}
