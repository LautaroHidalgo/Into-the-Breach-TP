package edu.fiuba.algo3.modelo.Tablero.excepciones;

public class MovimientoNoPermitidoException extends RuntimeException {
    public MovimientoNoPermitidoException(String message) {
        super(message);
    }
}
