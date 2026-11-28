package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.casilla.*;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.unidades.mech.MechCanon;
import edu.fiuba.algo3.modelo.unidades.vek.Saltador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EdificioTest {

    private RedElectrica redElectrica;
    private Tablero tablero;
    private Posicion origen;
    private Posicion adyacente;

    @BeforeEach
    public void setUp() {
        redElectrica = new RedElectrica();
        tablero = new Tablero();
        origen = new Posicion(1, 1);
        adyacente = new Posicion(1, 2);
    }

    @Test
    public void unEdificioAlRecibirDanioRestaEnergiaDeLaRedElectrica() {
        // ARRANGE
        Casilla casillaConEdificio = new Casilla(new Edificio((redElectrica)));

        // ACT
        casillaConEdificio.recibirAtaque(1);

        // ASSERT
        assertEquals(4, redElectrica.getEnergia());
    }

    @Test
    public void unEdificioDestruidoNoVuelveARestarEnergiaSiLoAtacan() {
        // ARRANGE
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
        tablero.agregarCasilla(origen, new Casilla(new Suelo()));
        tablero.agregarCasilla(adyacente, new Casilla(new Edificio(redElectrica)));
        Unidad unidad = new MechCanon();
        tablero.posicionarUnidad(origen, unidad);

        // ACT
        List<Posicion> movimientosValidos = tablero.movimientosValidosDesde(origen);

        // ASSERT
        assertFalse(movimientosValidos.contains(adyacente));
    }

    @Test
    public void unEdificioSeDaniaDebidoAQueUnaUnidadChocaContraElla() {
        // ARRANGE
        tablero.agregarCasilla(origen, new Casilla(new Suelo()));
        tablero.agregarCasilla(adyacente, new Casilla(new Edificio(redElectrica)));
        Unidad unidad = new Saltador();
        tablero.posicionarUnidad(origen, unidad);

        // ACT
        tablero.empujarUnidad(origen, Direccion.DERECHA);

        // ASSERT
        assertEquals(4, redElectrica.getEnergia());
    }

    @Test
    public void edificioDestruidoPasaASerTransitable() {
        // ARRANGE
        tablero.agregarCasilla(origen, new Casilla(new Suelo()));
        tablero.agregarCasilla(adyacente, new Casilla(new Edificio(redElectrica)));
        Unidad unidad = new MechCanon();
        tablero.posicionarUnidad(origen, unidad);

        tablero.atacar(adyacente, 1);

        // ACT
        List<Posicion> movimientosValidos = tablero.movimientosValidosDesde(origen);
        tablero.empujarUnidad(origen, Direccion.DERECHA);

        // ASSERT
        assertTrue(movimientosValidos.contains(adyacente));
        assertTrue(tablero.unidadEstaEn(unidad, adyacente));
        assertFalse(tablero.estaOcupada(origen));
        assertEquals(3, unidad.vida());
    }

    @Test
    public void choqueContraMontaniaProduceDanio() {
        // ARRANGE
        tablero.agregarCasilla(origen, new Casilla(new Suelo()));
        tablero.agregarCasilla(adyacente, new Casilla(new Montania()));
        Unidad unidad = new MechCanon();
        tablero.posicionarUnidad(origen, unidad);

        // ACT
        tablero.empujarUnidad(origen, Direccion.DERECHA);

        // ASSERT
        assertTrue(tablero.unidadEstaEn(unidad, origen));
        assertFalse(tablero.estaOcupada(adyacente));
        assertEquals(2, unidad.vida());
    }

    @Test
    public void coqueContraUnidadProduceDanio() {
        // ARRANGE
        tablero.agregarCasilla(origen, new Casilla(new Suelo()));
        tablero.agregarCasilla(adyacente, new Casilla(new Suelo()));
        Unidad empujada = new MechCanon();
        Unidad obstaculo = new Saltador();
        tablero.posicionarUnidad(origen, empujada);
        tablero.posicionarUnidad(adyacente, obstaculo);

        // ACT
        tablero.empujarUnidad(origen, Direccion.DERECHA);

        // ASSERT
        assertTrue(tablero.unidadEstaEn(empujada, origen));
        assertTrue(tablero.unidadEstaEn(obstaculo, adyacente));
        assertEquals(2, empujada.vida());
        assertEquals(1, obstaculo.vida());
    }

    @Test
    public void empujarUnaUnidadAfueraDelTablero() {
        // ARRANGE
        Posicion borde = new Posicion(0, 0);
        tablero.agregarCasilla(borde, new Casilla(new Suelo()));
        Unidad unidad = new MechCanon();
        tablero.posicionarUnidad(borde, unidad);

        // ACT
        tablero.empujarUnidad(borde, Direccion.ARRIBA);

        // ASSERT
        assertTrue(tablero.unidadEstaEn(unidad, borde));
        assertEquals(3, unidad.vida());
    }

    @Test
    public void redElectricaNoEsMenorACero() {
        // ACT
        for (int i = 0; i < 6; i++) {
            Casilla casillaConEdificio = new Casilla(new Edificio(redElectrica));
            casillaConEdificio.recibirAtaque(1);
        }

        // ASSERT
        assertEquals(0, redElectrica.getEnergia());
        assertFalse(redElectrica.estaActivo());
    }

    @Test
    public void multipleEdificiosCompartenLaMismaRedElectricaAlRecibirDanio() {
        // ARRANGE
        Casilla casillaEdificio1 = new Casilla(new Edificio(redElectrica));
        Casilla casillaEdificio2 = new Casilla(new Edificio(redElectrica));

        // ACT
        casillaEdificio1.recibirAtaque(1);
        casillaEdificio2.recibirAtaque(1);

        // ASSERT
        assertEquals(3, redElectrica.getEnergia());
    }
}
