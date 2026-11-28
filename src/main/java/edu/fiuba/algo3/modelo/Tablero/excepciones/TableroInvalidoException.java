package edu.fiuba.algo3.modelo.tablero.excepciones;

public class TableroInvalidoException extends IllegalArgumentException {

    public TableroInvalidoException(String mensaje) {
        super(mensaje);
    }
}