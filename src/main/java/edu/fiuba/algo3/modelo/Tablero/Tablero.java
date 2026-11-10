package edu.fiuba.algo3.modelo.Tablero;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.Casilla.*;

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

    public void posicionarUnidad(Posicion posicion, Unidad unidad){
        Casilla casilla = casillas.get(posicion);
        casilla.recibirUnidad(unidad);
    }
}
