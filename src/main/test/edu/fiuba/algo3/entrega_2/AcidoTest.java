package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Casilla.RedElectrica;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.Tablero.*;

import java.io.File;

public class AcidoTest {

    @Test
    public void test04UnaUnidadTerrestreEmpujadaAlAcidoQuedaContaminadayRecibeDobleDanio() {
        //ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestAcido.csv");
        Tablero tablero = new Tablero(mapa, new RedElectrica());

        Posicion posicionMech = new Posicion(1, 2);
        Posicion origenSuelo = new Posicion(1, 3);
        Posicion destinoAcido = new Posicion(1, 4);
        Posicion destinoFinal = new Posicion(1, 5);
        Luciernaga luciernaga = new Luciernaga();
        MechCanon mech = new MechCanon();

        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(origenSuelo, luciernaga);

        //ACT
        tablero.empujarUnidad(origenSuelo, Direccion.DERECHA);
        mech.atacar(posicionMech, destinoAcido, tablero);

        //ASSERT
        assertTrue(tablero.unidadEstaEn(luciernaga, destinoFinal));
        assertEquals(1, luciernaga.vida());
    }

    @Test
    public void test04BAvisponEnAcidoNoSeContaminaYRecibeDanioComun() {
        //ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestAcido.csv");
        Tablero tablero = new Tablero(mapa, new RedElectrica());

        Posicion posicionMech = new Posicion(1, 2);
        Posicion origenSuelo = new Posicion(1, 3);
        Posicion destinoAcido = new Posicion(1, 4);
        Posicion destinoSuelo = new Posicion(1, 5);
        Avispon avispon = new Avispon();
        MechCanon mech = new MechCanon();

        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(origenSuelo, avispon);

        //ACT
        tablero.empujarUnidad(origenSuelo, Direccion.DERECHA);
        mech.atacar(posicionMech, destinoAcido, tablero);

        //ASSERT
        assertTrue(tablero.unidadEstaEn(avispon, destinoSuelo));
        assertEquals(1, avispon.vida());
    }

    @Test
    public void test04UnMechQueSeMueveAlAcidoQuedaContaminadoYRecibeDobleDanio() {
        //ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestAcido.csv");
        Tablero tablero = new Tablero(mapa, new RedElectrica());

        Posicion origenSuelo = new Posicion(1, 3);
        Posicion destinoAcido = new Posicion(1, 4);
        MechCombate mech = new MechCombate();

        tablero.posicionarUnidad(origenSuelo, mech);

        //ACT
        tablero.moverUnidad(origenSuelo, destinoAcido);
        mech.recibirDanio(1);

        //ASSERT
        assertTrue(tablero.unidadEstaEn(mech, destinoAcido));
        assertEquals(1, mech.vida());
    }

    @Test
    public void ubicarUnaUnidadSobreElAcidoNoLaContamina() {
        //ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestAcido.csv");
        Tablero tablero = new Tablero(mapa, new RedElectrica());

        Posicion posicionAcido = new Posicion(1, 4);
        Luciernaga luciernaga = new Luciernaga();

        tablero.posicionarUnidad(posicionAcido, luciernaga);

        //ACT
        luciernaga.recibirDanio(1);

        //ASSERT
        assertEquals(2, luciernaga.vida());
    }

    @Test
    public void test06AlDeshacerElMovimientoDeUnMechQueTerminoSobreAcidoDejaDeEstarContaminado() {
        //ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestAcido.csv");
        Tablero tablero = new Tablero(mapa, new RedElectrica());

        Posicion origenSuelo = new Posicion(1, 3);
        Posicion destinoAcido = new Posicion(1, 4);
        MechCombate mech = new MechCombate();

        tablero.posicionarUnidad(origenSuelo, mech);

        //ACT
        tablero.moverUnidad(origenSuelo, destinoAcido);
        tablero.deshacerMovimiento(destinoAcido);
        mech.recibirDanio(1);

        //ASSERT
        assertTrue(tablero.unidadEstaEn(mech, origenSuelo));
        assertEquals(2, mech.vida());
    }

    @Test
    public void test06BUnMechYaContaminadoQueSeMueveASueloYDeshaceSigueContaminado() {
        // ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestAcido.csv");
        Tablero tablero = new Tablero(mapa, new RedElectrica());

        Posicion origenSuelo = new Posicion(1, 2);
        Posicion destinoSuelo = new Posicion(1, 3);
        MechCombate mech = new MechCombate();
        mech.recibirContaminacion();

        tablero.posicionarUnidad(origenSuelo, mech);

        // ACT
        tablero.moverUnidad(origenSuelo, destinoSuelo);
        tablero.deshacerMovimiento(destinoSuelo);
        mech.recibirDanio(1);

        // ASSERT
        assertTrue(tablero.unidadEstaEn(mech, origenSuelo));
        assertEquals(1, mech.vida());
    }



}