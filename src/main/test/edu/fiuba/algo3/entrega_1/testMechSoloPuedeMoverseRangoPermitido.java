package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Casilla.Montania;
import edu.fiuba.algo3.modelo.Casilla.Suelo;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class testMechSoloPuedeMoverseRangoPermitido {
    @Test
    public void testMechSoloPuedeMoverseRangoPermitido() {
        // ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTest2.csv");
        Tablero tablero = new Tablero(mapa);
        List<Casilla> listaEsperada = new ArrayList<>();
        MechCombate mech = new MechCombate();
        // Declaro la variable tablero de tipo Tablero;
        // y construyo con new el objeto en memoria guardandolo en la variable;
        // lo mismo para las demas;
        Posicion posicionMech = new Posicion(2,3);
        tablero.posicionarUnidad(posicionMech, mech);

        listaEsperada.agregarCasilla(new Posicion(0, 4), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(1, 2), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(1, 3), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(1, 4), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(1, 5), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(2, 1), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(2, 2), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(2, 3), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(3, 2), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(3, 1), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(4, 3), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(4, 1), new Casilla(new Suelo()));
        listaEsperada.agregarCasilla(new Posicion(4, 2), new Casilla(new Suelo()));

        // ACT
        List<Posicion> movimientos = tablero.movimientosValidosDesde(origen);
        // Guardo el conjunto de movimientos en una lista;

        // ASSERT
        assertFalse(movimientos.contains(obstaculo));
        // Tira falso para que no sea valido estar sobre la montania;
        assertFalse(movimientos.contains(atrasMontania));
        // Tira falso para que no pueda teletransportarse atras de la montania;
        assertTrue(movimientos.contains(libre));
        // Tira verdadero si esta libre y se puede mover ahi
    }
}

