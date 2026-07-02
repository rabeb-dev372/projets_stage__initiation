package presentation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import service.XHashtable;

public class XHashtableFXPane extends BorderPane {

    private XHashtable maTable;
    private XHashtableFXPanel visualizerPanel;

    private TextField keyField;
    private TextField valueField;
    private Button btnAdd, btnDelete, btnFind;

    public XHashtableFXPane() {
        // 1. Initialisation de la table logique (Tier Business)
        this.maTable = new XHashtable(10);


        // 2. Initialisation du panel graphique (Tier Presentation)
        this.visualizerPanel = new XHashtableFXPanel();
        this.visualizerPanel.setTable(maTable.getTable());
        this.setCenter(visualizerPanel);

        // 3. Construction des contrôles
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

        this.setTop(controlBar);
    }

    private void configurerEvenements() {

        // --- ÉVÉNEMENT : BOUTON ADD ---
        btnAdd.setOnAction(e -> {
            String key = keyField.getText().trim();
            String value = valueField.getText().trim();

            if (key.isEmpty() || value.isEmpty()) {
                afficherAlerte("Champs vides", "Veuillez remplir à la fois la clé et la valeur !");
                return;
            }

            try {
                int valeur = Integer.parseInt(value);
                int indexReel = maTable.add(key, valeur);

                visualizerPanel.setTable(maTable.getTable());
                visualizerPanel.StartAnimation(indexReel, "ADD");

                keyField.clear();
                valueField.clear();
            } catch (NumberFormatException ex) {
                afficherAlerte("Erreur de format", "La valeur doit être un nombre entier valide !");
            }
        });

        // --- ÉVÉNEMENT : BOUTON DELETE ---
        btnDelete.setOnAction(e -> {
            String key = keyField.getText().trim();
            if (key.isEmpty()) {
                afficherAlerte("Champ vide", "Veuillez entrer une clé à supprimer !");
                return;
            }

            int indexReel = maTable.delete(key);
            if (indexReel != -1) {
                visualizerPanel.setTable(maTable.getTable());
                visualizerPanel.StartAnimation(indexReel, "DELETE");
                keyField.clear();
            } else {
                afficherAlerte("Non trouvé", "La clé '" + key + "' n'existe pas dans la table.");
            }
        });

        // --- ÉVÉNEMENT : BOUTON FIND ---
        btnFind.setOnAction(e -> {
            String key = keyField.getText().trim();
            if (key.isEmpty()) {
                afficherAlerte("Champ vide", "Veuillez entrer une clé à rechercher !");
                return;
            }

            int indexReel = maTable.find(key);
            if (indexReel != -1) {
                visualizerPanel.StartAnimation(indexReel, "FIND");
            } else {
                afficherAlerte("Non trouvé", "La clé '" + key + "' n'a pas été trouvée.");
            }
        });
    }

    // ✅ MÉTHODE UTILITAIRE : Remplace proprement JOptionPane sans casser les Threads JavaFX
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(titre);
        alert.setContentText(message);
        alert.initOwner(this.getScene().getWindow()); // Associe la popup à l'application principale
        alert.showAndWait();
    }
}