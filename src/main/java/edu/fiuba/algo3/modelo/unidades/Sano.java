package edu.fiuba.algo3.modelo.unidades;

public class Sano implements EstadoSalud {

    @Override
    public int calcularDanio(int danioBase) {
        return danioBase;
    }

    @Override
    public EstadoSalud contaminar() {
        return new Contaminado();
    }
}
