package edu.fiuba.algo3.modelo.unidades.estados;
import edu.fiuba.algo3.modelo.Tablero.MovimientoNoPermitidoException;

public class MovimientoDeshecho extends EstadoTurno {
    @Override
    public EstadoTurno registrarMovimiento() {
        return new MovidoSinRetorno();
    }

    @Override
    public EstadoTurno deshacerMovimiento() {
        throw new MovimientoNoPermitidoException("No se puede deshacer el movimiento más de una vez por turno.");
    }
}

// Se verifica que no se puede volver a deshacer en el mismo turno.