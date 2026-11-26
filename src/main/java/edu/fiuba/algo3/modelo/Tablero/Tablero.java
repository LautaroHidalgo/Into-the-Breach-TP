package edu.fiuba.algo3.modelo.Tablero;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import edu.fiuba.algo3.modelo.Casilla.*;
import edu.fiuba.algo3.modelo.unidades.*;

public class Tablero {
    private Map<Posicion, Casilla> casillas = new HashMap<>();
    //cambio: el tablero sabe de dónde vino cada unidad que se movió (posición actual -> origen)
    private final Map<Posicion, Posicion> origenesDeMovimientos = new HashMap<>();
    private final RedElectrica redElectrica;
    private static final int CASILLAS_MAXIMAS = 64;
    private static final int TAMANIO = 8;

    public Tablero(File mapa, RedElectrica redElectrica) {
        this.redElectrica = redElectrica;
        LectorTableroCSV lector = new LectorTableroCSV();
        
        List<Casilla> listaCasillas = lector.transformarArchivo(mapa, redElectrica);

        if (listaCasillas.size() != CASILLAS_MAXIMAS) {
            throw new TableroInvalidoException("El tablero debe contener exactamente 64 casillas");
        }

        for (int i = 0; i < TAMANIO; i++){
            for(int j = 0; j < TAMANIO; j++){
                casillas.put(new Posicion(i, j), listaCasillas.get((i*TAMANIO+j)));
            }
        }
    }

    public Tablero(RedElectrica redElectrica) {
        this.casillas = new HashMap<>();
        this.redElectrica = redElectrica;
    }

    public void agregarCasilla(Posicion posicion, Casilla casilla) {
        casillas.put(posicion,casilla);
    }

    public boolean esIgualA(Tablero otro) {
        if (casillas.size() != otro.casillas.size()) {
            return false;
        }
        for (Posicion posicion : casillas.keySet()) {
            Casilla miCasilla = casillas.get(posicion);
            Casilla suCasilla = otro.casillaEn(posicion);
            if (!suCasilla.esIgual(miCasilla)) {
                return false;
            }
        }
        return true;
    }

    public void posicionarUnidad(Posicion posicion, Unidad unidad) {
        if (!estaDentro(posicion)) {
            throw new PosicionFueraDelTableroException(posicion);
        }
        casillaEn(posicion).recibirUnidad(unidad);
    }

    private Casilla casillaEn(Posicion posicion) {
        return casillas.getOrDefault(posicion, new Casilla(new FueraDelTablero()));
    }

    public boolean estaDentro(Posicion posicion) {
        return casillas.containsKey(posicion);
    }

    public void atacar(Posicion posicion, int danio) {
        casillaEn(posicion).recibirAtaque(danio);
    }

    public void empujarUnidad(Posicion origen, Direccion direccion) {
        casillaEn(origen).empujarUnidadHacia(casillaEn(origen.obtenerAdyacente(direccion)));
    }

    public Posicion buscarPrimerImpacto(Posicion origen, Direccion direccion) {
        Posicion actual = origen.obtenerAdyacente(direccion);
        while (!casillaEn(actual).detieneDisparos()) {
            actual = actual.obtenerAdyacente(direccion);
        }
        return actual;
    }

    public boolean unidadEstaEn(Unidad unidad, Posicion posicion) {
        return casillaEn(posicion).contiene(unidad);
    }

    public List<Posicion> movimientosValidosDesde(Posicion origen) {
        //cambio: usa casillaEn (sin null) fuera del tablero la casilla tampoco está ocupada
        Casilla casillaOrigen = casillaEn(origen);
        if (!casillaOrigen.ocupada()) {
            throw new IllegalArgumentException("No hay unidad en el origen");
        }
        List<Posicion> movimientosValidos = new ArrayList<>();
        Queue<Posicion> pendientes = new ArrayDeque<>();
        Map<Posicion, Integer> distancias = new HashMap<>();
        pendientes.add(origen);
        distancias.put(origen, 0);
        while (!pendientes.isEmpty()) {
            Posicion actual = pendientes.remove();
            int distancia = distancias.get(actual);
            //cambio: la casilla de origen le pregunta a su unidad (el tablero ya no usa getUnidad)
            if (!casillaOrigen.suUnidadPuedeRecorrer(distancia + 1)) {
                continue;
            }
            //cambio: recorre las 4 direcciones fuera del tablero casillaEn devuelve FueraDelTablero
            // que no se puede transitar así que no hacen falta los null ni los if de los bordes
            for (Direccion direccion : Direccion.values()) {
                Posicion siguiente = actual.obtenerAdyacente(direccion);
                if (distancias.containsKey(siguiente)
                        || !casillaOrigen.suUnidadPuedeEntrarEn(casillaEn(siguiente))) {
                    continue;
                }
                distancias.put(siguiente, distancia + 1);
                pendientes.add(siguiente);
                movimientosValidos.add(siguiente);
            }
        }
        return movimientosValidos;
    }

    public void moverUnidad(Posicion origen, Posicion destino) {
        if (!movimientosValidosDesde(origen).contains(destino)) {
            throw new MovimientoNoPermitidoException("La unidad no puede moverse a " + destino);
        }
        casillaEn(origen).moverUnidadA(casillaEn(destino));
        origenesDeMovimientos.put(destino, origen);
    }

    public void deshacerMovimiento(Posicion posicionActual) {
        if (!origenesDeMovimientos.containsKey(posicionActual)) {
            throw new MovimientoNoPermitidoException("No hay ningún movimiento para deshacer");
        }
        Posicion origen = origenesDeMovimientos.get(posicionActual);
        casillaEn(posicionActual).deshacerMovimientoHacia(casillaEn(origen));
        origenesDeMovimientos.remove(posicionActual);
    }

    public boolean estaOcupada(Posicion posicion) {
        return casillaEn(posicion).ocupada();
    }



}
