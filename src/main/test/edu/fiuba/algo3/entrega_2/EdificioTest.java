package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Casilla.Edificio;
import edu.fiuba.algo3.modelo.Casilla.RedElectrica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdificioTest {

    @Test
    public void unEdificioIntactoAportaEnergiaALaRedElectrica() {
        // ARRANGE
        RedElectrica redElectrica = new RedElectrica();
        new Edificio(redElectrica);

        // ACT
        int energiaActual = redElectrica.getEnergia();

        // ASSERT
        assertEquals(5, energiaActual);
    }

    @Test
    public void unEdificioAlRecibirDanioRestaEnergiaDeLaRedElectrica() {
        // ARRANGE
        RedElectrica redElectrica = new RedElectrica();
        Casilla casillaConEdificio = new Casilla(new Edificio((redElectrica)));

        // ACT
        casillaConEdificio.recibirAtaque(1);

        // ASSERT
        assertEquals(4, redElectrica.getEnergia());
    }

    @Test
    public void unEdificioDestruidoNoVuelveARestarEnergiaSiLoAtacan() {
        // ARRANGE
        RedElectrica redElectrica = new RedElectrica();
        Casilla casillaConEdificio = new Casilla(new Edificio(redElectrica));
        casillaConEdificio.recibirAtaque(1);

        // ACT
        casillaConEdificio.recibirAtaque(1);

        // ASSERT
        assertEquals(4, redElectrica.getEnergia());
    }
}
