package edu.fiuba.algo3.modelo.tablero.comandos;

import edu.fiuba.algo3.modelo.tablero.excepciones.MovimientoNoPermitidoException;

public class SinMovimiento implements Comando {

    @Override
    public void ejecutar() {
    }

    @Override
    public void deshacer() {
        throw new MovimientoNoPermitidoException("No hay ningún movimiento para deshacer");
    }
}