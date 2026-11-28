package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.Montania;
import edu.fiuba.algo3.modelo.casilla.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mech.MechCanon;
import edu.fiuba.algo3.modelo.unidades.vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.vek.Saltador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Test04ElCanonImpactaALaPrimeraUnidadEnLineaRecta {

    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/main/mapas/mapaSinObstaculos.csv"), new RedElectrica());
    }

    @Test
    public void test04ElCanonImpactaALaPrimeraUnidadEnLineaRectaYNoALasQueEstanDetras() {
        Posicion posicionMech = new Posicion(3, 0);
        MechCanon mech = new MechCanon();
        Posicion posicionLuciernaga = new Posicion(3, 3);
        Luciernaga primera = new Luciernaga();
        Posicion posicionSaltador = new Posicion(3, 6);
        Saltador detras = new Saltador();

        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(posicionLuciernaga, primera);
        tablero.posicionarUnidad(posicionSaltador, detras);

        mech.atacar(posicionMech, new Posicion(3, 7), tablero);

        assertEquals(2, primera.vida());
        assertTrue(tablero.unidadEstaEn(primera, new Posicion(3, 4)));
        assertEquals(2, detras.vida());
        assertTrue(tablero.unidadEstaEn(detras, new Posicion(3, 6)));
    }

    @Test
    public void test04BElCanonImpactaAlPrimerObstaculoEnLineaRectaYNoALasUnidadesDetras() {
        Posicion posicionMech = new Posicion(3, 0);
        MechCanon mech = new MechCanon();
        Luciernaga detras = new Luciernaga();
        tablero.posicionarUnidad(posicionMech, mech);
        tablero.agregarCasilla(new Posicion(3, 2), new Casilla(new Montania()));
        tablero.posicionarUnidad(new Posicion(3, 4), detras);

        mech.atacar(posicionMech, new Posicion(3, 4), tablero);

        assertEquals(3, detras.vida());
        assertTrue(tablero.unidadEstaEn(detras, new Posicion(3, 4)));
    }
}
