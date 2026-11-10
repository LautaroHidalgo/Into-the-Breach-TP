package edu.fiuba.algo3.modelo.Tablero;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.Casilla.*;


public class LectorTableroCSV {

    public List<Casilla> transformarArchivo(File mapa){
        if (mapa == null) {
            throw new IllegalArgumentException("Mapa inexistente");
        }

        List<Casilla> listaCasillas = new ArrayList<Casilla>();

        try (BufferedReader lector = new BufferedReader(new FileReader(mapa))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] casillas = linea.split(",");

                for (String tipo : casillas) {
                    if ("S".equals(tipo)) {
                        listaCasillas.add(new Casilla(new Suelo()));
                    }
                    if ("A".equals(tipo)) {
                        listaCasillas.add(new Casilla(new Agua()));
                    }
                    if ("E".equals(tipo)) {
                        listaCasillas.add(new Casilla(new Edificio()));
                    }
                    if ("M".equals(tipo)) {
                        listaCasillas.add(new Casilla(new Montania()));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el mapa: " + e.getMessage());
        }
        return listaCasillas;
    }
}