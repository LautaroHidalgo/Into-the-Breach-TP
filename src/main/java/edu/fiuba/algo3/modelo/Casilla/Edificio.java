package edu.fiuba.algo3.modelo.Casilla;

import edu.fiuba.algo3.modelo.unidades.Unidad;

/**
 * Edificio
 */
public class Edificio extends Terreno{

    private static final int DANIO_A_LA_RED = 1;
    private final RedElectrica redElectrica;
    private boolean estaDestruido;

    public Edificio() {
        this(new RedElectrica());
    }

    public Edificio(RedElectrica redElectrica) {
        this.redElectrica = redElectrica;
        this.estaDestruido = false;
    }

    @Override
    public boolean esTransitable() {
        return false;
    }

    @Override
    public void recibirAtaque(int danio) {
        if (!this.estaDestruido && danio > 0) {
            this.estaDestruido = true;
            this.redElectrica.restarEnergia(DANIO_A_LA_RED);
        }
    }

    @Override
    public void recibirUnidadEmpujada(Unidad empujada, Casilla origen, Casilla destino) {
        super.recibirUnidadEmpujada(empujada, origen, destino);
        this.recibirAtaque(Unidad.DANIO_COLISION);
    }

    public boolean estaDestruido() {
        return this.estaDestruido;
    }
}
