package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Casilla.Montania;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class testMechSoloPuedeMoverseRangoPermitido {
    @Test
    public void testMechSoloPuedeMoverseRangoPermitido() {
        // ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTest2.csv");
        Tablero tablero = new Tablero(mapa);
        MechCombate mech = new MechCombate();
        // montania --- atrasmontania (la próxima para cortar la trayectoria)
        // Declaro la variable tablero de tipo Tablero;
        // y construyo con new el objeto en memoria guardandolo en la variable;
        // lo mismo para las demas;
        Posicion posicionMech = new Posicion(2,3);
        Casilla casillaOrigen = tablero.obtenerCasilla(posicionMech);
        Casilla casillaObstaculo = tablero.obtenerCasilla(obstaculo);
        casillaOrigen.recibirUnidad(mech);
        casillaObstaculo.cambiarTerreno(montania);

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

