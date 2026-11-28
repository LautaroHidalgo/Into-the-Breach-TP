package edu.fiuba.algo3.modelo.unidades;


import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.unidades.estadosalud.Contaminado;
import edu.fiuba.algo3.modelo.unidades.estadosalud.EstadoSalud;
import edu.fiuba.algo3.modelo.unidades.estadosalud.Sano;

import static org.junit.jupiter.api.Assertions.*;

public class EstadoSaludTest {

    @Test
    public void unaUnidadSanaRecibeElDanioBase() {
        assertEquals(2, new Sano().calcularDanio(2));
    }

    @Test
    public void unaUnidadContaminadaRecibeElDobleDeDanio() {
        assertEquals(4, new Contaminado().calcularDanio(2));
    }

    @Test
    public void contaminarAUnaUnidadSanaLaDejaContaminada() {
        assertEquals(2, new Sano().contaminar().calcularDanio(1));
    }

    @Test
    public void laContaminacionNoSeAcumula() {
        EstadoSalud estado = new Contaminado().contaminar().contaminar();

        assertEquals(2, estado.calcularDanio(1));
    }
}
