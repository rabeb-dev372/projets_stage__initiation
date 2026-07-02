import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class XHashtableFXDrawer extends JFXPanel {//classe qui dessine la table sous forme des pixels graphiques sur un ecran

//public class XHashtableFXDrawer extends StackPane {
    private NodeList[] table;
    private Canvas canvas;
    private GraphicsContext gc;
    private int animationIndex =-1;
    private String animationType ="";
    private boolean interrepteur=false;
    private double translationX = 0;
    private boolean glissement=false;
    public XHashtableFXDrawer(){
        this.table = new NodeList[10];
        this.canvas = new Canvas(800,600);
        this.gc=canvas.getGraphicsContext2D();
        //this.getChildren().add(canvas);

            StackPane can=new StackPane(canvas);//on met canvas dans un conteur
            Scene scene=new Scene(can,800,600);
            this.setScene(scene);//on ajoute la scene au JFXPanel
        Platform.runLater(() ->{
        dessinertable();
        });
        dessinertable();

    }
public void setTable(NodeList[]table){
        this.table=table;
        dessinertable();
}
    public void dessinertable() {
        Platform.runLater(() -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());//0,0 cad a partir du coin supp gaucha
            double starty = 20;
            for (int i = 0; i < 10; i++) {
                Color couleurfond = Color.WHITE;
                if (i == animationIndex && interrepteur) {
                    if (animationType.equals("ADD"))    couleurfond = Color.LIGHTBLUE;
                    if (animationType.equals("FIND"))   couleurfond = Color.LIGHTGREEN;
                    if (animationType.equals("DELETE")) couleurfond = Color.SALMON;}
                    //dessiner l'indice
                    gc.setFill(Color.BLACK);
                gc.fillText(String.format("[%02d]",i),10,starty+22);
                //double ceX = 60;
                gc.setFill(couleurfond);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(2.0);

                gc.fillRect(60, starty, 90, 35);
                gc.strokeRect(60, starty, 90, 35);
                //gc.setFill(Color.GRAY);
                //gc.fillText("null",80,starty+25,25);
                if (table[i] == null || table[i].isEmpty()) {
                    gc.setFill(Color.BLACK);
                    gc.fillText("null", 90, starty + 22);
                } else {
                    gc.setFill(Color.BLACK);
                    gc.fillText(String.valueOf(i), 100, starty + 22);
                    double ceX = 150;
                    Node imm = table[i].getTete();
                    final boolean ligneEnCoursDAddition = (i == animationIndex && animationType.equals("ADD"));
                    while (imm != null) {//puisqu'on est entrain d'ajouter
                        boolean nouvellecase = (imm.next == null && i == animationIndex && animationType.equals("ADD"));
                        double caseajouteactuel=ceX;
                        double positionFlecheX = ceX;
                        double positionBoiteX = ceX + 35;
                        if (nouvellecase) {
                            caseajouteactuel += translationX;
                        }

                        gc.setStroke(Color.BLACK);
                        if (nouvellecase) {
                            gc.strokeLine(caseajouteactuel, starty + 17, caseajouteactuel + 35, starty + 17);
                        } else {
                            gc.strokeLine(ceX, starty + 17, ceX + 35, starty + 17);
                        }

                        gc.setFill(Color.WHITE);
                        gc.fillRect(caseajouteactuel + 35, starty, 80, 35);
                        gc.setStroke(Color.BLACK);
                        gc.strokeRect(caseajouteactuel + 35, starty, 80, 35);

                        String texte = imm.getKey() + ":" + imm.getValue();
                        gc.setFill(Color.BLACK);
                        gc.fillText(texte, caseajouteactuel + 42, starty + 22, 70);

                        ceX += 115; // 35 (flèche) + 80 (boîte) = 115 parfait
                        imm = imm.next;
                    }

                    double nullX = ceX;
                    if (ligneEnCoursDAddition) {
                        nullX += translationX;
                    }

                    // Case du pointeur de fin (null)
                    gc.setStroke(Color.BLACK);
                    gc.strokeRect(nullX, starty, 40, 35);
                    gc.setFill(Color.GRAY);
                    gc.fillRect(nullX + 1, starty + 1, 38, 33);

                    gc.setFill(Color.BLACK);
                    gc.fillText("null", nullX + 5, starty + 22);
                }
            starty+=55;}


        });
    }
    public void StartAnimation(int animationIndex, String animationType){
        this.animationIndex = animationIndex;
        this.animationType  = animationType;
        this.interrepteur   = true;
        if (animationType.equals("ADD")) {
            this.translationX = -115;
        } else {
            this.translationX = 0;
        }
        dessinertable();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(40); // 40 frames au total pour un mouvement progressif

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            private int framecount=0;
            @Override
            public void handle(ActionEvent e) {
                framecount++;
                // 1. Gestion de la translation : on rapproche la valeur de 0 à chaque pas
                if (animationType.equals("ADD") && translationX < 0) {
                    translationX += 5; // Avance de 5 pixels vers la droite à chaque tick
                    if (translationX > 0) translationX = 0;
                }

                // 2. Gestion du clignotement (on change l'état toutes les 5 frames pour ne pas fatiguer les yeux)
                if (framecount % 5 == 0) {
                    interrepteur = !interrepteur;
                }

                dessinertable();
            }
        };
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(30),event));//le timeline declanche l'event toutes les 200ms
        timeline.setOnFinished(e ->{
            this.animationIndex=-1;
            this.animationType="";
            this.interrepteur=false;
            dessinertable();
        });
        timeline.play();


    }
    }

