package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Casilla.Montania;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.armas.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.unidades.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.MechArtilleria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class AtaqueArtilleriaTest {

    private Tablero tablero;
    private MechArtilleria mech;
    private final Posicion posicionMech = new Posicion(3, 0);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/test/resources/mapas/mapaSinObstaculos.csv"));
        mech = new MechArtilleria();
        tablero.posicionarUnidad(posicionMech, mech);
    }

    @Test
    public void test05LaArtilleriaNopuedeAtacarUnaCasillaAdyacente() {
        assertThrows(ObjetivoInvalidoException.class, () -> mech.atacar(posicionMech, new Posicion(3,1), tablero));

    }

    @Test
    public void test05BLaArtilleriaAtacaPorEncimaDeObstaculosDaniaElObjetivoYEmpujaHaciaAfueraALasAdyacentes(){
        Posicion centro = new Posicion(3, 4);
        Luciernaga enElCentro = new Luciernaga();
        Luciernaga arriba = new Luciernaga();
        Luciernaga abajo = new Luciernaga();
        Luciernaga izquierda = new Luciernaga();
        Luciernaga derecha = new Luciernaga();
        tablero.agregarCasilla(new Posicion(3, 1), new Casilla(new Montania()));
        tablero.posicionarUnidad(centro, enElCentro);
        tablero.posicionarUnidad(new Posicion(2,4), arriba);
        tablero.posicionarUnidad(new Posicion(4,4), abajo);
        tablero.posicionarUnidad(new Posicion(3,3), izquierda);
        tablero.posicionarUnidad(new Posicion(3,5), derecha);

        mech.atacar(posicionMech, centro, tablero);

        assertEquals(2, enElCentro.vida());
        assertTrue(tablero.unidadEstaEn(enElCentro, centro));
        assertTrue(tablero.unidadEstaEn(arriba, new Posicion(1, 4)));
        assertTrue(tablero.unidadEstaEn(abajo, new Posicion(5, 4)));
        assertTrue(tablero.unidadEstaEn(izquierda, new Posicion(3, 2)));
        assertTrue(tablero.unidadEstaEn(derecha, new Posicion(3, 6)));
    }


}
