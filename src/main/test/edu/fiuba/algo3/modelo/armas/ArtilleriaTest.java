package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Casilla.Montania;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ArtilleriaTest {

    private Tablero tablero;
    private Artilleria artilleria;
    private final Posicion origen = new Posicion(3, 0);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/test/resources/mapas/mapaSinObstaculos.csv"));
        artilleria = new Artilleria();
    }
    @Test
    public void noPuedeAtacarUnaCasillaAdyacente() {
        assertThrows(ObjetivoInvalidoException.class, () -> artilleria.atacar(tablero, origen, new Posicion(3,1)));

    }

    @Test
    public void noPuedeAtacarUnaCasillaQueNoEsteEnLineaRecta(){
        assertThrows(ObjetivoInvalidoException.class, () -> artilleria.atacar(tablero, origen, new Posicion(5,3)));
    }

    @Test
    public void noPuedeAtacarFueraDelTablero(){
        assertThrows(ObjetivoInvalidoException.class, () -> artilleria.atacar(tablero, origen, new Posicion(3,9)));
    }

    @Test
    public void elDisparoPasaPorEncimaDeUnaUnidadYUnaMontania(){
        Luciernaga enElMedio = new Luciernaga();
        Luciernaga objetivo = new Luciernaga();
        tablero.agregarCasilla(new Posicion(3,1), new Casilla(new Montania()));
        tablero.posicionarUnidad(new Posicion(3,2), enElMedio);
        tablero.posicionarUnidad(new Posicion(3,4), objetivo);

        artilleria.atacar(tablero, origen, new Posicion(3,4));

        assertEquals(3, enElMedio.vida());
        assertEquals(2, objetivo.vida());

    }

    @Test
    public void unaUnidadAdyacenteQueChocaContraUnObstaculoRecibeDanioYNoSeMueve() {
        Luciernaga adyacente = new Luciernaga();
        tablero.posicionarUnidad(new Posicion(3, 5), adyacente);
        tablero.agregarCasilla(new Posicion(3, 6), new Casilla(new Montania()));

        artilleria.atacar(tablero, origen, new Posicion(3, 4));

        assertEquals(2, adyacente.vida());
        assertTrue(tablero.unidadEstaEn(adyacente, new Posicion(3, 5)));
    }

    @Test
    public void atacarUnaCasillaVaciaSinVecinosNoTieneEfecto() {
        assertDoesNotThrow(() -> artilleria.atacar(tablero, origen, new Posicion(3, 4)));
    }
}
