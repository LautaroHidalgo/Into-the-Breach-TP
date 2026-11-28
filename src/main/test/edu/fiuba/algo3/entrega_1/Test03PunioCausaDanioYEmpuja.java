package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.casilla.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mech.MechCombate;
import edu.fiuba.algo3.modelo.unidades.vek.Luciernaga;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Test03PunioCausaDanioYEmpuja {
    private Tablero tablero;

    @Test
    public void test03ElPunoCausaDosDeDanioAUnaUnidadAdyacenteYLaEmpujaUnaCasillaEnLaDireccionDelGolpe(){
        RedElectrica red = new RedElectrica();
        tablero = new Tablero(new File("src/main/mapas/mapaSinObstaculos.csv"), red);
        Posicion posicionMech = new Posicion(3, 3);
        Posicion posicionVek = new Posicion(3, 4);
        MechCombate mech = new MechCombate();
        Luciernaga vek = new Luciernaga();
        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(posicionVek, vek);

        mech.atacar(posicionMech, posicionVek, tablero);

        int vidaEsperada = 1;

        assertEquals(vidaEsperada, vek.vida());
        assertTrue(tablero.unidadEstaEn(vek, new Posicion(3,5)));
        assertFalse(tablero.unidadEstaEn(mech, posicionVek));
    }
}
