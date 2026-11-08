package Clases;

public abstract class Terreno {

    //determina si una unidad puede ingresar a un terreno determinado.
    public abstract boolean permiteIngreso(Unidad unidad);

    //aplica sobre una unidad los efectos propios del terreno.
    public abstract void aplicarEfecto(Unidad unidad);
}
