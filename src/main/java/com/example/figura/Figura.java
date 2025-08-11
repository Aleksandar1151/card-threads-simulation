package com.example.figura;

import com.example.karta.SpecijalnaKarta;
import com.example.konstante.Konstante;
import com.example.kontroleri.MainController;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.simulacija.Simulacija.matrica;
import static com.example.simulacija.Simulacija.putanja;
import static com.example.simulacija.Simulacija.trenutniIgrac;

public class Figura {

    int redniBroj;
    String tip;
    String boja;
    int brzina;
    int indeksLokacije;
    boolean uRupi;
    public  List predjenaPolja = new ArrayList<Pair<Integer,Integer>>();
    int vrijemeKretanja;

    public Figura(int redniBroj, String tip, String boja, int brzina)
    {
        this.redniBroj = redniBroj;
        this.tip = tip;
        this.boja = boja;
        this.brzina = brzina;
        this.indeksLokacije = 0;
        this.uRupi = false;
        this.vrijemeKretanja = 0;
    }

    //Geteri
    public String getBoja() { return boja; }
    public int getBrzina() {return brzina;}
    public int getIndeksLokacije() {return indeksLokacije;}
    public boolean getURupi() {return uRupi;}
    public int getRedniBroj() {return redniBroj;}

    public String getTip(){return tip;}


    //Seteri
    public void setBoja(String boja){this.boja = boja;}
    public void setBrzina(int brzina){this.brzina = brzina;}
    public void setIndeksLokacije(int indeksLokacije){this.indeksLokacije = indeksLokacije;}
    public void setuRupi(boolean uRupi){this.uRupi = uRupi;}

    //Funkcije
    public void upalaURupu()
    {
        this.uRupi = true;
    }

    public void pokupitiDijamant()
    {
        this.brzina++;
        System.out.println("------------Dijamant pokupljen--Brzina: " + brzina );
    }

    public void kretanje(int brojKarte)
    {

        if(indeksLokacije == 0)
        {
            predjenaPolja.add(putanja.get(indeksLokacije));
            MainController.postavitiFiguruNaPolje(0, (Konstante.DIMENZIJA_MATRICE/2), boja, tip);
            matrica[((Pair<Integer, Integer>)putanja.get(indeksLokacije)).getKey()][((Pair<Integer, Integer>)putanja.get(indeksLokacije)).getValue()] = this;

            try {
                Thread.sleep(1000);
                vrijemeKretanja++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);}


        }

        //indeksLokacije += (brojKarte+brzina);
        indeksLokacije += brzina;

        if(indeksLokacije < putanja.size())
        {

            //Preskače polje na kome se nalazi druga figura
            if("dijamant".equals(matrica[((Pair<Integer, Integer>)putanja.get(indeksLokacije)).getKey()][((Pair<Integer, Integer>)putanja.get(indeksLokacije)).getValue()]))
            {

            }else {
                while(matrica[((Pair<Integer, Integer>)putanja.get(indeksLokacije)).getKey()][((Pair<Integer, Integer>)putanja.get(indeksLokacije)).getValue()] != null )
                {
                    indeksLokacije++;
                }
            }


            //Obrisati polje na staroj lokaciji figure
            obrisatiStaroPolje();
            int stariRed = ((Pair<Integer, Integer>)predjenaPolja.get(predjenaPolja.size()-1)).getKey();
            int staraKolona = ((Pair<Integer, Integer>)predjenaPolja.get(predjenaPolja.size()-1)).getValue();

            //Postaviti figuru na novo polje.
            int red = ((Pair<Integer, Integer>)putanja.get(indeksLokacije)).getKey();
            int kolona = ((Pair<Integer, Integer>)putanja.get(indeksLokacije)).getValue();
            predjenaPolja.add(putanja.get(indeksLokacije));

            //Provjeriti da li je figura stala na dijamant
            if(  "dijamant".equals(matrica[red][kolona])  )
            {
                pokupitiDijamant();
            }
            matrica[red][kolona] = this;
            MainController.postavitiFiguruNaPolje(red, kolona, boja, tip);

            System.out.println("Na potezu je igrac "+ trenutniIgrac.getIme()+" Figura " + redniBroj + " prelazi " + indeksLokacije+ "" +
                    " polja, pomjera se sa pozicije " + Integer.toString(Konstante.brojPoljaMatrice(stariRed,staraKolona)) +  " na " + Integer.toString(Konstante.brojPoljaMatrice(red,kolona)));
        }
        else {
            System.out.println("Igrac["+trenutniIgrac.getIme()+"] je pobjednik!");
            obrisatiStaroPolje();
            MainController.postavitiFiguruNaPolje(Konstante.DIMENZIJA_MATRICE/2, Konstante.DIMENZIJA_MATRICE/2, boja, tip);
        }


        //Testiranje rupe.
        /*
        if(indeksLokacije == 1)
        {
            File file = new File("cards/card_joker.png");
            Image image = new Image(file.toURI().toString());
            SpecijalnaKarta sk = new SpecijalnaKarta(image);

            sk.aktiviraj();
        }
        */
    }


    //POMOCNE FUNKCIJE

    private void obrisatiStaroPolje()
    {
        int stariRed = ((Pair<Integer, Integer>)predjenaPolja.get(predjenaPolja.size()-1)).getKey();
        int staraKolona = ((Pair<Integer, Integer>)predjenaPolja.get(predjenaPolja.size()-1)).getValue();
        matrica[stariRed][staraKolona] = null;
        MainController.postavitiFiguruNaPolje(stariRed, staraKolona, Konstante.BOJA_POLJA, Integer.toString(Konstante.brojPoljaMatrice(stariRed,staraKolona)));

    }








}
