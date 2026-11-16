package edu.fiuba.algo3.modelo.unidades.estados;

import edu.fiuba.algo3.modelo.Tablero.MovimientoNoPermitidoException;

public abstract class EstadoTurno {

    public EstadoTurno registrarMovimiento() {
        throw new MovimientoNoPermitidoException("La unidad ya se movió en este turno");
    }

    public EstadoTurno deshacerMovimiento() {
        throw new MovimientoNoPermitidoException("No se puede deshacer el movimiento más de una vez por turno.");
    }

    public void verificarQuePuedeAtacar() {
    }

    public EstadoTurno registrarAtaque() {
        return new Agotado();
    }
}