package com.example.xhashtablevisualize_scenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("XHashtable-view.fxml"));
        primaryStage.setTitle("Hashtable_Visualizer");
        Scene scene = new Scene(fxmlLoader, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
