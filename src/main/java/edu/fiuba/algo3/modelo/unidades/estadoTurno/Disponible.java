package edu.fiuba.algo3.modelo.unidades.estadoTurno;
import edu.fiuba.algo3.modelo.Tablero.excepciones.MovimientoNoPermitidoException;

public class Disponible extends EstadoTurno {
    @Override
    public EstadoTurno registrarMovimiento() {
        return new Movido();
    }
    @Override
    public EstadoTurno deshacerMovimiento() {
        throw new MovimientoNoPermitidoException("No hay ningún movimiento para deshacer");
    }
}