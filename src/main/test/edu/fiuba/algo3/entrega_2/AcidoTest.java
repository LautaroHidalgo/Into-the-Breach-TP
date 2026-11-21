package edu.fiuba.algo3.entrega_2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.Tablero.*;

import java.io.File;

public class AcidoTest {
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
}