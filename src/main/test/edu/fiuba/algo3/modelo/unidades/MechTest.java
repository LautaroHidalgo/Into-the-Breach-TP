package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.armas.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.casilla.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.excepciones.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.unidades.mech.MechCombate;
import edu.fiuba.algo3.modelo.unidades.vek.Luciernaga;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MechTest {

    private Tablero tablero;
    private MechCombate mech;
    private Luciernaga vek;
    private final Posicion posicionMech = new Posicion(3,3);
    private final Posicion posicionVek = new Posicion(3,4);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/main/mapas/mapaSinObstaculos.csv"), new RedElectrica());
        mech = new MechCombate();
        vek = new Luciernaga();
        tablero.posicionarUnidad(posicionMech, mech);
        tablero.posicionarUnidad(posicionVek, vek);
    }

    @Test
    public void unAtaqueAUnObjetivoInvalidoNoGastaLaAccionDeAtaque() {
        assertThrows(ObjetivoInvalidoException.class,
                () -> mech.atacar(posicionMech, new Posicion(3, 6), tablero));

        mech.atacar(posicionMech, posicionVek, tablero);

        assertEquals(1, vek.vida());
    }

    @Test
    public void unSegundoAtaqueEnElMismoTurnoNoTieneNingunEfecto() {
        mech.atacar(posicionMech, posicionVek, tablero);
        Luciernaga otro = new Luciernaga();
        tablero.posicionarUnidad(new Posicion(2, 3), otro);

        assertThrows(AccionNoPermitidaException.class,
                () -> mech.atacar(posicionMech, new Posicion(2, 3), tablero));
        assertEquals(3, otro.vida());
    }

}
