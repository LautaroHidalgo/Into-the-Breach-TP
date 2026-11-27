package edu.fiuba.algo3.modelo.unidades.estadoSalud;

public interface EstadoSalud {

    int calcularDanio(int danioBase);

    EstadoSalud contaminar();
}
