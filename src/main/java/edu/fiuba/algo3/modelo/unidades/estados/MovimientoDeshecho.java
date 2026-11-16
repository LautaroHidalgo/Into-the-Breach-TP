package edu.fiuba.algo3.modelo.unidades.estados;

public class MovimientoDeshecho extends EstadoTurno {
    @Override
    public EstadoTurno registrarMovimiento() {
        return new MovidoSinRetorno();
    }
}
