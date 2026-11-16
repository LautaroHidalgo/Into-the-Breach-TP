package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Casilla.Edificio;
import edu.fiuba.algo3.modelo.Casilla.Montania;
import edu.fiuba.algo3.modelo.unidades.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class EmpujeTest {

    private Tablero tablero;
    private Luciernaga empujada;
    private final Posicion origen = new Posicion(3, 3);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/test/resources/mapas/mapaSinObstaculos.csv"));
        empujada = new Luciernaga();
        tablero.posicionarUnidad(origen, empujada);
    }

    @Test
    public void empujarHaciaUnaCasillaLibreDesplazaALaUnidadSinDaniarla() {
        tablero.empujarUnidad(origen, Direccion.ABAJO);

        assertTrue(tablero.unidadEstaEn(empujada, new Posicion(4, 3)));
        assertFalse(tablero.unidadEstaEn(empujada, origen));
        assertEquals(3, empujada.vida());
    }

    @Test
    public void empujarFueraDelTableroNoMueveNiDaniaALaUnidad() {
        Luciernaga enElBorde = new Luciernaga();
        tablero.posicionarUnidad(new Posicion(0, 0), enElBorde);

        tablero.empujarUnidad(new Posicion(0, 0), Direccion.ARRIBA);

        assertTrue(tablero.unidadEstaEn(enElBorde, new Posicion(0, 0)));
        assertEquals(3, enElBorde.vida());
    }

    @Test
    public void empujarContraOtraUnidadDaniaALasDosYNingunaSeMueve() {
        Luciernaga otra = new Luciernaga();
        tablero.posicionarUnidad(new Posicion(3, 4), otra);

        tablero.empujarUnidad(origen, Direccion.DERECHA);

        assertTrue(tablero.unidadEstaEn(empujada, origen));
        assertTrue(tablero.unidadEstaEn(otra, new Posicion(3, 4)));
        assertEquals(2, empujada.vida());
        assertEquals(2, otra.vida());
    }

    @Test
    public void empujarContraUnaMontaniaDaniaALaUnidadYNoLaMueve() {
        tablero.agregarCasilla(new Posicion(3, 4), new Casilla(new Montania()));

        tablero.empujarUnidad(origen, Direccion.DERECHA);

        assertTrue(tablero.unidadEstaEn(empujada, origen));
        assertEquals(2, empujada.vida());
    }

    @Test
    public void empujarContraUnEdificioDaniaALaUnidadYNoLaMueve() {
        tablero.agregarCasilla(new Posicion(3, 4), new Casilla(new Edificio()));

        tablero.empujarUnidad(origen, Direccion.DERECHA);

        assertTrue(tablero.unidadEstaEn(empujada, origen));
        assertEquals(2, empujada.vida());
    }

    @Test
    public void empujarUnaCasillaVaciaNoTieneEfecto() {
        assertDoesNotThrow(() -> tablero.empujarUnidad(new Posicion(5, 5), Direccion.DERECHA));
    }
}
