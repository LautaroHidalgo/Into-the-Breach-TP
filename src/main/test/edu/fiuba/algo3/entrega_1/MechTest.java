package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.MechCombate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.fiuba.algo3.modelo.unidades.*;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.*;

public class MechTest {

    @Test
    public void mechNoPuedeMoverseDosVecesPorTurno() {
        // ARRANGE
        Tablero tablero = new Tablero();
        Posicion origen   = new Posicion(0, 0);
        Posicion destino1 = new Posicion(0, 1);
        Posicion destino2 = new Posicion(0, 2);
        MechCombate mech  = new MechCombate();
        tablero.inicializarUnidad(mech, origen);

        // ACT
        tablero.moverUnidad(origen, destino1);

        // ASSERT
        assertThrows(
                MovimientoNoPermitidoException.class,
                () -> tablero.moverUnidad(destino1, destino2)
        );
    }

    @Test
    public void mechNoPuedeAtacarDosVecesPorTurno() {
        // ARRANGE
        Tablero tablero = new Tablero();
        Posicion posicionMech = new Posicion(0, 0);
        Posicion posicionVek  = new Posicion(0, 1);
        MechCombate mech = new MechCombate();
        Saltador vek = new Saltador();
        tablero.inicializarUnidad(mech, posicionMech);
        tablero.inicializarUnidad(vek, posicionVek);

        // ACT
        mech.atacar(posicionMech, posicionVek, tablero);

        // ASSERT
        assertThrows(
                AccionNoPermitidaException.class,
                () -> mech.atacar(posicionMech, posicionVek, tablero)
        );
    }

    @Test
    public void sePuedeDeshacerMovimientoSiAunNoAtaco() {
        // ARRANGE
        Tablero tablero = new Tablero();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);
        MechCombate mech = new MechCombate();

        tablero.inicializarUnidad(mech, origen);
        tablero.moverUnidad(origen, destino);

        // ACT
        tablero.deshacerMovimiento(destino, origen);

        // ASSERT
        assertFalse(tablero.estaOcupada(destino));
        assertTrue(tablero.estaOcupada(origen));
    }
}