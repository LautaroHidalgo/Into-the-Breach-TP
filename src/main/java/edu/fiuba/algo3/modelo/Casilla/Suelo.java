package edu.fiuba.algo3.modelo.Casilla;

import edu.fiuba.algo3.modelo.unidades.Unidad;

/**
 * Suelo
 */
public class Suelo extends Terreno{
    public boolean esTransitable() {
        return true;
    }

    @Override
    public boolean bloqueaDisparos() {
        return false;
    }

    @Override
    public void recibirUnidadEmpujada(Unidad empujada, Casilla origen, Casilla destino) {
        origen.liberar();
        destino.recibirUnidad(empujada);
    }
}
