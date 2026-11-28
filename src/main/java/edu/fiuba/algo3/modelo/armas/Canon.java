package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class Canon extends Arma {

    private static final int DANIO = 1;

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
