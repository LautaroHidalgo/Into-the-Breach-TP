package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Casilla.Montania;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.MechCanon;
import edu.fiuba.algo3.modelo.unidades.MechCombate;
import edu.fiuba.algo3.modelo.unidades.Saltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class AtaqueMechTest {

    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/test/resources/mapas/mapaSinObstaculos.csv"));
    }

    @Test
    public void test03ElPunoCausaDosDeDanioAUnaUnidadAdyacenteYLaEmpujaUnaCasillaEnLaDireccionDelGolpe(){
        Posicion posicionMech = new Posicion(3, 3);
        Posicion posicionVek = new Posicion(3, 4);
        MechCombate mech = new MechCombate();
        Luciernaga vek = new Luciernaga();
        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(posicionVek, vek);

        mech.atacar(posicionMech, posicionVek, tablero);

        assertEquals(1, vek.vida());
        assertTrue(tablero.unidadEstaEn(vek, new Posicion(3,5)));
        assertFalse(tablero.unidadEstaEn(mech, posicionVek));
    }

    @Test
    public void test04ElCanonImpactaALaPrimeraUnidadEnLineaRectaYNoALasQueEstanDetras() {
        Posicion posicionMech = new Posicion(3, 0);
        MechCanon mech = new MechCanon();
        Luciernaga primera = new Luciernaga();
        Saltador detras = new Saltador();
        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(new Posicion(3, 3), primera);
        tablero.posicionarUnidad(new Posicion(3, 6), detras);

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
