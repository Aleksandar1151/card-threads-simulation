package com.example.igrac;

import com.example.figura.Figura;
import com.example.figura.Lebdeca;
import com.example.figura.Obicna;
import com.example.figura.Superbrza;

import java.util.Random;
import java.util.Stack;

public class Igrac {

    String ime;
    String boja;
    public Stack<Figura> figure = new Stack<>();

    public Igrac(String ime, String boja)
    {
        this.ime = ime;
        this.boja = boja;

        Random rnd = new Random();
        for(int i = 4 ; i>0;i--)
        {
            int redniBrojFigure = rnd.nextInt(3);

            switch (redniBrojFigure)
            {
                case 0:
                {
                    figure.push(new Obicna(i,boja));
                    break;
                }
                case 1:
                {
                    //figure.push(new Lebdeca(i,boja));
                    figure.push(new Obicna(i,boja));
                    break;
                }
                case 2:
                {
                    figure.push(new Obicna(i,boja));
                    //figure.push(new Superbrza(i,boja));
                    break;
                }
            }
        }

    }

    //Geteri
    public String getIme()
    {
        return ime;
    }
    public String getBoja()
    {
        return boja;
    }

    //Seteri
    public void setIme(String ime)
    {
        this.ime = ime;
    }
    public void setBoja(String boja)
    {
        this.boja = boja;
    }
}
