package edu.fiuba.algo3.modelo.unidades;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnidadTest {

    @Test
    public void cadaUnidadEmpiezaConLaVidaIndicada() {
        assertEquals(3, new MechCombate().vida());
        assertEquals(3, new MechCanon().vida());
        assertEquals(2, new MechArtilleria().vida());
        assertEquals(2, new Saltador().vida());
        assertEquals(3, new Luciernaga().vida());
        assertEquals(2, new Avispon().vida());
    }

    @Test
    public void recibirDanioReduceLaVida() {
        Luciernaga luciernaga = new Luciernaga();

        luciernaga.recibirDanio(1);

        assertEquals(2, luciernaga.vida());
        assertTrue(luciernaga.estaViva());
    }

    @Test
    public void unaUnidadSinVidaEstaMuertaYSuVidaNoBajaDeCero() {
        Saltador saltador = new Saltador();

        saltador.recibirDanio(5);

        assertEquals(0, saltador.vida());
        assertFalse(saltador.estaViva());
    }

    @Test
    public void unaUnidadContaminadaRecibeElDobleDeDanio() {
        Luciernaga luciernaga = new Luciernaga();
        luciernaga.recibirContaminacion();

        luciernaga.recibirDanio(1);

        assertEquals(1, luciernaga.vida());
    }

    @Test
    public void contaminarDosVecesNoAcumulaElDanio() {
        Luciernaga luciernaga = new Luciernaga();
        luciernaga.recibirContaminacion();
        luciernaga.recibirContaminacion();

        luciernaga.recibirDanio(1);

        assertEquals(1, luciernaga.vida());
    }
}
