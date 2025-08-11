package com.example.simulacija;

import com.example.figura.Obicna;
import com.example.figura.Superbrza;
import com.example.igrac.Igrac;
import com.example.karta.Karta;
import com.example.karta.ObicnaKarta;
import com.example.karta.SpecijalnaKarta;
import com.example.konstante.Konstante;
import com.example.kontroleri.MainController;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.io.File;
import java.util.*;

import com.example.figura.Figura;

import static com.example.konstante.Konstante.DIMENZIJA_MATRICE;
import static com.example.konstante.Konstante.brojPoljaMatrice;

public class Simulacija extends Thread {

    public static Object[][] matrica;
    public static Queue igraci = new LinkedList<Igrac>();
    public static Queue spil = new LinkedList<Karta>();
    public static List putanja = new ArrayList<Pair<Integer,Integer>>();
    public static Igrac trenutniIgrac;
    public static boolean pauza = false;

    public Simulacija(int velicinaMatrice)
    {
        matrica = new Object[velicinaMatrice][velicinaMatrice];
        spil = kreirajSpil();
        igraci = kreirajIgrace();
        putanja = kreirajPutanju();



        System.out.print(" Lista polja: ");
        for(int i=0;i<putanja.size();i++){
            Pair<Integer,Integer> par = (Pair<Integer,Integer>)putanja.get(i);
            //System.out.println("--Red: "+ par.getKey()+ " Kolona: " +par.getValue());
            System.out.print(" "+ String.valueOf(brojPoljaMatrice(par.getKey(),par.getValue() ) ) );
        }

    }

    private Queue kreirajSpil() {

        List<Karta> listaKarata = new ArrayList<>();
        Queue queue = new LinkedList<Karta>();
        int brojKarte = 1;

        do {
            for(int i = 0; i < Konstante.BROJ_OBICNIH_KARATA; i++)
            {
                File file = new File("cards/card_"+brojKarte+".png");
                Image image = new Image(file.toURI().toString());
                listaKarata.add( new ObicnaKarta(image,brojKarte) );
            }
            brojKarte++;
        }while(brojKarte<5);

        for(int i = 0; i < Konstante.BROJ_SPECIJALNIH_KARATA; i++)
        {
            File file = new File("cards/card_joker.png");
            Image image = new Image(file.toURI().toString());
           // listaKarata.add( new SpecijalnaKarta(image) );
        }

        Collections.shuffle(listaKarata);

        for(Karta karta : listaKarata)
            queue.add(karta);

        return queue;
    }

    private Queue kreirajIgrace()
    {
        Queue listaIgraca = new LinkedList<Igrac>();

        for(int i = 1; i<Konstante.BROJ_IGRACA+1;i++)
        {
            switch (i)
            {
                case 1:
                {
                    listaIgraca.add( new Igrac(Integer.toString(i),"red") );
                    break;
                }
                case 2:
                {
                    listaIgraca.add( new Igrac(Integer.toString(i),"yellow") );
                    break;
                }
                case 3:
                {
                    listaIgraca.add( new Igrac(Integer.toString(i),"green") );
                    break;
                }
                case 4:
                {
                    listaIgraca.add( new Igrac(Integer.toString(i),"blue") );
                    break;
                }
            }
        }
        return listaIgraca;
    }

