package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.modelo.unidades.MechCombate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.fiuba.algo3.modelo.unidades.*;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;

import java.io.File;

public class MechTest {

    @Test
    public void mechNoPuedeMoverseDosVecesPorTurno() {
        // ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaParaMovimiento.csv");
        Tablero tablero = new Tablero(mapa);
        Posicion origen   = new Posicion(1, 2);
        Posicion destino1 = new Posicion(1, 3);
        Posicion destino2 = new Posicion(1, 4);
        MechCombate mech  = new MechCombate();
        tablero.posicionarUnidad(origen, mech);

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
        File mapa = new File("src/test/resources/mapas/mapaParaMovimiento.csv");
        Tablero tablero = new Tablero(mapa);
        Posicion posicionMech = new Posicion(1, 2);
        Posicion posicionVek  = new Posicion(1, 3);
        MechCombate mech = new MechCombate();
        Saltador vek = new Saltador();
        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(posicionVek, vek);

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
        File mapa = new File("src/test/resources/mapas/mapaParaMovimiento.csv");
        Tablero tablero = new Tablero(mapa);
        Posicion origen = new Posicion(1, 2);
        Posicion destino = new Posicion(1, 3);
        MechCombate mech = new MechCombate();

        tablero.posicionarUnidad(origen, mech);
        tablero.moverUnidad(origen, destino);

        // ACT
        tablero.deshacerMovimiento(destino, origen);

        // ASSERT
        assertFalse(tablero.estaOcupada(destino));
        assertTrue(tablero.estaOcupada(origen));
    }
}
