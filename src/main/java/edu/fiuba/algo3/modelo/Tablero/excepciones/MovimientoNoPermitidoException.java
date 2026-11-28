package edu.fiuba.algo3.modelo.tablero.excepciones;

public class MovimientoNoPermitidoException extends RuntimeException {
    public MovimientoNoPermitidoException(String message) {
        super(message);
    }
}
