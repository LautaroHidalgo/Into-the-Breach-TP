package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class Arma {

    protected final int danio;

    protected Arma(int danio) {
        this.danio = danio;
    }

    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (!tablero.estaDentro(objetivo) || !alcanza(origen, objetivo)) {
            throw new ObjetivoInvalidoException(objetivo);
        }
        disparar(tablero, origen, objetivo, origen.direccionHacia(objetivo));
    }

    protected abstract boolean alcanza(Posicion origen, Posicion objetivo);

    protected abstract void disparar(Tablero tablero, Posicion origen, Posicion objetivo, Direccion direccion);
}
