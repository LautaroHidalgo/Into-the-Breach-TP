package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.armas.Arma;
import edu.fiuba.algo3.modelo.unidades.desplazamiento.Desplazamiento;

public abstract class UnidadDeCombate extends Unidad {

    private final Arma arma;

    protected UnidadDeCombate(int vida, int movimiento, Arma arma, Desplazamiento desplazamiento) {
        super(vida, movimiento, desplazamiento);
        this.arma = arma;
    }

    // Valida el estado del turno y la casilla por ejemplo el Agua antes de disparar
    // recién después de disparar se registra el ataque.
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {
        verificarQuePuedeAtacar();
        tablero.verificarQueSePuedeAtacarDesde(origen);
        arma.atacar(tablero, origen, objetivo);
        registrarAtaque();
    }
}
