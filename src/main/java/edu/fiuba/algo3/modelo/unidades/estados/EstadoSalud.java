package edu.fiuba.algo3.modelo.unidades.estados;

public interface EstadoSalud {

    int calcularDanio(int danioBase);

    EstadoSalud contaminar();
}
