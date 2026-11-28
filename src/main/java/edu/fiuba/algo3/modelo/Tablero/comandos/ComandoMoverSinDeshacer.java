package edu.fiuba.algo3.modelo.tablero.comandos;

import edu.fiuba.algo3.modelo.tablero.excepciones.MovimientoNoPermitidoException;

public class ComandoMoverSinDeshacer implements Comando {

    @Override
    public void ejecutar() {
    }

    @Override
    public void deshacer() {
        throw new MovimientoNoPermitidoException("No se puede deshacer el movimiento más de una vez por turno.");
    }

    @Override
    public Comando registrarEnTurno(Comando nuevoMovimiento) {
        return this;
    }
}