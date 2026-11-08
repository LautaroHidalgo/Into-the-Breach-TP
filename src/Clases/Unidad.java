package Clases;

public abstract class Unidad {

    private int vida;

    //crea una unidad con una cantidad inicial de vida válida.
    public Unidad(int vida) {
        if (vida <= 0) {
            throw new IllegalArgumentException("La vida debe ser mayor a cero.");
        }
        this.vida = vida;
    }

    //descuenta puntos de vida sin permitir que sea negativo
    public void recibirDanio(int cantidad) {
        if(cantidad < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo.");
        }
        this.vida = Math.max(0, vida - cantidad);
    }

    //indica si esta viva
    public boolean estaViva() {
        return vida > 0;
    }
}
