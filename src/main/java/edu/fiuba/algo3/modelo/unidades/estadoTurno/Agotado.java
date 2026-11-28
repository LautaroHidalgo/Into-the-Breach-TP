package edu.fiuba.algo3.modelo.unidades.estadoturno;
import edu.fiuba.algo3.modelo.tablero.excepciones.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.tablero.excepciones.MovimientoNoPermitidoException;

public class Agotado extends EstadoTurno {
    @Override
    public EstadoTurno registrarMovimiento() {
        throw new MovimientoNoPermitidoException("La unidad ya se movió en este turno");
    }
    @Override
    public EstadoTurno deshacerMovimiento() {
        throw new AccionNoPermitidaException("No se puede deshacer el movimiento después de atacar");
    }
    @Override
    public void verificarQuePuedeAtacar() {
        throw new AccionNoPermitidaException("La unidad ya atacó en este turno");
    }
    @Override
    public EstadoTurno registrarAtaque() {
        throw new AccionNoPermitidaException("La unidad ya atacó en este turno");
    }
}

// Se bloquean claramente todas las acciones (mover, deshacer y atacar).