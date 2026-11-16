package edu.fiuba.algo3.modelo.unidades.estados;

public class MovidoSinRetorno extends EstadoTurno {
    // Hereda todo de EstadoTurno:
    // - registrarMovimiento() lanza MovimientoNoPermitidoException
    // - deshacerMovimiento() lanza MovimientoNoPermitidoException ("más de una vez por turno")
    // - registrarAtaque() pasa a Agotado
}
