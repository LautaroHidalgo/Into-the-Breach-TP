package edu.fiuba.algo3.modelo.unidades.estadoTurno;

import edu.fiuba.algo3.modelo.unidades.estadoSalud.Agotado;

public abstract class EstadoTurno {

    public abstract EstadoTurno registrarMovimiento();

    public abstract EstadoTurno deshacerMovimiento();

    public void verificarQuePuedeAtacar() {
    }

    public EstadoTurno registrarAtaque() {
        return new Agotado();
    }
}

// registrarMovimiento y deshacerMovimiento pasan a ser abstractos para que cada estado
// defina su propia transición, dejando solo el comportamiento comun de ataque.