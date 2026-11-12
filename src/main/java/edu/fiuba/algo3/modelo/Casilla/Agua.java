package edu.fiuba.algo3.modelo.Casilla;

/**
 * Agua
 */
public class Agua extends Terreno{
    public boolean esTransitable() {
        return false;
    }

    @Override
    public boolean bloqueaDisparos() {
        return false;
    }
}
