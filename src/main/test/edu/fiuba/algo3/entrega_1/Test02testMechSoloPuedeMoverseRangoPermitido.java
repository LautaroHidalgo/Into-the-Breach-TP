package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.casilla.*;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.unidades.mech.MechCombate;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Test02testMechSoloPuedeMoverseRangoPermitido {
    
    @Test
    public void testMechSoloPuedeMoverseRangoPermitido() {
        
        File mapa = new File("src/main/mapas/mapaTest2.csv");
        Tablero tablero = new Tablero(mapa, new RedElectrica());
        
        MechCombate mech = new MechCombate();
        Posicion posicionMech = new Posicion(2,3);
        tablero.posicionarUnidad(posicionMech, mech);
        List<Posicion> movimientos =  tablero.movimientosValidosDesde(posicionMech);

        List<Posicion> listaEsperada = new ArrayList<>();

        listaEsperada.add(new Posicion(0, 4));
        listaEsperada.add(new Posicion(1, 2));
        listaEsperada.add(new Posicion(1, 3));
        listaEsperada.add(new Posicion(1, 4));
        listaEsperada.add(new Posicion(1, 5));
        listaEsperada.add(new Posicion(2, 1));
        listaEsperada.add(new Posicion(2, 2));
        listaEsperada.add(new Posicion(3, 1));
        listaEsperada.add(new Posicion(3, 2));
        listaEsperada.add(new Posicion(3, 3));
        listaEsperada.add(new Posicion(4, 2));

        assertEquals(new HashSet<>(listaEsperada),new HashSet<>(movimientos));
    }
}