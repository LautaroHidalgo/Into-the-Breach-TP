package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Casilla.RedElectrica;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Avispon;
import edu.fiuba.algo3.modelo.unidades.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Saltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


public class AtaqueAdyacenteTest {

    private Tablero tablero;
    private final Posicion origen = new Posicion(3, 3);
    private final Posicion adyacente = new Posicion(3, 4);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/main/mapas/mapaSinObstaculos.csv"), new RedElectrica());
    }

    @Test
    public void daniaALaUnidadAdyacenteSinEmpujarla() {
        Luciernaga vek = new Luciernaga();
        tablero.posicionarUnidad(adyacente, vek);

        new AtaqueAdyacente(1).atacar(tablero, origen, adyacente);

        assertEquals(2, vek.vida());
        assertTrue(tablero.unidadEstaEn(vek, adyacente));
    }

    @Test
    public void noPuedeAtacarUnaCasillaQueNoEsAdyacente() {
        AtaqueAdyacente arma = new AtaqueAdyacente(1);

        assertThrows(ObjetivoInvalidoException.class,
                () -> arma.atacar(tablero, origen, new Posicion(3, 5)));
    }

    @Test
    public void laMordidaDelSaltadorHaceDosDeDanio() {
        Saltador saltador = new Saltador();
        Luciernaga objetivo = new Luciernaga();
        tablero.posicionarUnidad(origen, saltador);
        tablero.posicionarUnidad(adyacente, objetivo);

        saltador.atacar(origen, adyacente, tablero);

        assertEquals(1, objetivo.vida());
        assertTrue(tablero.unidadEstaEn(objetivo, adyacente));
    }

    @Test
    public void laPicaduraDelAvisponHaceUnoDeDanio() {
        Avispon avispon = new Avispon();
        Luciernaga objetivo = new Luciernaga();
        tablero.posicionarUnidad(origen, avispon);
        tablero.posicionarUnidad(adyacente, objetivo);

        avispon.atacar(origen, adyacente, tablero);

        assertEquals(2, objetivo.vida());
        assertTrue(tablero.unidadEstaEn(objetivo, adyacente));
    }
}
