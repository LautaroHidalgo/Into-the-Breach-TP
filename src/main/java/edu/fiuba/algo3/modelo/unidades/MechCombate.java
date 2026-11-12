package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.excepciones.MovimientoNoPermitidoException;
import edu.fiuba.algo3.modelo.excepciones.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class MechCombate implements Unidad {
    private boolean seMovio = false;
    private boolean ataco = false;

    @Override
    public void registrarMovimiento() {
        if (seMovio) {
            throw new MovimientoNoPermitidoException();
        }
        seMovio = true;
    }

    public void atacar(Posicion origen, Posicion destino, Tablero tablero) {
        if (ataco) {
            throw new AccionNoPermitidaException();
        }
        ataco = true;
    }

    @Override
    public void deshacerMovimiento() {
        this.seMovio = false;
    }
}