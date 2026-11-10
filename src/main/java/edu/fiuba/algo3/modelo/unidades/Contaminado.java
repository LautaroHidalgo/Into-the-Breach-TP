package edu.fiuba.algo3.modelo.unidades;

public class Contaminado implements EstadoSalud {

    public static final int MULTIPLICADOR_DANIO = 2;

    @Override
    public int calcularDanio(int danioBase) {
        return danioBase * MULTIPLICADOR_DANIO;
    }

    @Override
    public EstadoSalud contaminar() {
        return this;
    }
}
