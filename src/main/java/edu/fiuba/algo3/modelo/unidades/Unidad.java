package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.unidades.estados.*;

public abstract class Unidad {
    public static final int DANIO_COLISION = 1;

    private int vida;
    private final int movimiento;
    private EstadoSalud estadoSalud = new Sano();
    private EstadoTurno estadoTurno = new Disponible();

    protected Unidad(int vida, int movimiento) {
        this.vida = vida;
        this.movimiento = movimiento;
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