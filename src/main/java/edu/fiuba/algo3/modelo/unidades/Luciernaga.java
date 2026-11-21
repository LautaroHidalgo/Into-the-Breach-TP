package edu.fiuba.algo3.modelo.unidades;
import edu.fiuba.algo3.modelo.armas.EscupitajoAcido;

public class Luciernaga extends Vek {

    public static final int VIDA = 3;
    public static final int MOVIMIENTO = 2;

    public Luciernaga(){
        super(VIDA, MOVIMIENTO, new EscupitajoAcido(), new Terrestre());
    }
}
