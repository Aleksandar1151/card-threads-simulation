package com.example.kontroleri;

import com.example.figura.Duh;
import com.example.figura.Figura;
import com.example.igrac.Igrac;
import com.example.karta.Karta;
import com.example.karta.SpecijalnaKarta;
import com.example.konstante.Konstante;
import com.example.projekat1.HelloApplication;
import com.example.simulacija.Simulacija;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class MainController {

    @FXML
    public ImageView ui_imageView;
    @FXML
    public GridPane ui_matricaUI;
    @FXML
    private Label ui_label_igrac1;
    @FXML
    private Label ui_vrijemeTrajanja;

    @FXML
    private ListView<String> ui_listView;


    public static GridPane matricaUI;
    public static Label label_igrac1;
    public static ImageView _imageView;
    public static Label _vrijemeTrajanja;
    public static ListView<String> listView;
    public static Simulacija simulacija;
    public static Duh figuraDuh;


    public void initialize()
    {
        matricaUI = ui_matricaUI;
        label_igrac1 = ui_label_igrac1;
        _imageView = ui_imageView;
        _vrijemeTrajanja = ui_vrijemeTrajanja;
        listView = ui_listView;

        simulacija = new Simulacija(Konstante.DIMENZIJA_MATRICE);
        figuraDuh = new Duh();

        popunitiListu(simulacija.igraci);


        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String linija = listView.getSelectionModel().getSelectedItem();
                String[] linijaDio = linija.split("-",3);
                System.out.println("clicked on " + linijaDio[1]);
                otvoritiProzorZaFiguru(linija);
            }
        });



        switch (Konstante.DIMENZIJA_MATRICE)
        {
            case 7:
            {
                napraviMatricu7();
                break;
            }

            case 8:
            {
                napraviMatricu8();
                break;
            }

            case 9:
            {
                napraviMatricu9();
                break;
            }

            case 10:
            {
                napraviMatricu10();
                break;
            }
        }

}

    public void otvoritiProzorZaFiguru(String linija)
    {
        System.out.println("linija = " + linija);
        String[] linijaDio = linija.split("-",3);

        int redniBrojFigure = Integer.parseInt(linijaDio[1]);
        System.out.println("redniBrojFigure = " + redniBrojFigure);
        //System.out.println("redniBrojFigure = " + Integer.getInteger(linijaDio[1]));

        ListviewWindowController.redniBrojFigure = redniBrojFigure;


        String imeIgraca = linijaDio[0];
        //int redniBrojFigure = Integer.getInteger(linijaDio[1]);
        String tipFigure = linijaDio[2];

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("listview-window.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        }catch (Exception ex)
        {
            System.out.println("Nema tog fajla.");

        }


    }

    public void startButtonClicked() throws InterruptedException {

        ui_label_igrac1.setStyle("-fx-background-color: #FFFFFF");
        pokreniTimer();

        simulacija.start();
       // figuraDuh.start();

    }

    public void popunitiListu(Queue igraci)
    {
        for(int i = 0; i< Konstante.BROJ_IGRACA;i++)
        {
            Igrac igrac = (Igrac) igraci.remove();
            igraci.add(igrac);

            Figura figura = (Figura) igrac.figure.peek();

            listView.getItems().add(igrac.getIme() + "-" +figura.getRedniBroj()+"-"+figura.getTip());
        }

    }

    public void dodatiUListu()
    {

    }

    public void napraviMatricu7()
    {
        System.out.println("User logged in..." +System.getProperty("user.dir"));

        int broj=Konstante.DIMENZIJA_MATRICE;
        for (int i = 0 ; i < (broj*broj) ; i++) {
            Label label = createLabel(Integer.toString(i+1));
            label.setStyle("-fx-background-color: #FFFFFF");
            //button.setAlignment(Pos.CENTER);
            GridPane.setHalignment(label, HPos. CENTER);
            GridPane.setValignment(label, VPos. CENTER);
            matricaUI.add(label, i % broj, i / broj);
        }

        matricaUI.setAlignment(Pos.CENTER);

    }

    public void napraviMatricu8()
    {

        // Lista polja: 5 14 23 32 39 46 53 60 51 42 33 26 19 12 13 22 31 38 45 52 43 34 27 20 21 30 37 44 35 28 29 36
        int broj=8;
        matricaUI.getColumnConstraints().add(new ColumnConstraints(50));
        //matricaUI.getColumnConstraints().add(new ColumnConstraints(50));
        //matricaUI.getColumnConstraints().add(new ColumnConstraints(50));

        matricaUI.getRowConstraints().add(new RowConstraints(50));
        //matricaUI.getRowConstraints().add(new RowConstraints(50));
        //matricaUI.getRowConstraints().add(new RowConstraints(50));


        for (int i = 0 ; i < (broj*broj) ; i++) {
            Label label = createLabel(Integer.toString(i+1));
            label.setStyle("-fx-background-color: #FFFFFF");
            //button.setAlignment(Pos.CENTER);
            GridPane.setHalignment(label, HPos. CENTER);
            GridPane.setValignment(label, VPos. CENTER);
            matricaUI.add(label, i % broj, i / broj);
        }
    }

    public void napraviMatricu9()
    {

        // Lista polja:  5 15 25 35 45 53 61 69 77 67 57 47 37 29 21 13 14 24 34 44 52 60 68 58 48 38 30 22 23 33 43 51 59 49 39 31 32 42 50 40 41
        int broj=9;
        matricaUI.getColumnConstraints().add(new ColumnConstraints(50));
        matricaUI.getColumnConstraints().add(new ColumnConstraints(50));
        //matricaUI.getColumnConstraints().add(new ColumnConstraints(50));

        matricaUI.getRowConstraints().add(new RowConstraints(50));
        matricaUI.getRowConstraints().add(new RowConstraints(50));
        //matricaUI.getRowConstraints().add(new RowConstraints(50));


        for (int i = 0 ; i < (broj*broj) ; i++) {
            Label label = createLabel(Integer.toString(i+1));
            label.setStyle("-fx-background-color: #FFFFFF");
            //button.setAlignment(Pos.CENTER);
            GridPane.setHalignment(label, HPos. CENTER);
            GridPane.setValignment(label, VPos. CENTER);
            matricaUI.add(label, i % broj, i / broj);
        }
    }

    public void napraviMatricu10()
    {
        // Lista polja:
        int broj=10;
        matricaUI.getColumnConstraints().add(new ColumnConstraints(50));
        matricaUI.getColumnConstraints().add(new ColumnConstraints(50));
        matricaUI.getColumnConstraints().add(new ColumnConstraints(50));

        matricaUI.getRowConstraints().add(new RowConstraints(50));
        matricaUI.getRowConstraints().add(new RowConstraints(50));
        matricaUI.getRowConstraints().add(new RowConstraints(50));


        for (int i = 0 ; i < (broj*broj) ; i++) {
            Label label = createLabel(Integer.toString(i+1));
            label.setStyle("-fx-background-color: #FFFFFF");
            //button.setAlignment(Pos.CENTER);
            GridPane.setHalignment(label, HPos. CENTER);
            GridPane.setValignment(label, VPos. CENTER);
            matricaUI.add(label, i % broj, i / broj);
        }
    }


    public void dugmePrikazListeClick()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("intro-window.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        }catch (Exception ex)
        {
            System.out.println("Nema tog fajla.");
        }
    }

    private static Label createLabel(String text) {
        Label label = new Label(text);
       // button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setMaxSize(45, 45);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);

        return label ;
    }

    public static void postavitiFiguruNaPolje(int red, int kolona, String boja, String tekst)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label label = createLabel(tekst);
                GridPane.setHalignment(label, HPos. CENTER);
                GridPane.setValignment(label, VPos. CENTER);

                // System.out.println("pravi dugme----red_kolona"+red+"_"+kolona);
                label.setStyle("-fx-background-color: "+boja);
                matricaUI.add(label, kolona , red);
            }
        });


    }


    //Funkcija za indikaciju koji je igrač na potezu
    public static void indikatorLabelIgrac(String boja)
    {
        switch (boja)
        {
            case "crvena":
            {
                label_igrac1.setStyle("-fx-border-color: black");
            }
        }
    }

    public static void skinutiLabeluSaPolja(int red, int kolona)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ObservableList<Node> childrens = matricaUI.getChildren();
                for(Node node : childrens) {
                    if(node instanceof Label && matricaUI.getRowIndex(node) == red && matricaUI.getColumnIndex(node) == kolona) {
                        Label label= (Label)node; // use what you want to remove
                        matricaUI.getChildren().remove(label);


                        System.out.println("Obrisano.");
                        break;
                    }
                }
            }
        });
    }

    public void dugmeDijamantKlik()
    {

       // listView.getItems().add("Item 4");

    }

    public static void postavitiKartu(Karta karta)
    {
        Image image = karta.slika;
        _imageView.setImage(image);
    }



    public static void postavitiDijamant(Pair<Integer,Integer> lokacijaDijamanta )
    {

       // makeLabelOnPosition(lokacijaDijamanta.getKey(),lokacijaDijamanta.getValue(),"purple","0");

    }


    private void pokreniTimer() {


        Timer tr = new Timer() ;
        tr.scheduleAtFixedRate(new TimerTask(){
            int sekunde = 0;
            @Override
            public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        _vrijemeTrajanja.setText(Integer.toString(sekunde) + "s");
                        sekunde++;
                    }
                });


            }
        }, 0, 1000);
    }

}
