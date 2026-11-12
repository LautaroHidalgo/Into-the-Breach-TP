package edu.fiuba.algo3.modelo.Armas;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public abstract class Arma {
    public abstract void atacar(Posicion posicionMech, Posicion objetivo, Tablero tablero);
}