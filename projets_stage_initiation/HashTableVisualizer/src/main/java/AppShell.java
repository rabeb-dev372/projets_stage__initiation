/*import net.thevpc.nuts.NOut;
import net.thevpc.nuts.util.NMsg;

import java.util.LinkedList;

public class AppShell {
    private XHashtable maTable;
    private int tailleInitiale;

    // Le constructeur prépare la taille de la table de hachage
    public AppShell(int tailleInitiale) {
        this.tailleInitiale = tailleInitiale;
    }

    // La méthode run() exécute le scénario de test textuel demandé par le prof
    public void run() {
        System.setProperty("nuts.term", "custom"); // Force les couleurs sous PowerShell/Terminal

        // 1. Instanciation de la table de hachage brute
        this.maTable = new XHashtable(tailleInitiale);

        NOut.println(NMsg.ofC("##:bright blue:=== ÉTAPE 1 : INITIALISATION DE LA TABLE (%d cases) ===##", tailleInitiale));

        // 2. --- SCÉNARIO : AJOUTS ---
        maTable.add("rabeb", 15);
        maTable.add("youssef", 25);
        maTable.add("ala", 7);
        maTable.add("sahar", 35);

        NOut.println(NMsg.ofC("\n##:bright yellow:=== APRES AJOUTS EN CONSOLE ===##"));
        afficherTableEnConsole();

        // 3. --- SCÉNARIO : SUPPRESSION ---
        String cleASupprimer = "youssef";
        int indexSupprime = maTable.delete(cleASupprimer);

        NOut.println(NMsg.ofC("\n##:bright red:=== APRES DELETE %s (index %d) ===##", cleASupprimer, indexSupprime));
        afficherTableEnConsole();

        // 4. --- SCÉNARIO : RECHERCHE ---
        int r1 = maTable.find("rabeb");
        int r2 = maTable.find("youssef");

        NOut.println();
        NOut.println(NMsg.ofC("RECHERCHE ##:bright cyan:rabeb##   -> trouvé à l'index ##:bright green:%d##", r1));
        NOut.println(NMsg.ofC("RECHERCHE ##:bright cyan:youssef## -> résultat : ##:bright red:%d## (Non trouvé)", r2));
    }

    // Petite méthode utilitaire pour dessiner la table avec des caractères texte
    private void afficherTableEnConsole() {
        NodeList[] data = maTable.getTable();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                System.out.printf("[%02d] : [ vide ]\n", i);
            } else {
                System.out.printf("[%02d] : [ %s ]\n", i, data[i].toString());
            }
        }
    }
}*/