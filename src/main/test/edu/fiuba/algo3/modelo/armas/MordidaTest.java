package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Casilla.RedElectrica;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MordidaTest {

    private Tablero tablero;
    private Mordida arma;
    private final Posicion origen = new Posicion(3, 3);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/main/mapas/mapaSinObstaculos.csv"), new RedElectrica());
        arma = new Mordida();
    }

    @Test
    public void daniaALaUnidadAdyacenteSinEmpujarla() {
        Luciernaga vek = new Luciernaga();
        tablero.posicionarUnidad(new Posicion(3, 4), vek);

        arma.atacar(tablero, origen, new Posicion(3, 4));

        assertEquals(1, vek.vida());
        assertTrue(tablero.unidadEstaEn(vek, new Posicion(3, 4)));
    }

    @Test
    public void noPuedeAtacarUnaCasillaQueNoEsAdyacente() {
        assertThrows(ObjetivoInvalidoException.class,
                () -> arma.atacar(tablero, origen, new Posicion(3, 5)));
    }
}
