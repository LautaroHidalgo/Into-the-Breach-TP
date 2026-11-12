package edu.fiuba.algo3.modelo.Casilla;

import edu.fiuba.algo3.modelo.unidades.SinUnidad;
import edu.fiuba.algo3.modelo.unidades.Unidad;

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

    public boolean puedeRecibirUnidad(Unidad unidadRecibida){
        return esTransitable() && !ocupada();
    }

    public boolean ocupada(){
        return unidad.ocupaCasilla();
    }

    public Unidad getUnidad(){
        return this.unidad;
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
}
