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

    public Tablero(File mapa) {
        LectorTableroCSV lector = new LectorTableroCSV();
        
        List<Casilla> listaCasillas = lector.transformarArchivo(mapa);

        if (listaCasillas.size() != 64) {
            throw new TableroInvalidoException("El tablero debe contener exactamente 64 casillas");
        }

        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                casillas.put(new Posicion(i, j), listaCasillas.get((i*8+j)));
            }
        }
    }

    public Tablero() {
        this.casillas = new HashMap<>();
    }

    public void agregarCasilla(Posicion posicion, Casilla casilla) {
        casillas.put(posicion,casilla);
    }

    public Boolean equals(Tablero otroTablero){
        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Posicion pos = new Posicion(i,j);
                if (!otroTablero.getCasilla(pos).esIgual(casillas.get(pos))){
                    return false;
                }
            }
        }
        return true;
    }

    private Casilla getCasilla(Posicion posicion){
        return casillas.get(posicion);
    }

    public void posicionarUnidad(Posicion posicion, MechCombate unidad){
        Casilla casilla = casillas.get(posicion);
        casilla.recibirUnidad(unidad);
    }

    public List<Posicion> movimientosValidosDesde(Posicion origen) {
        Casilla casillaOrigen = casillas.get(origen);
        if (casillaOrigen == null || !casillaOrigen.ocupada()) {
            throw new IllegalArgumentException("No hay unidad en el origen");
        }
        Unidad unidad = casillaOrigen.getUnidad();
        List<Posicion> movimientosValidos = new ArrayList<>();
        Queue<Posicion> pendientes = new ArrayDeque<>();
        Map<Posicion, Integer> distancias = new HashMap<>();
        pendientes.add(origen);
        distancias.put(origen, 0);
        while (!pendientes.isEmpty()) {
            Posicion actual = pendientes.remove();
            int distancia = distancias.get(actual);
            if (distancia == 3) {
                continue;
            }
            for (Posicion siguiente : obtenerAdyacentes(actual)) {
                Casilla destino = casillas.get(siguiente);
                if (destino == null || distancias.containsKey(siguiente)) {
                    continue;
                }
                if (!destino.puedeRecibirUnidad(unidad)) {
                    continue;
                }
                distancias.put(siguiente, distancia + 1);
                pendientes.add(siguiente);
                movimientosValidos.add(siguiente);
            }
        }
        return movimientosValidos;
    }

        public List<Posicion> obtenerAdyacentes(Posicion posicion){
        List<Posicion> adjacentes = new ArrayList<>();
        if (posicion.derecha() != null){
            adjacentes.add(posicion.derecha());
        }
        if (posicion.izquierda() != null){
            adjacentes.add(posicion.izquierda());
        }
        if (posicion.arriba() != null){
            adjacentes.add(posicion.arriba());
        }
        if (posicion.abajo() != null){
            adjacentes.add(posicion.abajo());
        }
        return adjacentes;
    }

}
