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
                () -> tablero.deshacerMovimiento(destino, origen),
                "El tablero debería rechazar deshacer el movimiento si el Mech ya atacó"
        );
    }
}