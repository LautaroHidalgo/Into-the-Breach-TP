package edu.fiuba.algo3.modelo.unidades.estados;

import edu.fiuba.algo3.modelo.Tablero.AccionNoPermitidaException;
public class Agotado extends EstadoTurno {
    @Override
    public EstadoTurno deshacerMovimiento() {
        throw new AccionNoPermitidaException("No se puede deshacer el movimiento después de atacar");
    }
    @Override
    public void verificarQuePuedeAtacar() {
        throw new AccionNoPermitidaException("La unidad ya atacó en este turno");
    }
    @Override
    public EstadoTurno registrarAtaque() {
        throw new AccionNoPermitidaException("La unidad ya atacó en este turno");
    }
}
