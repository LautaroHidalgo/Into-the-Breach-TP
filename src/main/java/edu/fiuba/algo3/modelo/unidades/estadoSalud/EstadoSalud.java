package edu.fiuba.algo3.modelo.unidades.estadosalud;

public interface EstadoSalud {

    int calcularDanio(int danioBase);

    EstadoSalud contaminar();
}
