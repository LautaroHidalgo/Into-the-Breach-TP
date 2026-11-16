package edu.fiuba.algo3.modelo.unidades.estados;
public class Movido extends EstadoTurno {
    @Override
    public EstadoTurno deshacerMovimiento() {
        return new MovimientoDeshecho();
    }
}