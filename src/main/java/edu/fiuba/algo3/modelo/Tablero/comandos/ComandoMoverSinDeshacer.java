package edu.fiuba.algo3.modelo.Tablero.comandos;

import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Tablero.MovimientoNoPermitidoException;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class ComandoMoverSinDeshacer implements Comando {
    private final Unidad unidad;
    private final Casilla origen;
    private final Casilla destino;

    public ComandoMoverSinDeshacer(Unidad unidad, Casilla origen, Casilla destino) {
        this.unidad = unidad;
        this.origen = origen;
        this.destino = destino;
    }

    @Override
    public void ejecutar() {
        unidad.registrarMovimiento();
        origen.liberar();
        destino.recibirUnidadQueLlega(unidad);
    }

    @Override
    public void deshacer() {
        throw new MovimientoNoPermitidoException("No se puede deshacer el movimiento más de una vez por turno.");
    }
}