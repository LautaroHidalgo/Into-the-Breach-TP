package edu.fiuba.algo3.modelo.casilla;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class TerrenoTransitable extends Terreno {

    @Override
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
        destino.recibirUnidadQueLlega(empujada);
    }
}