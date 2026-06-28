package com.example.xhashtablevisualize_scenebuilder;

import Data.NodeList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Alert;

public class HashtableController {
    private NodeList[] hashtable=new NodeList[10];
    private XHashtableFXPanel mypanel;
    @FXML private BorderPane mainBorderPane;
    @FXML private TextField keyField;//la zone texte ou l'utilisateur tape la clé
    @FXML private TextField valueField;

    @FXML
    protected void initialize() {//methode qui s'execute dés que la fenetre s'ouvre
        for(int i=0;i<10;i++) {
            hashtable[i] = new NodeList();
        }
        mypanel=new XHashtableFXPanel();
        mypanel.setTable(hashtable);
        if (mainBorderPane != null) {
            mainBorderPane.setCenter(mypanel);
        }
    }
    @FXML
    protected void onAddClick(){
        String cle=keyField.getText().trim();
        String valeurStr= valueField.getText().trim();
        if (cle.isEmpty()||valeurStr.isEmpty()){
            afficherAlert("champs vides","veuillez remplir la clé et la valeur");
            return;}
        int val=Integer.parseInt(valeurStr);
        int indice = Math.abs(cle.hashCode()%10);
        hashtable[indice].ajoutFin(cle,val);

        mypanel.setTable((hashtable));
        mypanel.StartAnimation(indice,"ADD");
        keyField.clear();
        valueField.clear();

        //afficherAlert("Succès","tu veux ajouter la clé:"+cle+" avec la valeur: "+valeurStr);

        }
        @FXML
     protected void onDELETEClick() {
         String cle = keyField.getText().trim();
         //int intcle = Integer.parseInt(cle);
         int indice = Math.abs(cle.hashCode()%10);
         if (cle.isEmpty()) {
             afficherAlert("Champ vide", "Veuillez entrer une clé.");
             return;
         }
         boolean supp=hashtable[indice].supprimer(cle);
         if(supp){
             mypanel.setTable(hashtable);
             mypanel.StartAnimation(indice,"DELETE");
         }else{
             afficherAlert("Introuvable", "La clé '" + cle + "' n'existe pas.");
         }
         //afficherAlert("Succès (Test)", "Clic détecté sur Delete pour la clé : " + cle);
         keyField.clear();

     }
    @FXML
    protected void onFindClick() {
        String cle = keyField.getText().trim();
        //int intcle= Integer.parseInt(cle);
        if (cle.isEmpty()) {
            afficherAlert("Champ vide", "Veuillez entrer une clé.");
            return;
        }
        int index = Math.abs(cle.hashCode() % 10);
        int trouve = hashtable[index].find(cle);
        if(trouve!=-1){
            mypanel.StartAnimation(index,"FIND");

        }else{
            afficherAlert("Introuvable", "La clé '" + cle + "' n'est pas dans la table.");
        }
        //afficherAlert("Succès (Test)", "Clic détecté sur Find pour la clé : " + cle);
        keyField.clear();
    }

    public void afficherAlert(String titre, String msg){
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();

    }
}
