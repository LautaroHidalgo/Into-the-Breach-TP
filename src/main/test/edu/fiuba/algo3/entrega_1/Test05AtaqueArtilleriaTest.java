package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.armas.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.Montania;
import edu.fiuba.algo3.modelo.casilla.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mech.MechArtilleria;
import edu.fiuba.algo3.modelo.unidades.vek.Luciernaga;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Test05AtaqueArtilleriaTest {

    private Tablero tablero;
    private MechArtilleria mech;
    private final Posicion posicionMech = new Posicion(3, 0);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/main/mapas/mapaSinObstaculos.csv"), new RedElectrica());
        mech = new MechArtilleria();
        tablero.posicionarUnidad(posicionMech, mech);
    }

    @Test
    public void test05LaArtilleriaNopuedeAtacarUnaCasillaAdyacente() {
        assertThrows(ObjetivoInvalidoException.class, () -> 
            mech.atacar(posicionMech, new Posicion(3,1), tablero));
        assertThrows(ObjetivoInvalidoException.class, () -> 
            mech.atacar(posicionMech, new Posicion(2,0), tablero));
        assertThrows(ObjetivoInvalidoException.class, () -> 
            mech.atacar(posicionMech, new Posicion(4,0), tablero));
    }

    @Test
    public void test05BLaArtilleriaAtacaPorEncimaDeObstaculosDaniaElObjetivoYEmpujaHaciaAfueraALasAdyacentes(){
        Posicion centro = new Posicion(3, 4);
        Posicion posicionArriba = centro.obtenerAdyacente(Direccion.ARRIBA);
        Posicion posicionAbajo = centro.obtenerAdyacente(Direccion.ABAJO);
        Posicion posicionIzquierda = centro.obtenerAdyacente(Direccion.IZQUIERDA);
        Posicion posicionDerecha = centro.obtenerAdyacente(Direccion.DERECHA);

        Luciernaga luciernagaEnElCentro = new Luciernaga();
        Luciernaga luciernagaArriba = new Luciernaga();
        Luciernaga luciernagaAbajo = new Luciernaga();
        Luciernaga luciernagaIzquierda = new Luciernaga();
        Luciernaga luciernagaDerecha = new Luciernaga();

        tablero.agregarCasilla(new Posicion(3, 1), new Casilla(new Montania()));

        tablero.posicionarUnidad(centro, luciernagaEnElCentro);
        tablero.posicionarUnidad(posicionArriba, luciernagaArriba);
        tablero.posicionarUnidad(posicionAbajo, luciernagaAbajo);
        tablero.posicionarUnidad(posicionIzquierda, luciernagaIzquierda);
        tablero.posicionarUnidad(posicionDerecha, luciernagaDerecha);

        mech.atacar(posicionMech, centro, tablero);

        assertEquals(2, luciernagaEnElCentro.vida());
        assertTrue(tablero.unidadEstaEn(luciernagaEnElCentro, centro));
        assertTrue(tablero.unidadEstaEn(luciernagaArriba, posicionArriba.obtenerAdyacente(Direccion.ARRIBA)));
        assertTrue(tablero.unidadEstaEn(luciernagaAbajo, posicionAbajo.obtenerAdyacente(Direccion.ABAJO)));
        assertTrue(tablero.unidadEstaEn(luciernagaIzquierda, posicionIzquierda.obtenerAdyacente(Direccion.IZQUIERDA)));
        assertTrue(tablero.unidadEstaEn(luciernagaDerecha, posicionDerecha.obtenerAdyacente(Direccion.DERECHA)));
    }
}
