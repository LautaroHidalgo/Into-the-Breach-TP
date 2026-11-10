package edu.fiuba.algo3.modelo.unidades;

public interface EstadoSalud {

    int calcularDanio(int danioBase);

    EstadoSalud contaminar();
}
