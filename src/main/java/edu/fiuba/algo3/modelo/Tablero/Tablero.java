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
import edu.fiuba.algo3.modelo.Tablero.comandos.*;

public class Tablero {
    private Map<Posicion, Casilla> casillas = new HashMap<>();
    private final Map<Posicion, Comando> movimientos = new HashMap<>();
    private Comando estadoDeshacer = new SinMovimiento();
    private final Queue<Vek> ataquesAnunciados = new ArrayDeque<>();
    private static final int CASILLAS_MAXIMAS = 64;
    private static final int TAMANIO = 8;

    public Tablero(File mapa) {
        LectorTableroCSV lector = new LectorTableroCSV();

        List<Casilla> listaCasillas = lector.transformarArchivo(mapa);

        if (listaCasillas.size() != CASILLAS_MAXIMAS) {
            throw new TableroInvalidoException("El tablero debe contener exactamente 64 casillas");
        }

        for (int i = 0; i < TAMANIO; i++){
            for(int j = 0; j < TAMANIO; j++){
                agregarCasilla(new Posicion(i, j), listaCasillas.get((i*TAMANIO+j)));
            }
        }
    }

    public Tablero() {
        this.casillas = new HashMap<>();
    }

    public void agregarCasilla(Posicion posicion, Casilla casilla) {
        casilla.ubicarseEn(posicion);
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

    public void verificarQueSePuedeAtacarDesde(Posicion origen) {
        if (!casillaEn(origen).suUnidadPuedeAtacar()) {
            throw new AccionNoPermitidaException("La unidad no puede atacar desde " + origen);
        }
    }

    public void contaminar(Posicion posicion) {
        casillaEn(posicion).contaminarUnidad();
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
            if (!casillaOrigen.suUnidadPuedeRecorrer(distancia + 1)) {
                continue;
            }
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
        Comando movimiento = casillaEn(origen).moverUnidadA(casillaEn(destino));
        movimientos.put(destino, estadoDeshacer.registrarEnTurno(movimiento));
    }

    public void deshacerMovimiento(Posicion posicionActual) {
        Comando movimiento = movimientos.getOrDefault(posicionActual, estadoDeshacer);
        movimiento.deshacer();
        estadoDeshacer = new ComandoMoverSinDeshacer();
        movimientos.replaceAll((pos, cmd) -> estadoDeshacer);
    }

    public void anunciarAtaqueVek(Vek vek, Direccion direccion) {
        vek.anunciarAtaque(direccion);
        ataquesAnunciados.add(vek);
    }

    public void ejecutarAtaquesAnunciados() {
        while (!ataquesAnunciados.isEmpty()) {
            Vek vek = ataquesAnunciados.remove();
            vek.ejecutarAtaque(this);
        }
    }

    public boolean estaOcupada(Posicion posicion) {
        return casillaEn(posicion).ocupada();
    }
}