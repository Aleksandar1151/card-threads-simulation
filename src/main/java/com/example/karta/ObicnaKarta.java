package com.example.karta;

import javafx.scene.image.Image;

public class ObicnaKarta extends Karta{
    int brojKarte;

    public ObicnaKarta(Image slika, int brojKarte)
    {
        super(slika);
        this.brojKarte = brojKarte;
    }

    public int getBrojKarte() {
        return brojKarte;
    }
}
