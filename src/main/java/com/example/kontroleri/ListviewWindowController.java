package com.example.kontroleri;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ListviewWindowController {

    @FXML
    private Text ui_redniBroj;
    public static int redniBrojFigure;

    public void initialize()
    {
        ui_redniBroj.setText(  Integer.toString(redniBrojFigure) );
    }

}
