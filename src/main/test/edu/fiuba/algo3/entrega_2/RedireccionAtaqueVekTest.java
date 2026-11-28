package edu.fiuba.algo3.entrega_2;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.casilla.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mech.MechCanon;
import edu.fiuba.algo3.modelo.unidades.vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.vek.Saltador;

import org.junit.jupiter.api.Test;

import java.io.File;


public class RedireccionAtaqueVekTest {

    @Test
    public void test08ElAtaqueDelVekSeMueveConElyAtacaOtroVek(){
        //ARRANGE
        File mapa = new File("src/main/mapas/mapaTestVeks.csv");
        Tablero tablero = new Tablero(mapa, new RedElectrica());
        Posicion posicionMech = new Posicion(1,2);
        Posicion posicionVek = new Posicion(1,3);
        Posicion desplazamientoVek = new Posicion(1,4);
        Posicion edificio = new Posicion(0,3);
        Posicion posicionVekVictima = new Posicion(0,4);
        // Se guarda la direccion ARRIBA
        Direccion direccion = posicionVek.direccionHacia(edificio);
        MechCanon mech = new MechCanon();
        Avispon avispon = new Avispon();
        Saltador saltador = new Saltador();

        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(posicionVek, avispon);
        tablero.posicionarUnidad(posicionVekVictima, saltador);
        avispon.anunciarAtaque(direccion);

        //ACT
        mech.atacar(posicionMech, posicionVek, tablero);
        avispon.ejecutarAtaque(tablero);

        //ASSERT
        assertTrue(tablero.unidadEstaEn(avispon,desplazamientoVek));
        assertFalse(tablero.estaOcupada(posicionVek));

        assertEquals(1, saltador.vida());
        assertTrue(tablero.estaOcupada(posicionVekVictima));

        assertEquals(edificio, tablero.buscarPrimerImpacto(posicionVek, direccion));
    }

}
