package edu.fiuba.algo3.modelo.unidades;

public class Unidad {

    private int vida;
    private EstadoSalud estadoSalud = new Sano();

    protected Unidad(int vida) {
        this.vida = vida;
    }

    public void recibirDanio(int cantidad) {
        vida = Math.max(0, vida - estadoSalud.calcularDanio(cantidad));
    }

    public void recibirContaminacion(){
        estadoSalud = estadoSalud.contaminar();
    }

    public boolean estaViva() {
        return vida > 0;
    }

    public int vida(){
        return vida;
    }
}
