package Tests;

import Clases.Posicion;
import Clases.Casilla;

import Clases.Tablero;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasillaTest {

    @Test
    void noPermiteCrearTableroConMenosDe64Casillas() {
        Map<Posicion, Casilla> casillas = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            new Tablero(casillas);
        });
    }
}
