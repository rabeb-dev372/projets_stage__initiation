/*import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class XHashtableFXPanel extends JFXPanel {
    private NodeList[] table;
    private int animationIndex=-1;
    private String animationType="";
    private boolean interrepteur = false;
    private Rectangle[] listeRectangles = new Rectangle[10];

    public XHashtableFXPanel(){
        this.table= new NodeList[10];
        // On rafraîchit la vue JavaFX
        updateView();
    }
    public void setTable(NodeList[] table){
        this.table=table;
        updateView();
    }

    public void updateView(){
// POURQUOI Platform.runLater() ? (Règle d'or JavaFX Embedded)
//
// 1. LE PROBLÈME : Swing (les boutons) et JavaFX (les rectangles) tournent sur deux
//    Threads différentes. Un clic sur un bouton Swing ne peut pas modifier
//    directement des composants JavaFX sous peine de faire planter l'application.
//
// 2. LA SOLUTION : Platform.runLater(() -> { ... }) sert de boîte aux lettres.
//    Elle envoie les instructions graphiques à la file d'attente de JavaFX pour qu'elles
//    soient exécutées en toute sécurité sur sa propre thread.
//
// 3. LA SYNTAXE : L'opérateur "-> " (Expression Lambda) est un raccourci qui encapsule
//    tout le bloc de code qui suit pour le transmettre comme une tâche à accomplir.
// =======================================================================================
        //suite a chaque operation sur la table,
        Platform.runLater(()->{
            VBox colonne=new VBox(5);
            colonne.setAlignment(Pos.CENTER_LEFT);
            //colonne.setStyle("-fx-background-color: white;");//le style css
            colonne.setPadding(new Insets(15));//insets classe qui defini les marges (espace vide)
            for(int i=0;i<10;i++){
                //HBox ligne =new HBox(5);
                Pane ligne = new Pane();
                ligne.setPrefHeight(45);//hauteur fixe
                //ligne.setAlignment(Pos.CENTER_LEFT);
                ligne.setPadding(new Insets(5));
                Text indice = new Text(String.format("[%02d]",i));
                indice.setLayoutX(10);//positionner un noeud dans un conteneur(10px du bord gauche)
                indice.setLayoutY(25);
                //ligne.getChildren().add(indice);
                Color couleurcase=Color.WHITE;
                if(i==animationIndex && interrepteur){
                    if(animationType.equals("ADD")){
                        couleurcase=Color.LIGHTBLUE;
                    }else if(animationType.equals("FIND")){
                        couleurcase=Color.LIGHTGREEN;

                    } else if (animationType.equals("DELETE")) {
                        couleurcase=Color.RED;}}
                //case statique du tab
                Rectangle caserect = new Rectangle(80,35);
                caserect.setStroke(Color.BLACK);
                caserect.setFill(couleurcase);
                listeRectangles[i]=caserect;
                Text contenuCase;
                ligne.getChildren().add(indice);
                double ceX=50;//position de la case principale(position de depart)
                if (table[i] == null || table[i].isEmpty()) {

                    contenuCase = new Text("null");
                    contenuCase.setFill(Color.GRAY);
                    StackPane casecomplet = new StackPane();
                    casecomplet.getChildren().addAll(caserect,contenuCase);
                    casecomplet.setLayoutX(ceX);
                    casecomplet.setLayoutY(5);
                    ligne.getChildren().add(casecomplet);
                }else{
                    contenuCase = new Text(String.valueOf(i));
                    contenuCase.setFill(Color.BLACK);
                    StackPane casecomplet = new StackPane();
                    casecomplet.getChildren().addAll(caserect,contenuCase);

                    casecomplet.setLayoutX(ceX);
                    casecomplet.setLayoutY(5);
                    //Pane lignePane = new Pane();
                    ligne.getChildren().add(casecomplet);
                    ceX+=80;//avancement apres la case principale de largeur 80


                    Node imm = table[i].getTete();
                    while(imm!= null ){
                        //caseajoute.setWidth(10);

                        Line trait = new Line(0,0,40,0);
                        trait.setStroke(Color.BLACK);
                        trait.setStrokeWidth(2);
                        trait.setLayoutX(ceX);
                        trait.setLayoutY(22);
                        ligne.getChildren().add(trait);
                        ceX+=40;


                        Rectangle caseajoute=new Rectangle(70,35);
                        caseajoute.setStroke(Color.BLACK);
                        caseajoute.setFill(Color.WHITE);

                        String value= imm.getKey()+" : "+imm.getValue();
                        Text valueText=new Text(value);
                        StackPane noeud = new StackPane();
                        noeud.getChildren().addAll(caseajoute, valueText);
                        noeud.setLayoutX(ceX);
                        noeud.setLayoutY(5);
                        ligne.getChildren().addAll(noeud);

                        //translation
                        if(imm.next==null && i==animationIndex && animationType.equals("ADD")){
                            double distancedepart = -100-ceX;
                            noeud.setTranslateX(distancedepart);
                            TranslateTransition tt= new TranslateTransition(Duration.millis(500),noeud);
                            tt.setToX(0);//destination finale
                            tt.setDelay(Duration.millis(100));//temps d'attente avant le depart
                            tt.play();}
                        //lancer l'animation
                        ceX+=70;
                         imm=imm.next;
                    }
                    Rectangle ptr=new Rectangle(35,35);
                    ptr.setStroke(Color.BLACK);
                    ptr.setFill(Color.WHITE);

                    String end ="null";
                    Text endv=new Text(end);
                    StackPane noeudfinale = new StackPane();
                    noeudfinale.getChildren().addAll(ptr,endv);
                        noeudfinale.setLayoutX(ceX);
                        noeudfinale.setLayoutY(5);
                    ligne.getChildren().add(noeudfinale);
                }

                colonne.getChildren().add(ligne);
            }
            Scene sc=new Scene(colonne,750,550);
            this.setScene(sc);
        });
        }
                 public void StartAnimation(int index,String type){
                            this.animationType=type;
                            this.animationIndex=index;
                            this.interrepteur=true;
                            updateView();
                           // boolean interrepteur = false;
                     changercouleurinterrepteur(index, true);
                     EventHandler<ActionEvent> event=new EventHandler<ActionEvent>(){//c'est le comportement qui s'execute a chaque frame du  timeline(le comportement reperté)
                     @Override
                     public void handle(ActionEvent e){
                         interrepteur=!interrepteur;
                         changercouleurinterrepteur(index, interrepteur);//clignotement
                     }};
                     Timeline timeline= new Timeline();//miniteur
                     timeline.setCycleCount(6);
                     timeline.setAutoReverse(false);
                     timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200),event));//le timeline declanche l'event toutes les 200ms
                     timeline.setOnFinished(e ->{
                         this.animationIndex=-1;
                         this.animationType="";
                         this.interrepteur=false;
                         updateView();
                     });
                     timeline.play();

    }
    private void changercouleurinterrepteur(int index,boolean allume){
        Platform.runLater(() -> {
            if (allume) {
                if (animationType.equals("ADD")) {
                    listeRectangles[index].setFill(Color.LIGHTBLUE);
                } else if (animationType.equals("FIND")) {
                    listeRectangles[index].setFill(Color.LIGHTGREEN);
                } else if (animationType.equals("DELETE")) {
                    listeRectangles[index].setFill(Color.RED);
                }
            } else {
                // Retour au gris d'origine sans perturber le reste
                listeRectangles[index].setFill(Color.web("#E6E6E6"));
            }
        });
    }

                }*/
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class XHashtableFXPanel extends VBox {
    private NodeList[] table;
    private int animationIndex = -1;
    private String animationType = "";
    private boolean interrepteur = false;
    private Rectangle[] listeRectangles = new Rectangle[10];

    public XHashtableFXPanel(){
        this.table = new NodeList[10];

        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(15));
        this.setSpacing(5);

        updateView();
    }

    public void setTable(NodeList[] table){
        this.table = table;
        updateView();
    }

    public void updateView(){
        Platform.runLater(() -> {
            this.getChildren().clear();

            for(int i = 0; i < 10; i++){
                Pane ligne = new Pane();
                ligne.setPrefHeight(45);
                ligne.setPadding(new Insets(5));

                Text indice = new Text(String.format("[%02d]", i));
                indice.setLayoutX(10);
                indice.setLayoutY(25);

                Color couleurcase = Color.WHITE;
                if(i == animationIndex && interrepteur){
                    if(animationType.equals("ADD")){
                        couleurcase = Color.LIGHTBLUE;
                    } else if(animationType.equals("FIND")){
                        couleurcase = Color.LIGHTGREEN;
                    } else if (animationType.equals("DELETE")) {
                        couleurcase = Color.RED;
                    }
                }

                Rectangle caserect = new Rectangle(80, 35);
                caserect.setStroke(Color.BLACK);
                caserect.setFill(couleurcase);
                listeRectangles[i] = caserect;

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
                    while(imm != null){
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

                        String value = imm.getKey() + " : " + imm.getValue();
                        Text valueText = new Text(value);
                        StackPane noeud = new StackPane();
                        noeud.getChildren().addAll(caseajoute, valueText);
                        noeud.setLayoutX(ceX);
                        noeud.setLayoutY(5);
                        ligne.getChildren().addAll(noeud);

                        if(imm.next == null && i == animationIndex && animationType.equals("ADD")){
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

                this.getChildren().add(ligne);
            }
        });
    }

    public void StartAnimation(int index, String type){
        this.animationType = type;
        this.animationIndex = index;
        this.interrepteur = true;
        updateView();

        changercouleurinterrepteur(index, true);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
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

    private void changercouleurinterrepteur(int index, boolean allume){
        Platform.runLater(() -> {
            if (allume) {
                if (animationType.equals("ADD")) {
                    listeRectangles[index].setFill(Color.LIGHTBLUE);
                } else if (animationType.equals("FIND")) {
                    listeRectangles[index].setFill(Color.LIGHTGREEN);
                } else if (animationType.equals("DELETE")) {
                    listeRectangles[index].setFill(Color.RED);
                }
            } else {
                listeRectangles[index].setFill(Color.web("#E6E6E6"));
            }
        });
    }
}











