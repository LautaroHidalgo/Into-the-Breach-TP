class Posicion{
    private int fila;
    private int columna;

    // public mostrarCasillasALasQueMePuedoMover(){
    //     for i in range taka taka
    // }

    public void desplazarA(Posicion posicion){
        posicion.desplazarseAMi(this);
    }

    void private desplazarseAMi(Posicion OtraPosicion){
        OtraPosicion.setFila(this.fila);
        OtraPosicion.setColumna(this.columna);
    } 

    public void setFila(){
        this.fila = fila;
    }

    public void setColumna(){
        this.columna = columna;
    }
}