package com.example.hashtablevisualizefx_standalone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.XHashtableFXPane;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage ){
        XHashtableFXPane pane = new XHashtableFXPane();
        Scene sc=new Scene(pane,800,600);
        primaryStage.setTitle("Visualiseur Table de Hachage — javaFX standalone");
        primaryStage.setScene(sc);
        primaryStage.setResizable(false);
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}
