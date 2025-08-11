package com.example.figura;

import com.example.konstante.Konstante;
import com.example.kontroleri.MainController;
import com.example.simulacija.Simulacija;
import javafx.util.Pair;

import java.util.Random;

import static com.example.simulacija.Simulacija.*;

public class Duh extends Thread {



    public Duh()
    {

    }

    public void run()
    {
        Random rnd = new Random();
        while(!pauza)
        {

            int brojDijamanata = 2+rnd.nextInt(Konstante.DIMENZIJA_MATRICE-2);
            while(brojDijamanata>0)
            {
                int indeksLokacijaDijamanta = rnd.nextInt(putanja.size() - 1);

                Pair<Integer,Integer> lokacijaDijamanta = (Pair<Integer, Integer>) putanja.get(indeksLokacijaDijamanta);

                /*
                if(matrica[lokacijaDijamanta.getKey()][lokacijaDijamanta.getValue()] instanceof Figura )
                {
                    Figura figura = (Figura)matrica[lokacijaDijamanta.getKey()][lokacijaDijamanta.getValue()];
                    figura.pokupitiDijamant();
                }
                */


                brojDijamanata--;

                matrica[lokacijaDijamanta.getKey()][lokacijaDijamanta.getValue()] = "dijamant";

                MainController.postavitiFiguruNaPolje(lokacijaDijamanta.getKey(), lokacijaDijamanta.getValue(), Konstante.BOJA_DIJAMANTA, Konstante.KONSTANTA_DIJAMANT);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);}
        }
    }
}
