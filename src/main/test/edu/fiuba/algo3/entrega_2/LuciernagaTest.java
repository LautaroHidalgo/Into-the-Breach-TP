//Cambio: archivo nuevo. La Luciérnaga contamina a la unidad que impacta y la contaminación no se acumula.
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.Montania;
import edu.fiuba.algo3.modelo.casilla.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mech.MechArtilleria;
import edu.fiuba.algo3.modelo.unidades.mech.MechCombate;
import edu.fiuba.algo3.modelo.unidades.vek.Luciernaga;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class LuciernagaTest {

    @Test
    public void test06LaLuciernagaDaniaYContaminaALaPrimeraUnidadQueImpactaSinEmpujarla() {
        Tablero tablero = new Tablero(new File("src/main/mapas/mapaSinObstaculos.csv"), new RedElectrica());
        Luciernaga luciernaga = new Luciernaga();
        MechCombate mech = new MechCombate();
        tablero.posicionarUnidad(new Posicion(3, 0), luciernaga);
        tablero.posicionarUnidad(new Posicion(3, 3), mech);

        luciernaga.atacar(new Posicion(3, 0), new Posicion(3, 7), tablero);

        assertEquals(2, mech.vida());
        assertTrue(tablero.unidadEstaEn(mech, new Posicion(3, 3)));

        mech.recibirDanio(1);

        assertEquals(0, mech.vida());
    }

    @Test
    public void test06LaContaminacionNoSeAcumula() {
        // mapaTestAcido.csv: (2,3), (2,4) y (2,5) son Ácido Dos disparos de Artillería empujan al Mech,
        // sin dañarlo de (2,5) a (2,4) y de (2,4) a (2,3) queda contaminado dos veces.
        Tablero tablero = new Tablero(new File("src/main/mapas/mapaTestAcido.csv"), new RedElectrica());
        MechCombate mech = new MechCombate();
        MechArtilleria primeraArtilleria = new MechArtilleria();
        MechArtilleria segundaArtilleria = new MechArtilleria();
        Luciernaga luciernaga = new Luciernaga();
        tablero.posicionarUnidad(new Posicion(2, 5), mech);
        tablero.posicionarUnidad(new Posicion(4, 6), primeraArtilleria);
        tablero.posicionarUnidad(new Posicion(5, 5), segundaArtilleria);
        tablero.posicionarUnidad(new Posicion(2, 1), luciernaga);

        primeraArtilleria.atacar(new Posicion(4, 6), new Posicion(2, 6), tablero);
        segundaArtilleria.atacar(new Posicion(5, 5), new Posicion(2, 5), tablero);

        assertTrue(tablero.unidadEstaEn(mech, new Posicion(2, 3)));
        assertEquals(3, mech.vida());

        // contaminado dos veces el escupitajo (1 de daño) le saca el doble (2), no el cuádruple (4).
        luciernaga.atacar(new Posicion(2, 1), new Posicion(2, 7), tablero);

        assertEquals(1, mech.vida());
    }

    @Test
    public void siLaLuciernagaImpactaUnObstaculoNoDaniaALaUnidadQueEstaDetras() {
        Tablero tablero = new Tablero(new File("src/main/mapas/mapaSinObstaculos.csv"), new RedElectrica());
        Luciernaga luciernaga = new Luciernaga();
        MechCombate mech = new MechCombate();
        tablero.posicionarUnidad(new Posicion(3, 0), luciernaga);
        tablero.agregarCasilla(new Posicion(3, 2), new Casilla(new Montania()));
        tablero.posicionarUnidad(new Posicion(3, 4), mech);

        luciernaga.atacar(new Posicion(3, 0), new Posicion(3, 7), tablero);

        assertEquals(3, mech.vida());
        mech.recibirDanio(1);
        assertEquals(2, mech.vida());
    }
}
