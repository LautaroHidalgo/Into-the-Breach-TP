package edu.fiuba.algo3.modelo.tablero;

import java.util.HashMap;
import java.util.Map;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Tablero {
    private final Map<Posicion, Unidad> unidades;

    public Tablero() {
        this.unidades = new HashMap<>();
    }

    public void inicializarUnidad(Unidad unidad, Posicion posicion) {
        unidades.put(posicion, unidad);
    }

    public void moverUnidad(Posicion origen, Posicion destino) {
        Unidad unidad = unidades.remove(origen);

        unidad.registrarMovimiento();

        unidades.put(destino, unidad);
    }

    public void deshacerMovimiento(Posicion posicionActual, Posicion posicionAnterior) {
        Unidad unidad = unidades.remove(posicionActual);
        unidad.deshacerMovimiento();
        unidades.put(posicionAnterior, unidad);
    }

    public boolean estaOcupada(Posicion posicion) {
        return unidades.containsKey(posicion);
    }

}