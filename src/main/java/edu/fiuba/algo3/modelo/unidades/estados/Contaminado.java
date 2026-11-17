package edu.fiuba.algo3.modelo.unidades.estados;

public class Contaminado implements EstadoSalud {

    private static final int MULTIPLICADOR_DANIO = 2;

    @Override
    public int calcularDanio(int danioBase) {
        return danioBase * MULTIPLICADOR_DANIO;
    }

    @Override
    public EstadoSalud contaminar() {
        return this;
    }
}
