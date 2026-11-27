package edu.fiuba.algo3.modelo.unidades.estadoTurno;
import edu.fiuba.algo3.modelo.Tablero.excepciones.MovimientoNoPermitidoException;

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