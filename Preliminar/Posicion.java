package Preliminar;
class Posicion{
    private int fila;
    private int columna;

    // public mostrarCasillasALasQueMePuedoMover(){
    //     for i in range taka taka
    // }

    public void desplazarA(Posicion posicion){
        posicion.desplazarseAMi(this);
    }

    private void desplazarseAMi(Posicion OtraPosicion){
        OtraPosicion.setFila(this.fila);
        OtraPosicion.setColumna(this.columna);
    } 

    private void setFila(int fila){
        this.fila = fila;
    }

    private void setColumna(int columna){
        this.columna = columna;
    }
}