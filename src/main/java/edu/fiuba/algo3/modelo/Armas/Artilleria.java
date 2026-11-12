package edu.fiuba.algo3.modelo.Armas;
import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.DispararCasillaAdyacente;

public final class Artilleria extends Arma {
    @Override
    public void atacar(Posicion posicionMech, Posicion objetivo, Tablero tablero){
        // Calculo la distancia a 1 del mech (porque no puede disparar adyante)
        // y la guardo en una variable
        int distancia = posicionMech.distanciaACelda(objetivo);
        if (distancia == 1) {
            throw new DispararCasillaAdyacente("El mech no puede disparar a una casilla adyacente");
        }

        tablero.recibirAtaque(objetivo, 1);

        // Obtengo los adyacentes al objetivo
        Posicion posicionNorte = objetivo.arriba();
        Posicion posicionSur = objetivo.abajo();
        Posicion posicionEste = objetivo.derecha();
        Posicion posicionOeste = objetivo.izquierda();

        // Me fijo que cada posición no sea nula (fuera del tablero) y si no lo es, la empujo

        if (posicionNorte != null) {
            tablero.empujarUnidad(posicionNorte, Direccion.ARRIBA);
        }
        if (posicionSur != null ) {
            tablero.empujarUnidad(posicionSur, Direccion.ABAJO);
        }
        if (posicionEste != null ){
            tablero.empujarUnidad(posicionEste, Direccion.DERECHA);
        }
        if (posicionOeste != null ) {
            tablero.empujarUnidad(posicionOeste, Direccion.IZQUIERDA);
        }
    }
}