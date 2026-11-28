package edu.fiuba.algo3.modelo.armas;
import edu.fiuba.algo3.modelo.tablero.Posicion;

public class ObjetivoInvalidoException extends RuntimeException {
    public ObjetivoInvalidoException(Posicion objetivo) {
        super("El objetivo " + objetivo + " no esta al alcance del arma");
    }
}
