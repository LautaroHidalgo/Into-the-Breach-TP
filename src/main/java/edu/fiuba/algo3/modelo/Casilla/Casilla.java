package edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Casilla {
    private Terreno terreno;
    private Unidad unidad;

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
        return this.unidad != null;
    }

    public Unidad getUnidad(){
        return this.unidad;
    }

    public boolean esTransitable() {
        return terreno.esTransitable();
    }

    public void recibirAtaque(int danio){
        if (ocupada()) {
            this.unidad.recibirDanio(danio);
        }
    }
}
