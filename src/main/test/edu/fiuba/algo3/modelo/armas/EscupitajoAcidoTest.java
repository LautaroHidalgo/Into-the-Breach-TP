package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Casilla.RedElectrica;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class EscupitajoAcidoTest {

    private Tablero tablero;
    private EscupitajoAcido escupitajo;
    private final Posicion origen = new Posicion(3, 0);

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(new File("src/test/resources/mapas/mapaSinObstaculos.csv"), new RedElectrica());
        escupitajo = new EscupitajoAcido();
    }

    @Test
    public void noPuedeAtacarUnaCasillaQueNoEstaEnLineaRecta() {
        assertThrows(ObjetivoInvalidoException.class,
                () -> escupitajo.atacar(tablero, origen, new Posicion(4, 2)));
    }

    @Test
    public void impactaSoloALaPrimeraUnidadEnLinea() {
        Luciernaga primera = new Luciernaga();
        Luciernaga detras = new Luciernaga();
        tablero.posicionarUnidad(new Posicion(3, 2), primera);
        tablero.posicionarUnidad(new Posicion(3, 5), detras);

        escupitajo.atacar(tablero, origen, new Posicion(3, 7));

        assertEquals(2, primera.vida());
        assertEquals(3, detras.vida());
    }

    @Test
    public void siNoHayNadaEnLineaNoTieneEfecto() {
        assertDoesNotThrow(() -> escupitajo.atacar(tablero, origen, new Posicion(3, 7)));
    }
}
