package edu.fiuba.algo3.modelo.tablero.excepciones;

public class AccionNoPermitidaException extends RuntimeException {
    public AccionNoPermitidaException(String message) {
        super(message);
    }
}
