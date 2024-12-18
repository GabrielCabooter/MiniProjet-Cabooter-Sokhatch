
/**
 * Nom du projet : CPASVERSAILLESICI
 * Auteurs : Gabriel Cabooter et Arthur Sohkatch
 * Date du projet : Du 20 novembre 2024 au 18 décembre 2024
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Fenêtre de victoire qui affiche les scores des joueurs et permet
 * d'enregistrer le score actuel. La fenêtre affiche un tableau des scores
 * précédents et permet à l'utilisateur de saisir son nom pour enregistrer son
 * score après avoir gagné.
 */
public class FenetreVictoire extends javax.swing.JFrame {

    private List<Score> scores;  // Liste des scores enregistrés
    private DefaultTableModel tableModel;  // Modèle de table pour afficher les scores
    private int tempsInitial;  // Temps initial en fonction de la difficulté

    /**
     * Constructeur de la fenêtre de victoire. Initialise la fenêtre, charge les
     * scores précédents, enregistre le score actuel, et met à jour le tableau
     * des scores.
     *
     * @param nbCoups Le nombre de coups effectués pendant la partie.
     * @param tempsRestant Le temps restant à la fin de la partie.
     * @param nbLignes Le nombre de lignes dans la grille, utilisé pour
     * déterminer la difficulté.
     */
    public FenetreVictoire(int nbCoups, int tempsRestant, int nbLignes) {
        // Initialisation des composants de la fenêtre
        initComponents();

        scores = ScoreManager.chargerScores();  // Charge les scores précédents depuis le fichier
        setTitle("Victoire !");
        setSize(600, 630);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Déterminer la difficulté et initialiser le temps en fonction de celle-ci
        String difficulte = determinerDifficulte(nbLignes);
        initialiserTemps(difficulte);

        // Enregistrer le score du joueur
        enregistrerScore(nbCoups, tempsRestant, difficulte);

        // Sauvegarde les scores dans le fichier
        ScoreManager.sauvegarderScores(scores);

        // Initialiser le modèle de table et mettre à jour le tableau des scores
        initialiserTableModel();
        mettreAJourTableau();
    }

    /**
     * Détermine la difficulté en fonction du nombre de lignes.
     *
     * @param nbLignes Le nombre de lignes dans la grille.
     * @return Une chaîne représentant la difficulté ("Facile", "Moyenne", ou
     * "Difficile").
     */
    private String determinerDifficulte(int nbLignes) {
        switch (nbLignes) {
            case 5:
                return "Facile";
            case 7:
                return "Moyenne";
            default:
                return "Difficile";
        }
    }

    /**
     * Initialise le temps en fonction de la difficulté choisie.
     *
     * @param difficulte La difficulté du jeu (Facile, Moyenne, ou Difficile).
     */
    private void initialiserTemps(String difficulte) {
        switch (difficulte) {
            case "Facile":
                tempsInitial = 2 * 60;  // 2 minutes pour la difficulté Facile
                break;
            case "Moyenne":
                tempsInitial = 1 * 60;  // 1 minute pour la difficulté Moyenne
                break;
            case "Difficile":
                tempsInitial = 30;  // 30 secondes pour la difficulté Difficile
                break;
            default:
                tempsInitial = 0;  // Valeur par défaut
        }
    }

    /**
     * Transforme le temps restant en temps joué (différence entre le temps
     * initial et le temps restant).
     *
     * @param tempsRestant Le temps restant à la fin de la partie.
     * @return Le temps joué au format "mm:ss".
     */
    private String formatTempsJoue(int tempsRestant) {
        // Le temps joué est la différence entre le temps initial et le temps restant
        int tempsJoue = tempsInitial - tempsRestant;
        int minutes = tempsJoue / 60;
        int secondes = tempsJoue % 60;
        return String.format("%02d:%02d", minutes, secondes);
    }

    /**
     * Enregistre le score du joueur après la partie. Permet au joueur d'entrer
     * son nom et enregistre le score avec les informations correspondantes.
     *
     * @param nbCoups Le nombre de coups effectués pendant la partie.
     * @param tempsRestant Le temps restant à la fin de la partie.
     * @param difficulte La difficulté choisie pour le jeu.
     */
    private void enregistrerScore(int nbCoups, int tempsRestant, String difficulte) {
        String joueur = JOptionPane.showInputDialog(this, "Entrez votre nom :", "Nom du joueur", JOptionPane.QUESTION_MESSAGE);
        if (joueur == null || joueur.isBlank()) {
            joueur = "Anonyme";  // Si le joueur ne saisit pas de nom, il est enregistré sous "Anonyme"
        }
        Score score = new Score(joueur, nbCoups, tempsRestant, difficulte);
        scores.add(score);
        scores.sort((s1, s2) -> Integer.compare(s1.getScore(), s2.getScore())); // Trie les scores de manière croissante
    }

    /**
     * Initialise le modèle de la table pour afficher les scores.
     */
    private void initialiserTableModel() {
        // Crée un modèle de table avec les colonnes appropriées
        tableModel = new DefaultTableModel(new Object[]{"Joueur", "Coups", "Temps Joué", "Difficulté", "Score"}, 0);
        jTable1.setModel(tableModel);  // Assigne le modèle de table à votre jTable1
    }

    /**
     * Met à jour le tableau des scores avec les scores enregistrés.
     */
    private void mettreAJourTableau() {
        tableModel.setRowCount(0);  // Réinitialise le tableau
        for (Score score : scores) {
            // Calcule le temps joué et l'ajoute à la table
            String tempsJoue = formatTempsJoue(score.getTempsRestant());

            // Ajoute une ligne au tableau avec les informations du score
            tableModel.addRow(new Object[]{
                score.getJoueur(),
                score.getNbCoups(),
                tempsJoue, // Affichage du temps joué
                score.getDifficulte(),
                score.getScore()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel1.setText("VICTOIRE !");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jButton3.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jButton3.setText("Quitter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jButton4.setText("Rejouer");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jButton5.setText("Menu");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(0, 153, 153));
        jTable1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Joueur", "nbCoups", "TempsRestant", "Difficulté", "Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 358, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 83, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        ScoreManager.reinitialiserScores(); // Réinitialisation des scores
        System.exit(0); // Ferme l'application
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ChoixDiff choixDiff = new ChoixDiff();
        choixDiff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        EcranAccueil ecranAccueil = new EcranAccueil();
        ecranAccueil.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetreVictoire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreVictoire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreVictoire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreVictoire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FenetreVictoire().setVisible(true);
        });
    }

    private FenetreVictoire() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
