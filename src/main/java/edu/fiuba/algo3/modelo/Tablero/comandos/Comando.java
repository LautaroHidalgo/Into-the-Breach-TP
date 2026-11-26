package edu.fiuba.algo3.modelo.Tablero.comandos;

public interface Comando {
    void ejecutar();
    void deshacer();

    default Comando registrarEnTurno(Comando nuevoMovimiento) {
        return nuevoMovimiento;
    }
}