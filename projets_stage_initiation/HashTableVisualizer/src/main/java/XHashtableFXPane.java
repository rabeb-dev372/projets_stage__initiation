import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.swing.*;

public class XHashtableFXPane extends BorderPane {

        private XHashtable maTable;
        private XHashtableFXPanel visualizerPanel;
        //private XHashtableFXDrawer canvasDrawer;

        private TextField keyField;
        private TextField valueField;
        private Button btnAdd, btnDelete, btnFind;

        public XHashtableFXPane() {
            // 1. Initialisation des composants logiques et graphiques
            this.maTable = new XHashtable(10);
            //this.canvasDrawer = new XHashtableFXDrawer();
            //this.canvasDrawer.setTable(maTable.getTable());
            this.visualizerPanel = new XHashtableFXPanel();
            this.setCenter(visualizerPanel);

            //canvasDrawer.setTable(maTable.getTable());

            creerBarreControle();

            configurerEvenements();
        }

        private void creerBarreControle() {
            HBox controlBar = new HBox(10);
            controlBar.setPadding(new Insets(10));
            controlBar.setAlignment(Pos.CENTER);
            controlBar.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #cccccc; -fx-border-width: 0 0 1 0;");

            keyField = new TextField();
            keyField.setPrefWidth(100);
            valueField = new TextField();
            valueField.setPrefWidth(60);

            btnAdd = new Button("Add");
            btnDelete = new Button("Delete");
            btnFind = new Button("Find");

            controlBar.getChildren().addAll(
                    new Label("Clé :"), keyField,
                    new Label("Valeur :"), valueField,
                    btnAdd, btnDelete, btnFind
            );

            // On place la barre en haut du BorderPane
            this.setTop(controlBar);
        }

        private void configurerEvenements() {
            btnAdd.setOnAction(e -> {
                String key = keyField.getText().trim();
                String value = valueField.getText().trim();
                if (!key.isEmpty() && value.isEmpty()) {
                    try {
                        int valeur = Integer.parseInt(value);
                        int indexReel = maTable.add(key, valeur);

                        visualizerPanel.setTable(maTable.getTable());
                        visualizerPanel.StartAnimation(indexReel, "ADD");

                        keyField.clear();
                        valueField.clear();
                    } catch (NumberFormatException ex) {
                        System.out.println("Erreur format valeur");
                    }
                }
            });

            btnDelete.setOnAction(e -> {
                String key = keyField.getText().trim();
                if (!key.isEmpty()) {
                    int indexReel = maTable.delete(key);
                    if (indexReel != -1) {
                        visualizerPanel.setTable(maTable.getTable());
                        visualizerPanel.StartAnimation(indexReel, "DELETE");
                        keyField.clear();
                    } else {
                        afficherAlert("Introuvable", "La clé '" + key + "' n'existe pas.");
                    }
                }
            });

            btnFind.setOnAction(e -> {
                String key = keyField.getText().trim();
                if (!key.isEmpty()) {
                    int indexReel = maTable.find(key);
                    if (indexReel != -1) {
                        visualizerPanel.StartAnimation(indexReel, "FIND");
                    }
                }
            });
        }
    private void afficherAlert(String titre, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    }

