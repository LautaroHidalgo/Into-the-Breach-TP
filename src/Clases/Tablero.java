package Clases;

import java.util.Map;
import java.util.HashMap;

public class Tablero {

    private final Map<Posicion, Casilla> casillas;

    public Tablero(Map<Posicion, Casilla> casillas) { //Recibe el mapa de casillas y verifica que forme un tablero de 8x8.
        if (casillas == null || casillas.size() != 64) { //comprobamos que el tamañio no sea mayor a 64.
            throw new IllegalArgumentException("El tablero debe contener 64 casillas.");
        }

        //verifica que existen todas las posiciones y que tengan una casilla.
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Posicion posicion = new Posicion(fila, columna);

                if (!casillas.containsKey(posicion)
                        || casillas.get(posicion) == null) {
                    throw new IllegalArgumentException("Falta una casilla valida en el tablero.");
                }
            }
        }
        //copiamos las asociaciones para proteger la estructura interna.
        this.casillas = new HashMap<>(casillas);
    }

    //devuelve si la posicion pertenece al tablero.
    public boolean contiene(Posicion posicion) {
        return posicion != null && casillas.containsKey(posicion);
    }

    //devuelve la casilla correspondiente a una posicion válida.
    public Casilla obtenerCasilla(Posicion posicion) {
        if (!contiene(posicion)) {
            throw new IllegalArgumentException("Clases.Posicion fuera del tablero");
        }

        return casillas.get(posicion);
    }
}