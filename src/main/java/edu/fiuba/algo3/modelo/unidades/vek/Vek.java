package edu.fiuba.algo3.modelo.unidades.vek;

import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.armas.Arma;
import edu.fiuba.algo3.modelo.unidades.UnidadDeCombate;
import edu.fiuba.algo3.modelo.unidades.desplazamiento.Desplazamiento;

public abstract class Vek extends UnidadDeCombate {
    private Direccion direccionAtaque;

    protected Vek(int vida, int movimiento, Arma arma, Desplazamiento desplazamiento) {
        super(vida, movimiento, arma, desplazamiento);
    }

    @Override
    public void caerAlAgua() {
        morir();
    }

    public void anunciarAtaque(Direccion direccion) {
        this.direccionAtaque = direccion;
    }

    public void ejecutarAtaque(Tablero tablero) {
        if (estaViva()) {
            Posicion objetivo = posicion.obtenerAdyacente(direccionAtaque);
            atacar(posicion, objetivo, tablero);
        }
    }
}
