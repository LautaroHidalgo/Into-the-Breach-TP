package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class PunoTest {

    private Tablero tablero;
    private Puno puno;
    private final Posicion origen = new Posicion(3, 3);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/test/resources/mapas/mapaSinObstaculos.csv"));
        puno = new Puno();
    }

    @Test
    public void noPuedeGolpearUnaCasillaQueNoEsAdyacente() {
        assertThrows(ObjetivoInvalidoException.class,
                () -> puno.atacar(tablero, origen, new Posicion(3, 5)));
    }

    @Test
    public void noPuedeGolpearEnDiagonal() {
        assertThrows(ObjetivoInvalidoException.class,
                () -> puno.atacar(tablero, origen, new Posicion(4, 4)));
    }

    @Test
    public void empujaHaciaArribaSiElGolpeEsHaciaArriba() {
        Luciernaga vek = new Luciernaga();
        tablero.posicionarUnidad(new Posicion(2, 3), vek);

        puno.atacar(tablero, origen, new Posicion(2, 3));

        assertEquals(1, vek.vida());
        assertTrue(tablero.unidadEstaEn(vek, new Posicion(1, 3)));
    }

    @Test
    public void unaUnidadContaminadaRecibeElDobleDeDanio() {
        Luciernaga vek = new Luciernaga();
        vek.recibirContaminacion();
        tablero.posicionarUnidad(new Posicion(3, 4), vek);

        puno.atacar(tablero, origen, new Posicion(3, 4));

        assertFalse(vek.estaViva());
    }

    @Test
    public void golpearUnaCasillaVaciaNoTieneEfecto() {
        assertDoesNotThrow(() -> puno.atacar(tablero, origen, new Posicion(3, 4)));
    }

    @Test
    public void siLaUnidadEstaEnElBordeElGolpeLaDaniaPeroNoLaMueve() {
        Luciernaga vek = new Luciernaga();
        tablero.posicionarUnidad(new Posicion(3, 7), vek);

        puno.atacar(tablero, new Posicion(3, 6), new Posicion(3, 7));

        assertEquals(1, vek.vida());
        assertTrue(tablero.unidadEstaEn(vek, new Posicion(3, 7)));
    }
}
