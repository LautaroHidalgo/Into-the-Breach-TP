package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Casilla.*;

import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void edificioDestruidoPasaASerTransitable() {
        // ARRANGE
        RedElectrica redElectrica = new RedElectrica();
        Tablero tablero = new Tablero();
        Posicion origen = new Posicion(1, 1);
        Posicion posEdificio = new Posicion(1, 2);
        tablero.agregarCasilla(origen, new Casilla(new Suelo()));
        tablero.agregarCasilla(posEdificio, new Casilla(new Edificio(redElectrica)));
        Unidad unidad = new MechCanon();
        tablero.posicionarUnidad(origen, unidad);

        tablero.atacar(posEdificio, 1);

        // ACT
        List<Posicion> movimientosValidos = tablero.movimientosValidosDesde(origen);
        tablero.empujarUnidad(origen, Direccion.DERECHA);

        // ASSERT
        assertTrue(movimientosValidos.contains(posEdificio));
        assertTrue(tablero.unidadEstaEn(unidad, posEdificio));
        assertFalse(tablero.estaOcupada(origen));
        assertEquals(3, unidad.vida());
    }

    @Test
    public void choqueContraMontaniaProduceDanio() {
        // ARRANGE
        Tablero tablero = new Tablero();
        Posicion origen = new Posicion(1, 1);
        Posicion posMontania = new Posicion(1, 2);
        tablero.agregarCasilla(origen, new Casilla(new Suelo()));
        tablero.agregarCasilla(posMontania, new Casilla(new Montania()));
        Unidad unidad = new MechCanon();
        tablero.posicionarUnidad(origen, unidad);

        // ACT
        tablero.empujarUnidad(origen, Direccion.DERECHA);

        // ASSERT
        assertTrue(tablero.unidadEstaEn(unidad, origen));
        assertFalse(tablero.estaOcupada(posMontania));
        assertEquals(2, unidad.vida());
    }

    @Test
    public void coqueContraUnidadProduceDanio() {
        // ARRANGE
        Tablero tablero = new Tablero();
        Posicion posEmpujada = new Posicion(1, 1);
        Posicion posObstaculo = new Posicion(1, 2);
        tablero.agregarCasilla(posEmpujada, new Casilla(new Suelo()));
        tablero.agregarCasilla(posObstaculo, new Casilla(new Suelo()));
        Unidad empujada = new MechCanon();
        Unidad obstaculo = new Saltador();
        tablero.posicionarUnidad(posEmpujada, empujada);
        tablero.posicionarUnidad(posObstaculo, obstaculo);

        // ACT
        tablero.empujarUnidad(posEmpujada, Direccion.DERECHA);

        // ASSERT
        assertTrue(tablero.unidadEstaEn(empujada, posEmpujada));
        assertTrue(tablero.unidadEstaEn(obstaculo, posObstaculo));
        assertEquals(2, empujada.vida());
        assertEquals(1, obstaculo.vida());
    }

    @Test
    public void empujarUnaUnidadAfueraDelTablero() {
        // ARRANGE
        Tablero tablero = new Tablero();
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
        // ARRANGE
        RedElectrica redElectrica = new RedElectrica();

        // ACT
        for (int i = 0; i < 6; i++) {
            Casilla casillaConEdificio = new Casilla(new Edificio(redElectrica));
            casillaConEdificio.recibirAtaque(1);
        }

        // ASSERT
        assertEquals(0, redElectrica.getEnergia());
        assertFalse(redElectrica.estaActivo());
    }
}
