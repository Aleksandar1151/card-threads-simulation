package com.example.karta;

import com.example.figura.Figura;
import com.example.figura.Lebdeca;
import com.example.kontroleri.MainController;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.util.Random;
import java.util.Stack;

import static com.example.konstante.Konstante.*;
import static com.example.simulacija.Simulacija.matrica;
import static com.example.simulacija.Simulacija.putanja;


public class SpecijalnaKarta extends Karta {

    public SpecijalnaKarta(Image slika)
    {
        super(slika);
    }


    public void aktiviraj()
    {
        int red;
        int kolona;

        //Definisanje broja rupa
        Random rnd = new Random();
        //int brojRupa = 2+ rnd.nextInt(putanja.size()/2);

        int brojRupa = 1;

        Stack<Pair<Integer,Integer>> stackRupa1 = new Stack();
        Stack<Pair<Integer,Integer>> stackRupa2 = new Stack();


        //Kreirati stack na kojima se nalaze rupe
        while(brojRupa>0)
        {
            //int indeksLokacijeRupe = rnd.nextInt(putanja.size()-1);

            int indeksLokacijeRupe = 1;

            red =((Pair<Integer, Integer>)putanja.get(indeksLokacijeRupe)).getKey();
            kolona = ((Pair<Integer, Integer>)putanja.get(indeksLokacijeRupe)).getValue();

            stackRupa1.push(new Pair<>(red,kolona));
            Object polje = matrica[red][kolona];

            //Provjera da li se na polju gdje se pravi rupa, nalazi Lebdeća figura
            if(polje != null)
            {
                if(polje instanceof Figura)
                {
                    Figura figura = (Figura) polje;
                    if(!(figura instanceof Lebdeca))
                    {
                        figura.upalaURupu();
                        System.out.println("Figura upala u rupu! ");
                        matrica[red][kolona] = null;
                    }
                    else {
                        stackRupa1.pop();
                        System.out.println("Lebdeca figura nije upala u rupu. Red: " + red+ " Kolona: " +kolona);

                    }
                }
            }


            brojRupa--;

        }


        //Napraviti sve rupe
        while(!stackRupa1.empty())
        {
            Pair<Integer,Integer> par = stackRupa1.pop();
            stackRupa2.push(par);
            MainController.postavitiFiguruNaPolje(par.getKey(),par.getValue(), BOJA_RUPE, KONSTANTA_RUPA );
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}

        //Ocisti sve rupe
        while(!stackRupa2.empty())
        {
            Pair<Integer,Integer> par = stackRupa2.pop();
            MainController.postavitiFiguruNaPolje(par.getKey(),par.getValue(), BOJA_POLJA,String.valueOf(brojPoljaMatrice(par.getKey(),par.getValue())));
        }


    }
}
