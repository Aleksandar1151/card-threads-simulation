package com.example.konstante;

public class Konstante {

    public static int DIMENZIJA_MATRICE = 7;
    public static int BROJ_IGRACA = 1;

    public static final String BOJA_CRVENA = "red";
    public static final String BOJA_ZUTA = "yellow";
    public static final String BOJA_BLUE = "blue";
    public static final String BOJA_ZELENA = "green";
    public static final String BOJA_DIJAMANTA = "pink";
    public static final String BOJA_POLJA = "white";
    public static final String BOJA_RUPE = "black";

    public static final String KONSTANTA_X = "X";
    public static final String FIGURA_OBICNA = "O";
    public static final String FIGURA_LEBDECA = "L";
    public static final String FIGURA_SUPERBRZA = "S";
    public static final String KONSTANTA_DIJAMANT = "*";
    public static final String KONSTANTA_RUPA = "";

    public static final int CONSTANT_BRZINA_OBICNA = 1;
    public static final int CONSTANT_BRZINA_SUPERBRZA = 2;

    public static final int BROJ_OBICNIH_KARATA = 10;
    public static final int BROJ_SPECIJALNIH_KARATA = 12;

    public static int brojPoljaMatrice(int red, int kolona)
    {
        return ((red*Konstante.DIMENZIJA_MATRICE)+(kolona+1));
    }

}


