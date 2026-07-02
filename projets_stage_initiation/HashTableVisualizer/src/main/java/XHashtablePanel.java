import javax.swing.*;
import java.awt.*;

public class XHashtablePanel extends JPanel {
    private NodeList[] table;

    // Variables pour gérer l'animation de déplacement
    private int animationIndice = -1;
    private String animationType = "";
    private int animX = 0;          // Position X actuelle du bloc qui se déplace
    private int cibleX = 0;         // Position X finale où le bloc doit s'arrêter
    private String cleAnimee = "";  // La clé du nœud en cours d'ajout
    private int valeurAnimee = 0;   // La valeur du nœud en cours d'ajout
    private boolean enDeplacement = false;
    private Timer moveTimer;

    public XHashtablePanel() {
        this.table = new NodeList[10];
        setBackground(Color.WHITE);
    }

    public void setTable(NodeList[] table) {
        this.table = table;
        repaint();
    }

    public void startAnimation(int index, String type) {
        this.animationIndice = index;
        this.animationType = type;

        if (type.equals("ADD") && table[index] != null && !table[index].isEmpty()) {
            // 1. Récupérer les données du dernier nœud qui vient d'être inséré
            Node courant = table[index].getTete();
            int eleIndex = 0;
            while (courant.next != null) {
                courant = courant.next;
                eleIndex++;
            }
            this.cleAnimee = courant.getKey();
            this.valeurAnimee = courant.getValue();

            // 2. Calculer le point de départ (la case du tableau d'index) et la cible finale
            this.animX = 40; // Commence au niveau de la case th
            this.cibleX = 150 + eleIndex * (60 + 25 + 30); // Position finale X de ce maillon en pixcel
            this.enDeplacement = true;

            // 3. Lancer le Timer de déplacement fluide (60 images par seconde)
            if (moveTimer != null && moveTimer.isRunning()) {
                moveTimer.stop();
            }

            moveTimer = new Timer(16, e -> {
                if (animX < cibleX) {
                    animX += 8; // Vitesse du déplacement (pixels par frame)
                    if (animX > cibleX){ animX = cibleX;}
                } else {
                    // Le déplacement est fini !
                    ((Timer) e.getSource()).stop();
                    enDeplacement = false;
                    animationIndice = -1;
                    animationType = "";
                }
                repaint();
            });
            moveTimer.start();
        } else {
            // Pour DELETE et FIND, on garde le clignotement classique standard
            final int[] clignotements = {0};
            Timer flashTimer = new Timer(200, e -> {
                clignotements[0]++;
                animationIndice = (animationIndice == index) ? -1 : index;
                repaint();

                if (clignotements[0] >= 6) {
                    ((Timer) e.getSource()).stop();
                    animationIndice = -1;
                    animationType = "";
                    repaint();
                }
            });
            flashTimer.start();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int positionInitY = 60;
        int hautCase = 40;
        int espace = 15;

        int largeurIndex = 40;
        int largeurDonnee = 70;
        int largeurPointeur = 25;

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("HashTable", 50, positionInitY - 15);

        for (int i = 0; i < table.length; i++) {
            int tabX = 40;
            int posY = positionInitY + i * (hautCase + espace);

            // Indice à gauche
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, 14));
            g2d.drawString(String.valueOf(i), tabX - 20, posY + 25);

            // Couleur de base de la ligne th
            if (i == animationIndice && !enDeplacement) {
                if (animationType.equals("DELETE")) g2d.setColor(Color.RED);
                else if (animationType.equals("FIND")) g2d.setColor(Color.CYAN);
            } else {
                g2d.setColor(Color.WHITE);
            }

            // dessiner une case stataique
            g2d.fillRect(tabX, posY, largeurIndex, hautCase);//dessiner un rectangle plein
            g2d.setColor(Color.BLACK);
            g2d.drawRect(tabX, posY, largeurIndex, hautCase);//dessiner la bordure du rectangle
            g2d.drawString(String.valueOf(i), tabX + 15, posY + 25);

            if (table[i] == null || table[i].isEmpty()) {
                g2d.setColor(Color.GRAY);
                g2d.drawString("null", tabX + largeurIndex + 15, posY + 25);
            } else {
                int currentX = 150;

                // Flèche initiale
                g2d.setColor(Color.BLACK);
                g2d.drawLine(tabX + largeurIndex, posY + (hautCase / 2), currentX, posY + (hautCase / 2));

                int maillonIndex = 0;
                Node courant = table[i].getTete();

                while (courant != null) {
                    // Si on est en train d'animer l'ajout de CE maillon précis, on cache sa version fixe
                    // tant que le bloc mobile n'est pas arrivé à destination !
                    if (enDeplacement && i == animationIndice && courant.next == null) {
                        break;
                    }

                    // Dessin normal du maillon fixe stable
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(currentX, posY, largeurDonnee, hautCase);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(currentX, posY, largeurDonnee, hautCase);
                    g2d.drawString(courant.getKey() + ":" + courant.getValue(), currentX + 6, posY + 25);

                    int ptrX = currentX + largeurDonnee;
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(ptrX, posY, largeurPointeur, hautCase);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(ptrX, posY, largeurPointeur, hautCase);

                    if (courant.next == null) {
                        g2d.drawString("nul", ptrX + 4, posY + 25);
                    } else {
                        //g2d.fillOval(ptrX + (largeurPointeur / 2) - 3, posY + (hautCase / 2) - 3, 6, 6);
                        int prochainX = currentX + largeurDonnee + largeurPointeur + 30;
                        g2d.drawLine(ptrX + (largeurPointeur / 2), posY + (hautCase / 2), prochainX, posY + (hautCase / 2));
                        currentX = prochainX;
                    }

                    maillonIndex++;
                    courant = courant.next;
                }
            }

            // Si on est en train de déplacer un bloc sur cette ligne
            if (enDeplacement && i == animationIndice) {
                int animY = positionInitY + i * (hautCase + espace);

                // Dessiner le bloc vert qui glisse le long de la ligne
                g2d.setColor(Color.GREEN);
                g2d.fillRect(animX, animY, largeurDonnee, hautCase);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(animX, animY, largeurDonnee, hautCase);

                // Texte à l'intérieur du bloc en déplacement
                g2d.setFont(new Font("Arial", Font.BOLD, 12));
                g2d.drawString(cleAnimee + ":" + valeurAnimee, animX + 6, animY + 25);
            }
        }
    }
}
/*import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
public class XHashtablePanel extends JPanel {
    private NodeList[]table;
    private int animationIndice=-1;//initiallement aucune case ne clignote
    private String animationType;
    public XHashtablePanel() {
        this.table = new NodeList[10];
        setBackground(Color.WHITE);
    }
    public void setTable(NodeList[] table){
        this.table=table;
        repaint();
    }
    public void startAnimation(int index, String type){
        this.animationIndice=index;
        this.animationType=type;
        final int []clignotements = {0};//un compteur de clignottements
        Timer timer= new Timer(300, e ->{
            clignotements[0]++;
            if(animationIndice==index){
                    animationIndice= -1;
            }else{
                animationIndice= index;
            }
            repaint();

            if (clignotements[0] >= 6){
                ((Timer)e.getSource()).stop();
                animationIndice = -1;
                animationType = "";
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Rendre les lignes et flèches lisses
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int positionInitY = 60;
        int hautCase = 40;
        int espace = 15; // Espace entre 2 lignes

        int largeurIndex = 40;   // Case du tableau statique th
        int largeurDonnee = 60;
        int largeurPointeur = 25;

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("HashTable", 50, positionInitY - 15);

        for (int i = 0; i < table.length; i++) {
            int tabX = 40;
            int posY = positionInitY + i * (hautCase + espace);

            // 1. Dessiner l'indice à côté de la case
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, 14));
            g2d.drawString(String.valueOf(i), tabX - 20, posY + 25);

            // 2. Gestion de la couleur de la case th (Animation ou Blanc de base)
            if (i == animationIndice) {
                if (animationType.equals("ADD")) g2d.setColor(Color.GREEN);
                else if (animationType.equals("DELETE")) g2d.setColor(Color.RED);
                else if (animationType.equals("FIND")) g2d.setColor(Color.CYAN);
            } else {
                g2d.setColor(Color.WHITE);
            }

            // 3. Dessiner le rectangle th du tableau statique
            g2d.fillRect(tabX, posY, largeurIndex, hautCase);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(tabX, posY, largeurIndex, hautCase);

            // Écrire l'indice calculé directement à l'intérieur de la case th
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString(String.valueOf(i), tabX + 15, posY + 25);

            // --- CAS 1 : LA CASE EST VIDE ---
            if (table[i] == null || table[i].isEmpty()) {
                g2d.setColor(Color.GRAY);
                g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                g2d.drawString("null", tabX + largeurIndex + 15, posY + 25);
            }
            // --- CAS 2 : IL Y A DES NOEUDS EN COLLISION ---
            else {
                int currentX = 150; // Position X du tout premier maillon à droite

                // Dessiner la flèche initiale qui part de th vers la liste
                g2d.setColor(Color.BLACK);
                g2d.drawLine(tabX + largeurIndex, posY + (hautCase / 2), currentX, posY + (hautCase / 2));
                g2d.drawLine(currentX, posY + (hautCase / 2), currentX - 5, posY + (hautCase / 2) - 4);
                g2d.drawLine(currentX, posY + (hautCase / 2), currentX - 5, posY + (hautCase / 2) + 4);

                int maillonIndex = 0;
                Node courant = table[i].getTete();

                // Parcours propre de la liste chaînée
                while (courant != null) {

                    // On remet la couleur du pinceau pour les maillons
                    g2d.setColor(Color.WHITE);

                    // Dessiner la case "Donnée" du nœud
                    g2d.fillRect(currentX, posY, largeurDonnee, hautCase);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(currentX, posY, largeurDonnee, hautCase);
                    g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                    g2d.drawString(courant.getKey() + ":" + courant.getValue(), currentX + 6, posY + 25);

                    // Dessiner la case "Pointeur" (le rectangle next)
                    int ptrX = currentX + largeurDonnee;
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(ptrX, posY, largeurPointeur, hautCase);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(ptrX, posY, largeurPointeur, hautCase);

                    // Si c'est le dernier élément
                    if (courant.next == null) {
                        g2d.setFont(new Font("Arial", Font.PLAIN, 11));
                        g2d.drawString("nul", ptrX + 4, posY + 25);
                    } else {
                        // Si un autre nœud suit : dessiner le point d'ancrage + la flèche suivante
                        g2d.fillOval(ptrX + (largeurPointeur / 2) - 3, posY + (hautCase / 2) - 3, 6, 6);

                        int prochainX = currentX + largeurDonnee + largeurPointeur + 30;
                        g2d.drawLine(ptrX + (largeurPointeur / 2), posY + (hautCase / 2), prochainX, posY + (hautCase / 2));
                        g2d.drawLine(prochainX, posY + (hautCase / 2), prochainX - 5, posY + (hautCase / 2) - 4);
                        g2d.drawLine(prochainX, posY + (hautCase / 2), prochainX - 5, posY + (hautCase / 2) + 4);

                        currentX = prochainX; // Décaler X pour le maillon suivant
                    }

                    maillonIndex++;
                    courant = courant.next; // AVANCEMENT DU POINTEUR (évite la boucle infinie !)
                }
            }
        }
    }
}*/