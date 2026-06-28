package com.example.xhashtablevisualize_scenebuilder;

import Data.Node;
import Data.NodeList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class XHashtableFXPanel extends VBox {
    private NodeList[] table;
    private int animationIndex= -1;
    private String animationType="";
    private boolean interrepteur=false;
    private Rectangle[] listRectangles = new Rectangle[10];
    public XHashtableFXPanel(){
        this.table=new NodeList[10];
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(15));
        this.setSpacing(5);

        this.setMinSize(750, 500);
        this.setPrefSize(750, 500);

        updateView();
    }
    public void setTable(NodeList[]table){
        this.table=table;
        updateView();
    }
    public void updateView(){
        Platform.runLater(() ->{
        this.getChildren().clear();
        for(int i=0;i<10;i++){
            Pane ligne= new Pane();
            ligne.setPrefHeight(45);
            ligne.setPadding(new Insets(5));

            Text indice = new Text(String.format("[%02d]",i));
            Color couleurcase=Color.WHITE;
            if(i==animationIndex && interrepteur){
                if(animationType.equals("ADD")){
                    couleurcase=Color.LIGHTBLUE;
                } else if (animationType.equals("DELETE")) {
                    couleurcase=Color.RED;
                } else if (animationType.equals("FIND")) {
                    couleurcase=Color.LIGHTGREEN;
                }
            }
            Rectangle caserect = new Rectangle(80,35);
            caserect.setStroke(Color.BLACK);
            caserect.setFill(couleurcase);
            listRectangles[i] = caserect;

            Text contenuCase;
            ligne.getChildren().add(indice);
            double ceX = 50;

            if (table[i] == null || table[i].isEmpty()) {
                contenuCase = new Text("null");
                contenuCase.setFill(Color.GRAY);
                StackPane casecomplet = new StackPane();
                casecomplet.getChildren().addAll(caserect, contenuCase);
                casecomplet.setLayoutX(ceX);
                casecomplet.setLayoutY(5);
                ligne.getChildren().add(casecomplet);
            } else {
                contenuCase = new Text(String.valueOf(i));
                contenuCase.setFill(Color.BLACK);
                StackPane casecomplet = new StackPane();
                casecomplet.getChildren().addAll(caserect, contenuCase);

                casecomplet.setLayoutX(ceX);
                casecomplet.setLayoutY(5);
                ligne.getChildren().add(casecomplet);
                ceX += 80;

                Node imm = table[i].getTete();
                while (imm != null) {
                    Line trait = new Line(0, 0, 40, 0);
                    trait.setStroke(Color.BLACK);
                    trait.setStrokeWidth(2);
                    trait.setLayoutX(ceX);
                    trait.setLayoutY(22);
                    ligne.getChildren().add(trait);
                    ceX += 40;

                    Rectangle caseajoute = new Rectangle(70, 35);
                    caseajoute.setStroke(Color.BLACK);
                    caseajoute.setFill(Color.WHITE);

                    // Gestion des doublons : clé et valeur s'affichent proprement
                    String value = imm.getKey() + " : " + imm.getValue();
                    Text valueText = new Text(value);
                    StackPane noeud = new StackPane();
                    noeud.getChildren().addAll(caseajoute, valueText);
                    noeud.setLayoutX(ceX);
                    noeud.setLayoutY(5);
                    ligne.getChildren().addAll(noeud);

                    if (imm.next == null && i == animationIndex && animationType.equals("ADD")) {
                        double distancedepart = -100 - ceX;
                        noeud.setTranslateX(distancedepart);
                        TranslateTransition tt = new TranslateTransition(Duration.millis(500), noeud);
                        tt.setToX(0);
                        tt.setDelay(Duration.millis(100));
                        tt.play();
                    }
                    ceX += 70;
                    imm = imm.next;
                }

                Rectangle ptr = new Rectangle(35, 35);
                ptr.setStroke(Color.BLACK);
                ptr.setFill(Color.WHITE);

                String end = "null";
                Text endv = new Text(end);
                StackPane noeudfinale = new StackPane();
                noeudfinale.getChildren().addAll(ptr, endv);
                noeudfinale.setLayoutX(ceX);
                noeudfinale.setLayoutY(5);
                ligne.getChildren().add(noeudfinale);
            }

            // Ajout de la ligne directement dans notre VBox
            this.getChildren().add(ligne);
        }
        });
    }

    public void StartAnimation(int index, String type) {
        this.animationType = type;
        this.animationIndex = index;
        this.interrepteur = true;
        updateView();

        changercouleurinterrepteur(index, true);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                interrepteur = !interrepteur;
                changercouleurinterrepteur(index, interrepteur);
            }
        };

        Timeline timeline = new Timeline();
        timeline.setCycleCount(6);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), event));
        timeline.setOnFinished(e -> {
            this.animationIndex = -1;
            this.animationType = "";
            this.interrepteur = false;
            updateView();
        });
        timeline.play();
    }

    private void changercouleurinterrepteur(int index, boolean allume) {
        Platform.runLater(() -> {
            if (allume) {
                if (animationType.equals("ADD")) {
                    listRectangles[index].setFill(Color.LIGHTBLUE);
                } else if (animationType.equals("FIND")) {
                    listRectangles[index].setFill(Color.LIGHTGREEN);
                } else if (animationType.equals("DELETE")) {
                    listRectangles[index].setFill(Color.RED);
                }
            } else {
                listRectangles[index].setFill(Color.web("#E6E6E6"));
            }
        });
    }
        }
