package edu.fiuba.algo3.modelo.unidades;

public class Terrestre implements Desplazamiento {

    @Override
    public void terminarEnAcido(Unidad unidad) {
        unidad.recibirContaminacion();
    }

    @Override
    public void terminarEnAgua(Unidad unidad) {
        unidad.caerAlAgua();
    }

    @Override
    public boolean puedeAtacarDesdeElAgua() {
        return false;
    }
}