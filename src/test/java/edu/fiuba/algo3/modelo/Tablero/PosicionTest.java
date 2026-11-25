package edu.fiuba.algo3.modelo.Tablero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PosicionTest {

    @Test
    public void obtieneLaPosicionAdyacenteEnCadaDireccion(){
        Posicion posicion = new Posicion(3, 3);

        assertEquals(new Posicion(2, 3), posicion.obtenerAdyacente(Direccion.ARRIBA));
        assertEquals(new Posicion(4, 3), posicion.obtenerAdyacente(Direccion.ABAJO));
        assertEquals(new Posicion(3, 2), posicion.obtenerAdyacente(Direccion.IZQUIERDA));
        assertEquals(new Posicion(3, 4), posicion.obtenerAdyacente(Direccion.DERECHA));
    }

    @Test
    public void dosPosicionesEnLaMismaFilaOColumnaEstanAlineadas() {
        Posicion posicion = new Posicion(3, 3);

        assertTrue(posicion.estaAlineadaCon(new Posicion(3, 7)));
        assertTrue(posicion.estaAlineadaCon(new Posicion(0, 3)));
        assertFalse(posicion.estaAlineadaCon(new Posicion(4, 4)));
        assertFalse(posicion.estaAlineadaCon(new Posicion(3, 3)));
    }

    @Test
    public void obtieneLaDireccionHaciaUnaPosicionAlineada() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(Direccion.DERECHA, posicion.direccionHacia(new Posicion(3, 7)));
        assertEquals(Direccion.ARRIBA, posicion.direccionHacia(new Posicion(0, 3)));
        assertEquals(Direccion.ABAJO, posicion.direccionHacia(new Posicion(4, 3)));
        assertEquals(Direccion.IZQUIERDA, posicion.direccionHacia(new Posicion(3, 1)));
    }

    @Test
    public void noHayDireccionHaciaUnaPosicionEnDiagonal() {
        assertThrows(PosicionesNoAlineadasException.class,
                () -> new Posicion(3, 3).direccionHacia(new Posicion(4, 4)));
    }
}
