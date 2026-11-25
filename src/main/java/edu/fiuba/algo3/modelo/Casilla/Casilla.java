package edu.fiuba.algo3.modelo.Casilla;

import edu.fiuba.algo3.modelo.unidades.SinUnidad;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.Tablero.comandos.*;

public class Casilla {
    private Terreno terreno;
    //cambio: una casilla vacía tiene SinUnidad en vez de null
    private Unidad unidad = new SinUnidad();

    public Casilla(Terreno terreno){
        this.terreno = terreno;
    }

    public boolean esIgual(Casilla otraCasilla){
        return otraCasilla.compararme(terreno);
    } 

    private boolean compararme(Terreno otroTerreno){
        return terreno.esIgual(otroTerreno);
    }

    public void recibirUnidad(Unidad unidadRecibida){
        this.unidad = unidadRecibida;
    }

    //ubica la unidad SIN aplicar el efecto del terreno
    public boolean puedeRecibirUnidad(Unidad unidadRecibida){
        return esTransitable() && !ocupada();
    }

    // la unidad termina acá por movimiento o empuje así que se aplica el efecto del terreno
    public void recibirUnidadQueLlega(Unidad unidadQueLlega) {
        this.unidad = unidadQueLlega;
        this.terreno.aplicarEfecto(unidadQueLlega);
    }

    // el terreno decide si su unidad puede atacar desde acá (en el Agua la terrestre no)
    public boolean suUnidadPuedeAtacar() {
        return terreno.permiteAtacar(unidad);
    }

    //contamina a la unidad que ocupa la casilla (si está vacía SinUnidad no hace nada)
    public void contaminarUnidad() {
        unidad.recibirContaminacion();
    }

    public boolean ocupada(){
        return unidad.ocupaCasilla();
    }

    public boolean suUnidadPuedeRecorrer(int casillas) {
        return unidad.puedeRecorrer(casillas);
    }

    public boolean suUnidadPuedeEntrarEn(Casilla destino) {
        return destino.puedeRecibirUnidad(unidad);
    }

    public boolean esTransitable() {
        return terreno.esTransitable();
    }

    public void liberar() {
        unidad = new SinUnidad();
    }

    //el ataque le llega a lo que haya en la casilla, Si está vacía SinUnidad no hace nada
    public void recibirAtaque(int danio) {
        unidad.recibirDanio(danio);
        terreno.recibirAtaque(danio);
    }

    public void empujarUnidadHacia(Casilla destino) {
        unidad.serEmpujada(this, destino);
    }

    //la unidad que ocupa esta casilla decide si hay choque, si está vacía decide el terreno
    public void recibirUnidadEmpujada(Unidad empujada, Casilla origen) {
        unidad.chocarCon(empujada, origen, this);
    }

    //la casilla está vacía así que el terreno decide si la unidad entra o choca
    public void dejarPasar(Unidad empujada, Casilla origen) {
        terreno.recibirUnidadEmpujada(empujada, origen, this);
    }


    public boolean detieneDisparos() {
        return unidad.ocupaCasilla() || terreno.bloqueaDisparos();
    }


    public boolean contiene(Unidad otra) {
        return unidad == otra;
    }

    public Comando moverUnidadA(Casilla destino) {
        return unidad.moverseEntre(this, destino);
    }

    public void deshacerMovimientoHacia(Casilla origen) {
        Unidad movida = unidad;
        movida.deshacerMovimiento();
        liberar();
        origen.recibirUnidad(movida);
    }
}
