package Preliminar;
public class Casilla {
    private Posicion posicion;
    private Terreno terreno;
    private Unidad unidad;
    private RecepcionDeDanio recepcionDanio;

    public void recibirAtaque(Ataque ataque){
        this.unidad.recibirDaño(Ataque);
    }

    public void moverUnidad(Casilla casillaDestino){
        casillaDestino.recibirUnidad(this.unidad);
        this.desocupar();
    }

    public void recibirUnidad(Unidad unidadRecibida){
        this.unidad = unidadRecibida;
        this.ocupar();
    }

    public void desocupar(){
        this.unidad = null;
    }
}
