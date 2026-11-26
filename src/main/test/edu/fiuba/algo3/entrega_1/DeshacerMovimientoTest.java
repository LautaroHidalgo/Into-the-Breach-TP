package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.Tablero.*;

import java.io.File;

public class DeshacerMovimientoTest {

    @Test
    public void noSePuedeDeshacerUnMovimientoDespuesDeQueElMechAtaco() {
        // ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaParaMovimiento.csv");
        Tablero tablero = new Tablero(mapa);
        Posicion origen = new Posicion(1, 2);
        Posicion destino = new Posicion(1, 3);
        Posicion posVek = new Posicion(1, 4);

        MechCombate mech = new MechCombate();
        Saltador vek = new Saltador();

        // ACT
        tablero.posicionarUnidad(origen, mech);
        tablero.posicionarUnidad(posVek, vek);

        tablero.moverUnidad(origen, destino);
        mech.atacar(destino, posVek, tablero);

        // ASSERT
        assertThrows(
                AccionNoPermitidaException.class,
                () -> tablero.deshacerMovimiento(destino),
                "El tablero debería rechazar deshacer el movimiento si el Mech ya atacó"
        );
    }

    @Test
    public void noSePuedeDeshacerUnMovimientoMasDeUnaVezPorTurno() {
        // ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaParaMovimiento.csv");
        Tablero tablero = new Tablero(mapa);
        Posicion origen = new Posicion(1, 2);
        Posicion primerDestino = new Posicion(1, 3);
        Posicion segundoDestino = new Posicion(1, 4);

        MechCombate mech = new MechCombate();
        tablero.posicionarUnidad(origen, mech);

        // ACT
        tablero.moverUnidad(origen, primerDestino);
        tablero.deshacerMovimiento(primerDestino);
        tablero.moverUnidad(origen, segundoDestino);

        // ASSERT
        assertThrows(
                MovimientoNoPermitidoException.class,
                () -> tablero.deshacerMovimiento(segundoDestino),
                "No deberia deshacer un movimiento más de una vez por turno."
        );
    }

    @Test
    public void dosMechsDistintosNoPuedenDeshacerSuMovimientoEnElMismoTurno() {
        File mapa = new File("src/test/resources/mapas/mapaParaMovimiento.csv");
        Tablero tablero = new Tablero(mapa);
        Posicion origenMech1 = new Posicion(1, 2);
        Posicion destinoMech1 = new Posicion(1, 3);
        Posicion origenMech2 = new Posicion(2, 2);
        Posicion destinoMech2 = new Posicion(2, 3);

        MechCombate mech1 = new MechCombate();
        MechCanon mech2 = new MechCanon();
        tablero.posicionarUnidad(origenMech1, mech1);
        tablero.posicionarUnidad(origenMech2, mech2);

        tablero.moverUnidad(origenMech1, destinoMech1);
        tablero.moverUnidad(origenMech2, destinoMech2);
        tablero.deshacerMovimiento(destinoMech1);

        assertThrows(
                MovimientoNoPermitidoException.class,
                () -> tablero.deshacerMovimiento(destinoMech2),
                "Un segundo Mech no debería poder deshacer si otro Mech ya deshizo en el mismo turno."
        );
    }

}