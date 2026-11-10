package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Casilla.*;
import edu.fiuba.algo3.modelo.Tablero.*;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

public class TableroTest {

    @Test
    public void tableroSeCargaAPartirDeUnMapaPredefinido() {

        Tablero mapaEsperado = new Tablero();

        mapaEsperado.agregarCasilla(new Posicion(0, 0), new Casilla(new Montania()));
        mapaEsperado.agregarCasilla(new Posicion(0, 1), new Casilla(new Montania()));
        mapaEsperado.agregarCasilla(new Posicion(0, 2), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(0, 3), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(0, 4), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(0, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(0, 6), new Casilla(new Montania()));
        mapaEsperado.agregarCasilla(new Posicion(0, 7), new Casilla(new Montania()));

        mapaEsperado.agregarCasilla(new Posicion(1, 0), new Casilla(new Montania()));
        mapaEsperado.agregarCasilla(new Posicion(1, 1), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 3), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 4), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 7), new Casilla(new Montania()));

        mapaEsperado.agregarCasilla(new Posicion(2, 0), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(2, 1), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 3), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 4), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 7), new Casilla(new Agua()));

        mapaEsperado.agregarCasilla(new Posicion(3, 0), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(3, 1), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(3, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(3, 3), new Casilla(new Montania()));
        mapaEsperado.agregarCasilla(new Posicion(3, 4), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(3, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(3, 6), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(3, 7), new Casilla(new Agua()));

        mapaEsperado.agregarCasilla(new Posicion(4, 0), new Casilla(new Montania()));
        mapaEsperado.agregarCasilla(new Posicion(4, 1), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(4, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(4, 3), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(4, 4), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(4, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(4, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(4, 7), new Casilla(new Montania()));

        mapaEsperado.agregarCasilla(new Posicion(5, 0), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(5, 1), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(5, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(5, 3), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(5, 4), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(5, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(5, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(5, 7), new Casilla(new Montania()));

        mapaEsperado.agregarCasilla(new Posicion(6, 0), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(6, 1), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(6, 2), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(6, 3), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(6, 4), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(6, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(6, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(6, 7), new Casilla(new Edificio()));

        mapaEsperado.agregarCasilla(new Posicion(7, 0), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(7, 1), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(7, 2), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(7, 3), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(7, 4), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(7, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(7, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(7, 7), new Casilla(new Montania()));

        File mapa = new File("src/test/resources/mapas/mapa.csv");
        Tablero tablero = new Tablero(mapa);

        assertTrue(tablero.equals(mapaEsperado));
    }

    @Test
    public void comparar2MapasDiferentesDaFalso() {

        Tablero mapaEsperado = new Tablero();

        mapaEsperado.agregarCasilla(new Posicion(0, 0), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(0, 1), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(0, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(0, 3), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(0, 4), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(0, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(0, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(0, 7), new Casilla(new Suelo()));

        mapaEsperado.agregarCasilla(new Posicion(1, 0), new Casilla(new Montania()));
        mapaEsperado.agregarCasilla(new Posicion(1, 1), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 3), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 4), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(1, 7), new Casilla(new Montania()));

        mapaEsperado.agregarCasilla(new Posicion(2, 0), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(2, 1), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 3), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 4), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(2, 7), new Casilla(new Agua()));

        mapaEsperado.agregarCasilla(new Posicion(3, 0), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(3, 1), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(3, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(3, 3), new Casilla(new Montania()));
        mapaEsperado.agregarCasilla(new Posicion(3, 4), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(3, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(3, 6), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(3, 7), new Casilla(new Agua()));

        mapaEsperado.agregarCasilla(new Posicion(4, 0), new Casilla(new Montania()));
        mapaEsperado.agregarCasilla(new Posicion(4, 1), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(4, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(4, 3), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(4, 4), new Casilla(new Edificio()));
        mapaEsperado.agregarCasilla(new Posicion(4, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(4, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(4, 7), new Casilla(new Montania()));

        mapaEsperado.agregarCasilla(new Posicion(5, 0), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(5, 1), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(5, 2), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(5, 3), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(5, 4), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(5, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(5, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(5, 7), new Casilla(new Montania()));

        mapaEsperado.agregarCasilla(new Posicion(6, 0), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(6, 1), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(6, 2), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(6, 3), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(6, 4), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(6, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(6, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(6, 7), new Casilla(new Edificio()));

        mapaEsperado.agregarCasilla(new Posicion(7, 0), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(7, 1), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(7, 2), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(7, 3), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(7, 4), new Casilla(new Agua()));
        mapaEsperado.agregarCasilla(new Posicion(7, 5), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(7, 6), new Casilla(new Suelo()));
        mapaEsperado.agregarCasilla(new Posicion(7, 7), new Casilla(new Montania()));

        File mapa = new File("src/test/resources/mapas/mapa.csv");
        Tablero tablero = new Tablero(mapa);

        assertFalse(tablero.equals(mapaEsperado));
    }

    @Test
    public void noSePuedeCrearUnMapaConCantidadDeCasillasDistintoA64(){

        File mapa = new File("src/test/resources/mapas/mapaInvalido.csv");
        
        assertThrows(TableroInvalidoException.class,() -> new Tablero(mapa));  
    }
}
