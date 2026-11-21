package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.unidades.estados.*;

public abstract class Unidad {
    public static final int DANIO_COLISION = 1;

    private int vida;
    private final int movimiento;
    private EstadoSalud estadoSalud = new Sano();
    private EstadoTurno estadoTurno = new Disponible();
    private final Desplazamiento desplazamiento;

    protected Unidad(int vida, int movimiento, Desplazamiento desplazamiento) {
        this.vida = vida;
        this.movimiento = movimiento;
        this.desplazamiento = desplazamiento;
    }

    public void recibirDanio(int cantidad) {
        vida = Math.max(0, vida - estadoSalud.calcularDanio(cantidad));
    }

    public void recibirContaminacion() {
        estadoSalud = estadoSalud.contaminar();
    }

    public boolean estaViva() {
        return vida > 0;
    }

    //la unidad termina en Ácido según su desplazamiento se contamina o no
    public void terminarEnAcido() {
        desplazamiento.terminarEnAcido(this);
    }

    //la unidad termina en Agua según su desplazamiento le afecta o no
    public void terminarEnAgua() {
        desplazamiento.terminarEnAgua(this);
    }

    public boolean puedeAtacarDesdeElAgua() {
        return desplazamiento.puedeAtacarDesdeElAgua();
    }

    // una unidad terrestre cae al Agua. Por defecto no le pasa nada (Mech), el Vek lo redefine
    public void caerAlAgua() {
    }

    protected void morir() {
        vida = 0;
    }

    public boolean ocupaCasilla() {
        return true;
    }

    public void serEmpujada(Casilla origen, Casilla destino) {
        destino.recibirUnidadEmpujada(this, origen);
    }

    public void chocarCon(Unidad empujada, Casilla origen, Casilla destino) {
        empujada.recibirDanio(DANIO_COLISION);
        recibirDanio(DANIO_COLISION);
    }

    public void registrarMovimiento() {
        estadoTurno = estadoTurno.registrarMovimiento();
    }

    public void deshacerMovimiento() {
        estadoTurno = estadoTurno.deshacerMovimiento();
    }

    public void verificarQuePuedeAtacar() {
        estadoTurno.verificarQuePuedeAtacar();
    }

    public void registrarAtaque() {
        estadoTurno = estadoTurno.registrarAtaque();
    }

    public void reiniciarTurno() {
        estadoTurno = new Disponible();
    }

    public int vida() {
        return vida;
    }

    public boolean puedeRecorrer(int casillas) {
        return casillas <= movimiento;
    }
}