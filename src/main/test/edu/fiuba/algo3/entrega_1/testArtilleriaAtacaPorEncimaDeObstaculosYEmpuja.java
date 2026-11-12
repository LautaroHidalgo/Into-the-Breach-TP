package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.DispararCasillaAdyacente;
import edu.fiuba.algo3.modelo.unidades.MechArtilleria;
import edu.fiuba.algo3.modelo.unidades.Saltador;
import org.junit.jupiter.api.Test;

import java.io.File;

public class testArtilleriaAtacaPorEncimaDeObstaculosYEmpuja {
    @Test
    public void testArtilleriaAtacaPorEncimaDeObstaculosYEmpuja(){
        // ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTest5.csv");
        Tablero tablero = new Tablero(mapa);
        MechArtilleria mech = new MechArtilleria();
        Posicion posicionMech = new Posicion(2,1);
        Posicion obstaculo = new Posicion(2, 2);
        tablero.posicionarUnidad(posicionMech, mech);
        Saltador vek1 = new Saltador();
        Saltador vek2 = new Saltador();
        Saltador vek3 = new Saltador();
        Saltador vek4 = new Saltador();
        Saltador vek5 = new Saltador();
        Posicion posicionEnemigo1 = new Posicion(2,3); //atras de la montania
        Posicion posicionEnemigo2 = new Posicion(2,4); //atras de la montania (medio de los veks)
        Posicion posicionEnemigo3 = new Posicion(2,5); //atras de la montania
        Posicion posicionEnemigo4 = new Posicion(1,4); //arriba de la montania
        Posicion posicionEnemigo5 = new Posicion(3,4); //abajo de la montania
        tablero.posicionarUnidad(posicionEnemigo1, vek1);
        tablero.posicionarUnidad(posicionEnemigo2, vek2);
        tablero.posicionarUnidad(posicionEnemigo3, vek3);
        tablero.posicionarUnidad(posicionEnemigo4, vek4);
        tablero.posicionarUnidad(posicionEnemigo5, vek5);
        // Desplazamiento de mentira, falta el metodo para desplazar
        Posicion desplazamientoAr = new Posicion(0,4); // Se mueve arriba
        Posicion desplazamientoDe = new Posicion(2,6); // Se mueve derecha
        Posicion desplazamientoAb = new Posicion(4,4); // Se mueve abajo

        //ACT
        DispararCasillaAdyacente exception = assertThrows(
                DispararCasillaAdyacente.class, () -> mech.atacar(posicionMech,obstaculo, tablero),
                "Este mech no puede disparar a una casilla adyacente"
        );
        assertEquals("El mech no puede disparar a una casilla adyacente", exception.getMessage());

        mech.atacar(posicionMech,posicionEnemigo2,tablero);

        // ASSERT
        assertTrue(vek2.tieneVida(1)); // Pierde 1 porque es el objetivo
        assertTrue(tablero.posicionEstaOcupada(posicionEnemigo2));

        // Arriba
        assertFalse(tablero.posicionEstaOcupada(posicionEnemigo4));
        assertTrue(tablero.posicionEstaOcupada(desplazamientoAr));
        assertTrue(vek4.tieneVida(2));

        // Abajo
        assertFalse(tablero.posicionEstaOcupada(posicionEnemigo5));
        assertTrue(tablero.posicionEstaOcupada(desplazamientoAb));
        assertTrue(vek5.tieneVida(2));

        // Derecha
        assertFalse(tablero.posicionEstaOcupada(posicionEnemigo3));
        assertTrue(tablero.posicionEstaOcupada(desplazamientoDe));
        assertTrue(vek3.tieneVida(2));

        assertTrue(tablero.posicionEstaOcupada(posicionEnemigo1));
        assertTrue(vek1.tieneVida(1)); // Pierde 1 de vida por chocar

    }

}