package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.casilla.Agua;
import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.excepciones.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.unidades.mech.MechArtilleria;
import edu.fiuba.algo3.modelo.unidades.mech.MechCombate;
import edu.fiuba.algo3.modelo.unidades.vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.vek.Saltador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class AguaTest {

    // Mapa sin obstáculos con Agua en (3,5) la Artillería en (3,0) dispara a (3,3) y empuja
    // hacia la derecha sin dañarla a la unidad de (3,4) que termina en el Agua
    private Tablero tablero;
    private MechArtilleria artilleria;
    private final Posicion posicionArtilleria = new Posicion(3, 0);
    private final Posicion objetivoArtilleria = new Posicion(3, 3);
    private final Posicion vecinaDelObjetivo = new Posicion(3, 4);
    private final Posicion agua = new Posicion(3, 5);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/main/mapas/mapaSinObstaculos.csv"), new RedElectrica());
        tablero.agregarCasilla(agua, new Casilla(new Agua()));
        artilleria = new MechArtilleria();
        tablero.posicionarUnidad(posicionArtilleria, artilleria);
    }

    @Test
    public void test05UnVekTerrestreEmpujadoAlAguaMuere() {
        Saltador saltador = new Saltador();
        tablero.posicionarUnidad(vecinaDelObjetivo, saltador);

        artilleria.atacar(posicionArtilleria, objetivoArtilleria, tablero);

        assertTrue(tablero.unidadEstaEn(saltador, agua));
        assertFalse(saltador.estaViva());
    }

    @Test
    public void test05ElAvisponEmpujadoAlAguaNoMuere() {
        Avispon avispon = new Avispon();
        tablero.posicionarUnidad(vecinaDelObjetivo, avispon);

        artilleria.atacar(posicionArtilleria, objetivoArtilleria, tablero);

        assertTrue(tablero.unidadEstaEn(avispon, agua));
        assertTrue(avispon.estaViva());
        assertEquals(2, avispon.vida());
    }

    @Test
    public void test05UnMechEmpujadoAlAguaNoMuerePeroNoPuedeAtacar() {
        MechCombate mech = new MechCombate();
        Saltador vek = new Saltador();
        tablero.posicionarUnidad(vecinaDelObjetivo, mech);
        tablero.posicionarUnidad(new Posicion(3, 6), vek);

        artilleria.atacar(posicionArtilleria, objetivoArtilleria, tablero);

        assertTrue(tablero.unidadEstaEn(mech, agua));
        assertEquals(3, mech.vida());
        assertThrows(AccionNoPermitidaException.class,
                () -> mech.atacar(agua, new Posicion(3, 6), tablero));
        assertEquals(2, vek.vida());
    }

    @Test
    public void test05UnMechQueSeMueveAlAguaNoPuedeAtacar() {
        MechCombate mech = new MechCombate();
        tablero.posicionarUnidad(vecinaDelObjetivo, mech);

        tablero.moverUnidad(vecinaDelObjetivo, agua);

        assertThrows(AccionNoPermitidaException.class,
                () -> mech.atacar(agua, new Posicion(3, 6), tablero));
    }

    @Test
    public void intentarAtacarDesdeElAguaNoGastaElAtaqueDelTurno() {
        MechCombate mech = new MechCombate();
        Saltador vek = new Saltador();
        tablero.posicionarUnidad(agua, mech);
        tablero.posicionarUnidad(new Posicion(2, 6), vek);

        assertThrows(AccionNoPermitidaException.class,
                () -> mech.atacar(agua, new Posicion(3, 6), tablero));
        tablero.moverUnidad(agua, new Posicion(2, 5));
        mech.atacar(new Posicion(2, 5), new Posicion(2, 6), tablero);

        assertFalse(vek.estaViva());
    }

    @Test
    public void elAvisponPuedeAtacarDesdeElAgua() {
        Avispon avispon = new Avispon();
        Saltador vek = new Saltador();
        tablero.posicionarUnidad(agua, avispon);
        tablero.posicionarUnidad(new Posicion(3, 6), vek);

        avispon.atacar(agua, new Posicion(3, 6), tablero);

        assertEquals(1, vek.vida());
    }
}
