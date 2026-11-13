package edu.fiuba.algo3.modelo.Casilla;

public class RedElectrica {

    private static final int ENERGIA_INICIAL = 5;
    private int energia;

    public RedElectrica() {
        this.energia = ENERGIA_INICIAL;
    }

    public void restarEnergia(int cantidad) {
        this.energia = Math.max(0, this.energia - cantidad);
    }

    public int getEnergia() {
        return this.energia;
    }

    public boolean estaActivo() {
        return this.energia > 0;
    }
}
