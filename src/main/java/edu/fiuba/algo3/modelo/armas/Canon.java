package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class Canon extends Arma {

    public static final int DANIO = 1;

    public Canon() {
        super(DANIO);
    }

    // alcance: cualquier casilla en linea recta
    @Override
    protected boolean alcanza(Posicion origen, Posicion objetivo) {
        return origen.estaAlineadaCon(objetivo);
    }

    @Override
    protected void disparar(Tablero tablero, Posicion origen, Posicion objetivo, Direccion direccion) {
        Posicion impacto = tablero.buscarPrimerImpacto(origen, direccion);
        tablero.atacar(impacto, danio);
        tablero.empujarUnidad(impacto, direccion);
    }
}
