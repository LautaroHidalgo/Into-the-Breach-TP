package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class Artilleria extends Arma {

    public static final int DANIO = 1;
    public static final int DISTANCIA_MINIMA = 2;

    public Artilleria() {
        super(DANIO);
    }

    @Override
    protected boolean alcanza(Posicion origen, Posicion objetivo) {
        return origen.estaAlineadaCon(objetivo) && origen.distanciaACelda(objetivo) >= DISTANCIA_MINIMA;
    }

    @Override
    protected void disparar(Tablero tablero, Posicion origen, Posicion objetivo, Direccion direccion) {
        tablero.atacar(objetivo, danio);
        for (Direccion haciaAfuera : Direccion.values()) {
            tablero.empujarUnidad(objetivo.obtenerAdyacente(haciaAfuera), haciaAfuera);
        }
    }

}
