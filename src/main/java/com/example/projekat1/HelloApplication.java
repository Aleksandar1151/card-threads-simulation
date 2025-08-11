package com.example.projekat1;

import com.example.kontroleri.MainController;
import com.example.simulacija.Simulacija;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloApplication extends Application {
    public static int broj = 2;



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("Java Projekat");
        stage.setScene(scene);
        stage.show();




    }



    public static void main(String[] args) {
        launch();
    }
}