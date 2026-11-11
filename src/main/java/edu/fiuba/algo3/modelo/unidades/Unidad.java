package edu.fiuba.algo3.modelo.unidades;

public abstract class Unidad {

    private int vida;
    private final int movimiento;
    private EstadoSalud estadoSalud = new Sano();

    protected Unidad(int vida, int movimiento) {
        this.vida = vida;
        this.movimiento = movimiento;
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

    // solo para los tests retirar despues
    public int vida(){
        return vida;
    }
    // solo para los tests retirar despues
    public int movimiento(){
        return movimiento;
    }
}
