package edu.fiuba.algo3.entrega_1;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class testMechSoloPuedeMoverseRangoPermitido {
    @Test
    public void testMechSoloPuedeMoverseRangoPermitido() {
        // ARRANGE
        Tablero tablero = new Tablero();
        MechCombate mech = new MechCombate();
        Montania montania = new Montania();
        Posicion origen = new Posicion(4,4);
        Posicion obstaculo = new Posicion(4,5);
        Posicion atrasMontania = new Posicion(4,6);
        // montania --- atrasmontania (la próxima para cortar la trayectoria)
        Posicion libre = new Posicion(4,3);
        // Declaro la variable tablero de tipo Tablero;
        // y construyo con new el objeto en memoria guardandolo en la variable;
        // lo mismo para las demas;
        Casilla casillaOrigen = tablero.obtenerCasilla(origen);
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

