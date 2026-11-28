package edu.fiuba.algo3.entrega_2;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.casilla.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mech.MechCanon;
import edu.fiuba.algo3.modelo.unidades.vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.vek.Saltador;

import org.junit.jupiter.api.Test;

import java.io.File;

public class EmergenciaTest {
    @Test
    public void test10VekSobreCasillaEmergenciaBloqueaAparicionyRecibeDanio(){
        File mapa = new File("src/main/mapas/mapaTestVeks.csv");
        Tablero tablero = new Tablero(mapa, new RedElectrica());

    }
}
