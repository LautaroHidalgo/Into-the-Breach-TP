package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Casilla.RedElectrica;
import edu.fiuba.algo3.modelo.Tablero.MovimientoNoPermitidoException;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.PosicionFueraDelTableroException;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Avispon;
import edu.fiuba.algo3.modelo.unidades.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.MechCombate;
import edu.fiuba.algo3.modelo.unidades.Saltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MovimientoMechTest {

    private Tablero tablero;
    private MechCombate mech;
    private final Posicion origen = new Posicion(2, 3);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/test/resources/mapas/mapaParaMovimiento.csv"), new RedElectrica());
        mech = new MechCombate();
        tablero.posicionarUnidad(origen, mech);
    }

    @Test
    public void elMechSeMueveAUnaCasillaAlcanzable() {
        tablero.moverUnidad(origen, new Posicion(1, 4));

        assertTrue(tablero.unidadEstaEn(mech, new Posicion(1, 4)));
        assertFalse(tablero.estaOcupada(origen));
    }

    @Test
    public void elMechNoPuedeMoverseMasLejosQueSuRango() {
        assertThrows(MovimientoNoPermitidoException.class,
                () -> tablero.moverUnidad(origen, new Posicion(5, 3)));
        assertTrue(tablero.unidadEstaEn(mech, origen));
    }

    @Test
    public void elMechNoPuedeMoverseAUnaMontania() {
        assertThrows(MovimientoNoPermitidoException.class,
                () -> tablero.moverUnidad(origen, new Posicion(1, 1)));
    }

    @Test
    public void elMechNoPuedeMoverseEncimaDeOtraUnidad() {
        Saltador vek = new Saltador();
        tablero.posicionarUnidad(new Posicion(1, 3), vek);

        assertThrows(MovimientoNoPermitidoException.class,
                () -> tablero.moverUnidad(origen, new Posicion(1, 3)));
        assertTrue(tablero.unidadEstaEn(vek, new Posicion(1, 3)));
        assertTrue(tablero.unidadEstaEn(mech, origen));
    }

    @Test
    public void elMechNoPuedeMoverseFueraDelTablero() {
        assertThrows(MovimientoNoPermitidoException.class,
                () -> tablero.moverUnidad(origen, new Posicion(-1, 2)));
    }

    @Test
    public void unMovimientoInvalidoNoGastaElMovimientoDelTurno() {
        assertThrows(MovimientoNoPermitidoException.class,
                () -> tablero.moverUnidad(origen, new Posicion(1, 1)));

        tablero.moverUnidad(origen, new Posicion(1, 3));

        assertTrue(tablero.unidadEstaEn(mech, new Posicion(1, 3)));
    }

    @Test
    public void deshacerDevuelveAlMechExactamenteASuPosicionAnterior() {
        tablero.moverUnidad(origen, new Posicion(1, 4));

        tablero.deshacerMovimiento(new Posicion(1, 4));

        assertTrue(tablero.unidadEstaEn(mech, origen));
        assertFalse(tablero.estaOcupada(new Posicion(1, 4)));
    }

    @Test
    public void noSePuedeDeshacerSiNoHuboMovimiento() {
        assertThrows(MovimientoNoPermitidoException.class,
                () -> tablero.deshacerMovimiento(origen));
    }

    @Test
    public void noSePuedeUbicarUnaUnidadFueraDelTablero() {
        assertThrows(PosicionFueraDelTableroException.class,
                () -> tablero.posicionarUnidad(new Posicion(8, 8), new Saltador()));
    }

    @Test
    public void cadaUnidadSeMueveSegunSuPropioRango() {
        Tablero libre = new Tablero(new File("src/test/resources/mapas/mapaSinObstaculos.csv"), new RedElectrica());
        libre.posicionarUnidad(new Posicion(3, 0), new Luciernaga());
        libre.posicionarUnidad(new Posicion(5, 0), new Avispon());

        assertTrue(libre.movimientosValidosDesde(new Posicion(3, 0)).contains(new Posicion(3, 2)));
        assertFalse(libre.movimientosValidosDesde(new Posicion(3, 0)).contains(new Posicion(3, 3)));
        assertTrue(libre.movimientosValidosDesde(new Posicion(5, 0)).contains(new Posicion(5, 4)));
        assertFalse(libre.movimientosValidosDesde(new Posicion(5, 0)).contains(new Posicion(5, 5)));
    }
}
