package edu.fiuba.algo3.modelo.Tablero.excepciones;

public class TableroInvalidoException extends IllegalArgumentException {

    public TableroInvalidoException(String mensaje) {
        super(mensaje);
    }
}