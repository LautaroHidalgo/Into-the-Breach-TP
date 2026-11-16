package edu.fiuba.algo3.modelo.unidades.estados;
import edu.fiuba.algo3.modelo.Tablero.MovimientoNoPermitidoException;

public class MovidoSinRetorno extends EstadoTurno {
    @Override
    public EstadoTurno registrarMovimiento() {
        throw new MovimientoNoPermitidoException("La unidad ya se movió en este turno");
    }
    @Override
    public EstadoTurno deshacerMovimiento() {
        throw new MovimientoNoPermitidoException("No se puede deshacer el movimiento más de una vez por turno.");
    }
}

// define explicitamente que no permite volver a moverse ni deshacer nuevamente