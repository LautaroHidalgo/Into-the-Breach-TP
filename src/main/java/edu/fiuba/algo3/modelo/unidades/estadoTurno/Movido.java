package edu.fiuba.algo3.modelo.unidades.estadoturno;
import edu.fiuba.algo3.modelo.tablero.excepciones.MovimientoNoPermitidoException;

public class Movido extends EstadoTurno {
    @Override
    public EstadoTurno registrarMovimiento() {
        throw new MovimientoNoPermitidoException("La unidad ya se movió en este turno");
    }
    @Override
    public EstadoTurno deshacerMovimiento() {
        return new Disponible();
    }
}

// Se agrega una excepción que se lanza al intentar volver a moverse.