package edu.fiuba.algo3.entrega_2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.Tablero.*;

import java.io.File;

public class AcidoTest {

    // ARREGLAR TESTS
    @Test
    public void test04UnaUnidadTerrestreEnAcidoPorMovimientoQuedaContaminadayRecibeDobleDanio() {
        //ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestAcido.csv");
        Tablero tablero = new Tablero(mapa);

        Posicion posicionMech = new Posicion(2, 2);
        Posicion destinoAcido = new Posicion(2, 3);
        Luciernaga luciernaga = new Luciernaga();
        MechCanon mech = new MechCanon();

        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(destinoAcido, luciernaga);

        //ACT
        mech.atacar(posicionMech, destinoAcido, tablero);

        //ASSERT
        assertEquals(1, luciernaga.vida());
    }
    // ARREGLAR TESTS
    @Test
    public void test04BAvisponEnAcidoNoSeContaminaYRecibeDanioComun() {
        //ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestAcido.csv");
        Tablero tablero = new Tablero(mapa);
        Posicion posicionMech = new Posicion(1, 3);
        Posicion destinoAcido = new Posicion(1, 4);
        Posicion destinoSuelo = new Posicion(1, 5);
        Avispon avispon = new Avispon();
        MechCanon mech = new MechCanon();

        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(destinoAcido, avispon);

        //ACT
        mech.atacar(posicionMech, destinoAcido, tablero);

        //ASSERT
        assertTrue(tablero.unidadEstaEn(avispon, destinoSuelo));
        assertEquals(1, avispon.vida());
    }

    @Test
    public void test04UnMechQueSeMueveAlAcidoQuedaContaminadoYRecibeDobleDanio() {
        //ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestAcido.csv");
        Tablero tablero = new Tablero(mapa);

        Posicion origenSuelo = new Posicion(1, 3);
        Posicion destinoAcido = new Posicion(1, 4);
        MechCombate mech = new MechCombate();

        tablero.posicionarUnidad(origenSuelo, mech);

        //ACT
        tablero.moverUnidad(origenSuelo, destinoAcido);
        mech.recibirDanio(1);

        //ASSERT
        assertEquals(1, mech.vida());
    }

    @Test
    public void ubicarUnaUnidadSobreElAcidoNoLaContamina() {
        //ARRANGE
        File mapa = new File("src/test/resources/mapas/mapaTestAcido.csv");
        Tablero tablero = new Tablero(mapa);

        Posicion posicionAcido = new Posicion(1, 4);
        Luciernaga luciernaga = new Luciernaga();

        tablero.posicionarUnidad(posicionAcido, luciernaga);

        //ACT
        luciernaga.recibirDanio(1);

        //ASSERT
        assertEquals(2, luciernaga.vida());
    }

}