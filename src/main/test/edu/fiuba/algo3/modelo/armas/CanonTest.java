package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Casilla.Agua;
import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Casilla.Edificio;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class CanonTest {

    private Tablero tablero;
    private Canon canon;
    private final Posicion origen = new Posicion(3, 0);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/test/resources/mapas/mapaSinObstaculos.csv"));
        canon = new Canon();
    }

    @Test
    public void noPuedeDispararAUnaCasillaQueNoEstaEnLineaRecta() {
        assertThrows(ObjetivoInvalidoException.class,
                () -> canon.atacar(tablero, origen, new Posicion(4, 2)));
    }

    @Test
    public void noPuedeDispararFueraDelTablero() {
        assertThrows(ObjetivoInvalidoException.class,
                () -> canon.atacar(tablero, origen, new Posicion(3, 9)));
    }

    @Test
    public void unEdificioFrenaElDisparo(){
        Luciernaga detras = new Luciernaga();
        tablero.agregarCasilla(new Posicion(3,2), new Casilla(new Edificio()));
        tablero.posicionarUnidad(new Posicion(3, 4), detras);

        canon.atacar(tablero, origen, new Posicion(3, 4));

        assertEquals(3, detras.vida());
    }

    @Test
    public void elAguaNoFrenaElDisparo(){
        Luciernaga vek = new Luciernaga();
        tablero.agregarCasilla(new Posicion(3,2), new Casilla(new Agua()));
        tablero.posicionarUnidad(new Posicion(3, 4), vek);

        canon.atacar(tablero, origen, new Posicion(3, 4));

        assertEquals(2, vek.vida());
    }

    @Test
    public void siNoHayNadaEnLineaElDisparoNoTieneEfecto() {
        assertDoesNotThrow(() -> canon.atacar(tablero, origen, new Posicion(3, 7)));
    }
}

