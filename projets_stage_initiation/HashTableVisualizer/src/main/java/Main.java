/*import javafx.application.Platform;
import net.thevpc.nuts.NOut;
import net.thevpc.nuts.Nuts;
import net.thevpc.nuts.util.NMsg;*/

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       // Force l'affichage des couleurs ANSI pour Nuts sous PowerShell
        //System.setProperty("nuts.term", "custom");

        //Nuts.require(new String[]{"--color=true"});        XHashtable maTable = new XHashtable(10);
        XHashtable maTable = new XHashtable(10);

        // --- 1. AJOUTS ---
        /*maTable.add("rabeb", 15);
        maTable.add("youssef", 25);
        maTable.add("ala", 7);
        maTable.add("sahar", 35);*/

        //NOut.println(NMsg.ofC("##:bright yellow:=== APRES AJOUTS ===##"));
        //XHashtableDrawer.draw(maTable);

        // --- 2. SUPPRESSION CORRIGÉE ---
       /* int indexSupprime = maTable.delete("youssef");

        NOut.println(NMsg.ofC("##:bright yellow:=== APRES DELETE youssef (index %d) ===##", indexSupprime));
        XHashtableDrawer.draw(maTable);

        // --- 3. RECHERCHE CORRIGÉE ---
        int r1 = maTable.find("rabeb");
        int r2 = maTable.find("youssef");

        NOut.println();
        // Utilisation stricte des balises ##:couleur:texte## pour chaque section dynamique
        NOut.println(NMsg.ofC("FIND ##:bright cyan:rabeb##   -> index ##:bright green:%d##", r1));
        NOut.println(NMsg.ofC("FIND ##:bright cyan:youssef## -> index ##:bright red:%d## ##:dark grey:(-1 = non trouvé)##", r2));
    }*/
       // XHashtable maTable = new XHashtable(10);

        // 2. Lancer l'interface graphique Swing
        SwingUtilities.invokeLater(() -> {
            XHashtableSwingDrawer visualizer = new XHashtableSwingDrawer();
            /*XHashtableFXPanel fxPanel = new XHashtableFXPanel();
            fxPanel.setTable(maTable.getTable());
            //on ajoute le composant javafx a la fenetre swing
            frame.add(fxPanel);
            frame.setVisible(true);
            System.out.println("L'interface graphique hybride Swing/JavaFX a été lancée avec succès !");*/
            visualizer.initGUI(maTable);
            //visualizer.initGUI(maTable);
            //System.out.println("L'interface Swing a été lancée avec succès !");

            //tester l'animation
           /* new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("déclechement d'une animation visuelle sur l'indice 3..");
                    fxPanel.StartAnimation(3, "ADD");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();*/
            // 3. AMÉLIORATION ESSENTIELLE : Forcer le démarrage du moteur JavaFX
            // par précaution, pour s'assurer que sa piste (Thread) est prête en arrière-plan.
            // Platform.startup(() -> {
            //Cette ligne s'exécute sur le FX Application Thread
            //System.out.println("Le moteur JavaFX est initialisé avec succès !");
            //Nuts.require(new String[]{"--color=true"});
            //AppShell app= new AppShell(10);
            //app.run();
//});

        });

    }
}