package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Tablero.*;

public abstract class Unidad {
    public static final int DANIO_COLISION = 1;

    private int vida;
    private final int movimiento;
    private EstadoSalud estadoSalud = new Sano();

    private boolean yaSeMovio = false;
    private boolean yaAtaco = false;
    private boolean yaDeshizoMovimiento = false;

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
        if (yaSeMovio) {
            throw new MovimientoNoPermitidoException("La unidad ya se movió en este turno");
        }
        yaSeMovio = true;
    }

    public void deshacerMovimiento() {
        if (yaAtaco) {
            throw new AccionNoPermitidaException("No se puede deshacer el movimiento después de atacar");
        }
        if (yaDeshizoMovimiento) {
            throw new MovimientoNoPermitidoException("No se puede deshacer el movimiento más de una vez por turno.");
        }
        if (!yaSeMovio) {
            throw new MovimientoNoPermitidoException("No hay ningún movimiento para deshacer");
        }
        yaSeMovio = false;
        yaDeshizoMovimiento = true;
    }

    public void registrarAtaque() {
        if (yaAtaco) {
            throw new AccionNoPermitidaException("La unidad ya atacó en este turno");
        }
        yaAtaco = true;
        yaSeMovio = true;
    }

    public void reiniciarTurno() {
        yaSeMovio = false;
        yaAtaco = false;
        yaDeshizoMovimiento = false;
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
