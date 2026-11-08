package Clases;

public class Casilla {

    private Terreno terreno;
    private Unidad ocupante;

    //crea una casilla con un terreno obligatorio y sin unidad
    public Casilla(Terreno terreno) {
        if (terreno == null) {
            throw new IllegalArgumentException("La casilla debe tener un terreno.");
        }

        this.terreno = terreno;
        this.ocupante = null;
    }

    //devuelve el terreno actual de la casilla
    public Terreno getTerreno() {
        return terreno;
    }

    //devuelve la unidad que ocupa la casilla
    public Unidad getOcupante() {
        return ocupante;
    }

    //indica si la casilla esta libre
    public boolean estaLibre() {
        return ocupante == null;
    }

    //coloca una unidad si la casilla esta desocupada
    public void recibirUnidad(Unidad unidad) {
        if (unidad == null) {
            throw new IllegalArgumentException("La unidad no puede ser null");
        }

        if (!estaLibre()) {
            throw new IllegalStateException("La casilla esta ocupada.");
        }

        this.ocupante = unidad;
    }

    //quita la unidad de la casilla
    public void desocupar() {
        this.ocupante = null;
    }
}