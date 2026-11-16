package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Casilla.Edificio;
import edu.fiuba.algo3.modelo.Casilla.RedElectrica;

import edu.fiuba.algo3.modelo.Casilla.Suelo;
import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.MechCanon;
import edu.fiuba.algo3.modelo.unidades.Saltador;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    @Test
    public void unUnidadNoPuedeMoverseAUnaCasillaOcupadaPorUnEdificio() {
        // ARRANGE
        Tablero tablero = new Tablero();
        Posicion origen = new Posicion(1, 1);
        Posicion casillaConEdificio = new Posicion(1, 2);
        tablero.agregarCasilla(origen, new Casilla(new Suelo()));
        tablero.agregarCasilla(casillaConEdificio, new Casilla(new Edificio()));
        Unidad unidad = new MechCanon();
        tablero.posicionarUnidad(origen, unidad);

        // ACT
        List<Posicion> movimientosValidos = tablero.movimientosValidosDesde(origen);

        // ASSERT
        assertFalse(movimientosValidos.contains(casillaConEdificio));
    }

    @Test
    public void unEdificioSeDaniaDebidoAQueUnaUnidadChocaContraElla() {
        // ARRANGE
        RedElectrica redElectrica = new RedElectrica();
        Tablero tablero = new Tablero();
        Posicion origen = new Posicion(1, 1);
        Posicion casillaConEdificio = new Posicion(1, 2);
        tablero.agregarCasilla(origen, new Casilla(new Suelo()));
        tablero.agregarCasilla(casillaConEdificio, new Casilla(new Edificio(redElectrica)));
        Unidad unidad = new Saltador();
        tablero.posicionarUnidad(origen, unidad);

        // ACT
        tablero.empujarUnidad(origen, Direccion.DERECHA);

        // ASSERT
        assertEquals(4, redElectrica.getEnergia());
    }
}
