package edu.fiuba.algo3.modelo.unidades.vek;
import edu.fiuba.algo3.modelo.armas.EscupitajoAcido;
import edu.fiuba.algo3.modelo.unidades.desplazamiento.Terrestre;

public class Luciernaga extends Vek {

    public static final int VIDA = 3;
    public static final int MOVIMIENTO = 2;

    public Luciernaga(){
        super(VIDA, MOVIMIENTO, new EscupitajoAcido(), new Terrestre());
    }
}
