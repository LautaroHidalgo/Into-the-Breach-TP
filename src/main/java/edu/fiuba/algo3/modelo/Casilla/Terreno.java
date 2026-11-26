package edu.fiuba.algo3.modelo.Casilla;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Terreno {

    public boolean esIgual(Terreno otroTerreno) {
        return this.getClass() == otroTerreno.getClass();
    }

    public boolean esTransitable() {
        return false;
    }


    public void recibirAtaque(int danio) {
    }

    public boolean bloqueaDisparos() {
        return true;
    }


    public void recibirUnidadEmpujada(Unidad empujada, Casilla origen, Casilla destino) {
        empujada.recibirDanio(Unidad.DANIO_COLISION);
    }

    public void aplicarEfecto(Unidad unidad) {
    }

    public boolean permiteAtacar(Unidad unidad) {
        return true;
    }
}

