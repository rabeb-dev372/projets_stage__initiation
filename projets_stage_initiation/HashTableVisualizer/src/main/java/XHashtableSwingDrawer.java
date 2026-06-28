import javax.swing.*;
import java.awt.*;;
public class XHashtableSwingDrawer {
    private JFrame frame;
    private XHashtableFXDrawer panel;

    public void initGUI(XHashtable table) {
        frame = new JFrame("Visualiseur de Table de Hachage-javaSwing+javaFX");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 750);

        panel= new XHashtableFXDrawer();
        frame.add(panel, BorderLayout.CENTER);
        panel.setTable(table.getTable());

        // Barre de contrôle en haut
        JPanel controlPanel = new JPanel(new FlowLayout());
        JTextField keyField = new JTextField(8);
        JTextField valueField = new JTextField(5);

        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnFind = new JButton("Find");

        controlPanel.add(new JLabel("clé :"));
        controlPanel.add(keyField);
        controlPanel.add(new JLabel("valeur :"));
        controlPanel.add(valueField);
        controlPanel.add(btnAdd);
        controlPanel.add(btnDelete);
        controlPanel.add(btnFind);
        frame.add(controlPanel, BorderLayout.NORTH);

        //  BOUTON ADD
        btnAdd.addActionListener(e -> {
            String key = keyField.getText();
            String value = valueField.getText().trim();

            if (!key.isEmpty() && !value.isEmpty()) {
                try {
                    int valeur = Integer.parseInt(value);

                    // On insère l'entrée et on récupère la case de destination finale
                    int indexReel = table.add(key, valeur);

                    // On met à jour l'interface graphique
                    panel.setTable(table.getTable());
                    panel.StartAnimation(indexReel, "ADD");

                    // Vider les champs après l'insertion réussie
                    keyField.setText("");
                    valueField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre entier valide pour la valeur !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez remplir les deux champs !", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        });

        // BOUTON DELETE
        btnDelete.addActionListener(e -> {
            String key = keyField.getText(); // On cherche l'élément à supprimer par sa clé (texte)

            if (!key.isEmpty()) {
                // On délègue la recherche de l'index et la suppression directement à la structure
                int ind = table.delete(key);

                if (ind != -1) {
                    panel.setTable(table.getTable());
                    panel.StartAnimation(ind, "DELETE");

                    // On vide le champ après suppression
                    keyField.setText("");
                    valueField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Clé non trouvée !", "Élément introuvable", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez saisir la clé à supprimer !", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        });

        // BOUTON FIND
        btnFind.addActionListener(e -> {
            String key = keyField.getText().trim(); // On cherche à l'aide de la clé textuelle

            if (!key.isEmpty()) {
                int indiceCherche = table.find(key);

                if (indiceCherche != -1) {
                    // On lance l'animation sur la bonne alvéole trouvée
                    panel.StartAnimation(indiceCherche, "FIND");
                } else {
                    JOptionPane.showMessageDialog(frame, "Clé introuvable !", "Résultat", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez saisir la clé à rechercher !", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}