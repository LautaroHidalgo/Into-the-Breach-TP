package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

public class testArtilleriaAtacaPorEncimaDeObstaculosYEmpuja {
    @Test
    public void testArtilleriaAtacaPorEncimaDeObstaculosYEmpuja(){
        // ARRANGE
        Tablero tablero = new Tablero();
        MechArtilleria mech = new MechArtilleria();
        VekTerrestre vek1 = new VekTerrestre();
        VekTerrestre vek2 = new VekTerrestre();
        VekTerrestre vek3 = new VekTerrestre();
        VekTerrestre vek4 = new VekTerrestre();
        VekTerrestre vek5 = new VekTerrestre();
        Posicion origen = new Posicion(3,3);
        Posicion obstaculo = new Posicion(3,4);
        Posicion enemigo1 = new Posicion(2,6); //arriba de la montania
        Posicion enemigo2 = new Posicion(3,5); //atras de la montania
        Posicion enemigo3 = new Posicion(3,6); //atras de la montania (medio de los veks)
        Posicion enemigo4 = new Posicion(3,7); //atras de la montania
        Posicion enemigo5 = new Posicion(4,6); //abajo de la montania
        Posicion desplazamientoAr = new Posicion(1,6); // Se mueve arriba
        Posicion desplazamientoIz = new Posicion(3,4); // Se mueve izquierda
        Posicion desplazamientoDe = new Posicion(3,8); // Se mueve derecha
        Posicion desplazamientoAb = new Posicion(5,6); // Se mueve abajo
        Montania montania = new Montania();
        Casilla casillaOrigen = tablero.obtenerCasilla(origen);
        Casilla casillaObstaculo = tablero.obtenerCasilla(obstaculo);
        Casilla casillaEnemiga1 = tablero.obtenerCasilla(enemigo1);
        Casilla casillaEnemiga2 = tablero.obtenerCasilla(enemigo2);
        Casilla casillaEnemiga3 = tablero.obtenerCasilla(enemigo3);
        Casilla casillaEnemiga4 = tablero.obtenerCasilla(enemigo4);
        Casilla casillaEnemiga5 = tablero.obtenerCasilla(enemigo5);
        Casilla casillaDestinoArriba = tablero.obtenerCasilla(desplazamientoAr);
        Casilla casillaDestinoIzquierda = tablero.obtenerCasilla(desplazamientoIz);
        Casilla casillaDestinoDerecha = tablero.obtenerCasilla(desplazamientoDe);
        Casilla casillaDestinoAbajo = tablero.obtenerCasilla(desplazamientoAb);
        casillaEnemiga1.recibirUnidad(vek1);
        casillaEnemiga2.recibirUnidad(vek2);
        casillaEnemiga3.recibirUnidad(vek3);
        casillaEnemiga4.recibirUnidad(vek4);
        casillaEnemiga5.recibirUnidad(vek5);
        casillaOrigen.recibirUnidad(mech);
        casillaObstaculo.cambiarTerreno(montania);

        //ACT
        DispararCasillaAdyacente exception = assertThrows(
                DispararCasillaAdyacente.class, () -> mech.atacar(origen,obstaculo, tablero),
                "Un mech no puede disparar a una casilla adyacente"
        );
        assertEquals("Disparo a casilla adyacente", exception.getMessage());

        mech.atacar(origen,enemigo3,tablero);

        // ASSERT
        assertTrue(vek3.tieneVida(2));
        assertTrue(casillaEnemiga1.estaLibre());
        assertFalse(casillaDestinoArriba.estaLibre());
        assertFalse(casillaEnemiga2.estaLibre());
        assertTrue(vek2.tieneVida(2));
        assertTrue(casillaEnemiga4.estaLibre());
        assertFalse(casillaDestinoDerecha.estaLibre());
        assertTrue(casillaEnemiga5.estaLibre());
        assertFalse(casillaDestinoAbajo.estaLibre());
    }
}