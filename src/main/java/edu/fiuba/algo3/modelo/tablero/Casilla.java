package edu.fiuba.algo3.modelo.tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Casilla {
    private Unidad unidad;
    private boolean ocupada;

    public Casilla() {
        this.ocupada = false;
    }

    public boolean estaLibre() {
        return !ocupada;
    }

    public void ocupar(Unidad unidad) {
        this.unidad = unidad;
        this.ocupada = true;
    }

    public Unidad desocupar() {
        Unidad unidadSaliente = this.unidad;
        this.unidad = null;
        this.ocupada = false;
        return unidadSaliente;
    }
}