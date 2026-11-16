package edu.fiuba.algo3.modelo.Casilla;

import edu.fiuba.algo3.modelo.unidades.Unidad;

/**
 * Terreno
 */
public class Terreno {

    public boolean esIgual(Terreno otroTerreno) {
        return this.getClass() == otroTerreno.getClass();
    }

    public boolean esTransitable() {
        return false;
    }

    //cambio: por defecto atacar el terreno no tiene efecto
    public void recibirAtaque(int danio) {
    }

    //por defecto el terreno es un obstáculo para los disparos
    public boolean bloqueaDisparos() {
        return true;
    }

    // por defecto el terreno es un obstaculo entonces la unidad choca recibe daño y no se mueve
    public void recibirUnidadEmpujada(Unidad empujada, Casilla origen, Casilla destino) {
        empujada.recibirDanio(Unidad.DANIO_COLISION);
    }
}
