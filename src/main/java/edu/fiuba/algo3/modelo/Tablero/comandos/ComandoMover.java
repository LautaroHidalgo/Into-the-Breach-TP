package edu.fiuba.algo3.modelo.tablero.comandos;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.estadosalud.EstadoSalud;

public class ComandoMover implements Comando {
    private final Unidad unidad;
    private final Casilla origen;
    private final Casilla destino;
    private final EstadoSalud estadoSaludPrevio;

    public ComandoMover(Unidad unidad, Casilla origen, Casilla destino, EstadoSalud estadoSaludPrevio) {
        this.unidad = unidad;
        this.origen = origen;
        this.destino = destino;
        this.estadoSaludPrevio = estadoSaludPrevio;
    }

    @Override
    public void ejecutar() {
        unidad.registrarMovimiento();
        origen.liberar();
        destino.recibirUnidadQueLlega(unidad);
    }

    @Override
    public void deshacer() {
        unidad.deshacerMovimiento(estadoSaludPrevio);
        destino.liberar();
        origen.recibirUnidad(unidad);
    }
}
