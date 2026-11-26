package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Avispon;
import edu.fiuba.algo3.modelo.unidades.MechCombate;
import edu.fiuba.algo3.modelo.unidades.Saltador;
import org.junit.jupiter.api.Test;

import java.io.File;

public class AtaqueAnunciadoVekTest {

    @Test
    public void test07LosVekAnuncianSuAtaqueYSeEjecutanEnElOrdenAnunciado() {
        // ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestVeks.csv");
        Tablero tablero = new Tablero(mapa);

        Posicion posSaltador = new Posicion(1, 3);
        Posicion posAvispon = new Posicion(1, 4);
        Posicion posMech = new Posicion(0, 4);

        Saltador saltador = new Saltador();
        Avispon avispon = new Avispon();
        MechCombate mech = new MechCombate();

        tablero.posicionarUnidad(posSaltador, saltador);
        tablero.posicionarUnidad(posAvispon, avispon);
        tablero.posicionarUnidad(posMech, mech);

        //ACT
        tablero.anunciarAtaqueVek(saltador, Direccion.DERECHA);
        tablero.anunciarAtaqueVek(avispon, Direccion.ARRIBA);

        //ACT
        tablero.ejecutarAtaquesAnunciados();

        // ASSERT:
        assertEquals(0, avispon.vida());
        assertFalse(avispon.estaViva());
        assertEquals(3, mech.vida());
    }
}