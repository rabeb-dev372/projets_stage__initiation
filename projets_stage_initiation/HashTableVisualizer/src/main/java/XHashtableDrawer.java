/*import net.thevpc.nuts.NOut;
import net.thevpc.nuts.util.NMsg;

import java.util.LinkedList;

public class XHashtableDrawer {

    public static void draw(XHashtable table) {
        NodeList[] data = table.getTable();

        NOut.println(NMsg.ofC("##:bright yellow:=== HASH TABLE ===##"));
        NOut.println(NMsg.ofC("##:bright yellow:______________________##"));

        String[] couleurs = {
                "bright red", "bright green", "bright blue", "bright yellow",
                "bright cyan", "bright magenta", "red", "green", "blue", "cyan"
        };

        for (int i = 0; i < data.length; i++) {
            String clr = couleurs[i % couleurs.length];

            // 1. On affiche d'abord l'index vertical de la table (ex: [00], [01])
            NOut.print(NMsg.ofC("##:" + clr + ":[%02d]## : ", i));

            // 2. CORRECTION : On vérifie si la liste de cette case est vide
            if (data[i].isEmpty()) {
                NOut.println(NMsg.ofC("##:dark grey:(vide)##"));
            } else {
                // 3. CORRECTION : On parcourt tous les éléments de la LinkedList horizontale
                int compteur = 0;
                Node courant = data[i].getTete();
                while (courant != null)   {
                    if (compteur > 0) {
                        // On affiche une flèche de liaison s'il y a une collision (plusieurs wagons)
                        NOut.print(NMsg.ofC(" ##:dark grey:->## "));
                    }
                    // On affiche le couple Clé:Valeur du wagon actuel
                    NOut.print(NMsg.ofC("[##:" + clr + ":%s##:##:bright white:%d##]", courant.getKey(), courant.getValue()));
                    compteur++;
                }
                NOut.println(); // Retour à l
        }
        NOut.println();
    }
}}*/