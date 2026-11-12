package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Casilla.Casilla;

public class SinUnidad extends Unidad{

    public SinUnidad(){
        super(0, 0);
    }

    @Override
    public boolean ocupaCasilla(){
        return false;
    }

    @Override
    public void recibirDanio(int cantidad){
        // No hay nadie que reciba el daño.
    }

    @Override
    public void serEmpujada(Casilla origen, Casilla destino) {
        // No hay nadie a quien empujar.
    }

    @Override
    public void chocarCon(Unidad empujada, Casilla origen, Casilla destino) {
        destino.dejarPasar(empujada, origen);
    }
}

