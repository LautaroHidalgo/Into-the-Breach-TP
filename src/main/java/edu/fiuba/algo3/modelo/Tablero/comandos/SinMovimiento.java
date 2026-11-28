package edu.fiuba.algo3.modelo.Tablero.comandos;

import edu.fiuba.algo3.modelo.Tablero.MovimientoNoPermitidoException;

public class SinMovimiento implements Comando {

    @Override
    public void ejecutar() {
    }

    @Override
    public void deshacer() {
        throw new MovimientoNoPermitidoException("No hay ningún movimiento para deshacer");
    }
}