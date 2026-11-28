package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Tablero.Direccion;
import edu.fiuba.algo3.modelo.Tablero.Posicion;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public abstract class DisparoDirecto extends Arma {

    protected DisparoDirecto(int danio) {
        super(danio);
    }

    @Override
    protected boolean alcanza(Posicion origen, Posicion objetivo) {
        return origen.estaAlineadaCon(objetivo);
    }

    @Override
    protected void disparar(Tablero tablero, Posicion origen, Posicion objetivo, Direccion direccion) {
        Posicion impacto = tablero.buscarPrimerImpacto(origen, direccion);
        tablero.atacar(impacto, danio);
        aplicarEfectoSobre(tablero, impacto, direccion);
    }

    protected abstract void aplicarEfectoSobre(Tablero tablero, Posicion posicion, Direccion direccion);
}