    private ArrayList kreirajPutanju()
    {
        ArrayList<Pair<Integer,Integer>> listaPredjenihPolja = new ArrayList<>();
        Pair<Integer,Integer> pair;
        Pair<Integer,Integer> staroPolje = null;

        //int dimenzija = 7;

        int sredina = Konstante.DIMENZIJA_MATRICE/2 ;

        System.out.println("Sredina: " + sredina);
        int krug = 1;

        int kolona = sredina;
        int red=0;
        int kvadrant = 2;
        do {
           // MainController.indikatorLabelIgrac("crvena");
            //Evidentiranje predjenih polja
            pair = new Pair<>(red,kolona);

            /*
            if(staroPolje != null )
               MainController.makeLabelOnPosition(staroPolje.getKey(),staroPolje.getValue(), Konstante.BOJA_BIJELA,String.valueOf((staroPolje.getKey()*Konstante.DIMENZIJA_MATRICE)+(staroPolje.getValue()+1)));
            */


            if(listaPredjenihPolja.contains(pair))
            {
                red++;
                krug++;

                pair = new Pair<>(red,kolona);
            }




            //MainController.makeLabelOnPosition(red,kolona, Konstante.BOJA_CRVENA,"X");

            /*
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/

            listaPredjenihPolja.add(pair);

            System.out.println(red+ " == " + (sredina) + " ---- "+kolona +" == "+ (sredina-1));
            /*if(red == (sredina-1) && kolona == (sredina))
            {
                break;
            }*/

            if(DIMENZIJA_MATRICE % 2 == 0)
            {
                if(red == (sredina-1) && kolona == (sredina))
                {
                    red++;
                    kolona--;
                    pair = new Pair<>(red,kolona);
                    listaPredjenihPolja.add(pair);
                    break;
                }
            }else {

                if(red == sredina && kolona == sredina)
                {
                    break;
                }

            }






            staroPolje = new Pair<>(red,kolona);

            //kretanje
            switch(kvadrant)
            {
                case 1:
                {
                    red--;
                    kolona++;

                    break;
                }
                case 2:
                {
                    red++;
                    kolona++;

                    break;
                }

                case 3:
                {
                    red++;
                    kolona--;
                    break;
                }

                case 4:
                {
                    red--;
                    kolona--;
                    break;
                }

            }

            //PROVJERA KVADRANTA

            if(DIMENZIJA_MATRICE % 2 == 0)
            {
                //kraj 1. kvadranta
                if(red == (krug-1) && kolona == sredina )
                {
                    kvadrant = 2;
                    System.out.println("KVADRANT = " + kvadrant);
                }
                // ------ ovdje je problem. Prekid treba da se desi na 3,7, a do njega nikad ne dođe jer su parovi 3,6 i 4,7.
                //kraj 2.kvadranta
                if(red == (sredina-1) && (kolona) == (Konstante.DIMENZIJA_MATRICE-krug)  )
                {
                    kvadrant = 3;
                    System.out.println("KVADRANT = " + kvadrant);
                }

                //kraj 3.kvadranta
                if((red)==(Konstante.DIMENZIJA_MATRICE-krug) && kolona == (sredina-1))
                {
                    kvadrant = 4;
                    System.out.println("KVADRANT = " + kvadrant);
                }

                //kraj 4.kvadranta
                if(red==sredina && kolona == (krug-1))
                {
                    kvadrant = 1;
                    System.out.println("KVADRANT = " + kvadrant);
                }
            }else {
                //kraj 1. kvadranta
                if(red == (krug-1) && kolona == sredina )
                {
                    kvadrant = 2;
                }

                //kraj 2.kvadranta
                if(red == sredina && kolona == (Konstante.DIMENZIJA_MATRICE-krug)  )
                {
                    kvadrant = 3;
                }

                //kraj 3.kvadranta
                if(red==(Konstante.DIMENZIJA_MATRICE-krug) && kolona == sredina)
                {
                    kvadrant = 4;
                }

                //kraj 4.kvadranta
                if(red==sredina && kolona == (krug-1))
                {
                    kvadrant = 1;
                }
            }

        }while(true && kolona<10 && red < 10);

        System.out.println("Napravljena lista polja.");



        return listaPredjenihPolja;
    }


    //Ovdje ide simulacija
    public void run()
    {
        //kreirajPutanju();

        //matrica[2][5] = new Obicna("blue");
        //matrica[2][5] = "dijamant";

        while(!pauza)
        {
            if(igraci.isEmpty()){
                System.out.println("Nema vise igraca.");
                pauza = true;
                break;
            }

            // Odabir igrača
            trenutniIgrac = (Igrac)igraci.remove();
            //System.out.println("-----IZABRAN IGRAC: " + trenutniIgrac.getIme());
            //igraci.add(trenutniIgrac);

            // Preuzimanje figure
            Figura trenutnaFigura = trenutniIgrac.figure.peek();
            System.out.println("Preuzela se nova figura. ");

            if(trenutnaFigura.getURupi())
            {
                trenutniIgrac.figure.pop();
            }

            //Provjera da li igrač ima figure
            if(trenutniIgrac.figure.empty()){
                System.out.println("Igrac "+trenutniIgrac.getIme()+" je izgubio.");
                continue;
            }
            else{
                trenutnaFigura = trenutniIgrac.figure.peek();
                igraci.add(trenutniIgrac);
            }



            // Izvlačenje karte
            Karta trenutnaKarta = (Karta) spil.remove();
            spil.add(trenutnaKarta);
            MainController.postavitiKartu(trenutnaKarta);

            // Provjera tipa karte
            if(trenutnaKarta instanceof ObicnaKarta)
            {
                if(trenutnaFigura instanceof Superbrza)
                {
                    trenutnaFigura.kretanje(((ObicnaKarta) trenutnaKarta).getBrojKarte()*2 + trenutnaFigura.getBrzina());
                }
                else {
                    trenutnaFigura.kretanje(((ObicnaKarta) trenutnaKarta).getBrojKarte());
                }
            }

            if(trenutnaKarta instanceof SpecijalnaKarta)
            {
                //((SpecijalnaKarta) trenutnaKarta).aktiviraj();
            }




            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);}
        }

        System.out.println("KRAJ IGRE ");
    }



}
