
/**
 * Nom du projet : CPASVERSAILLESICI
 * Auteurs : Gabriel Cabooter et Arthur Sokhatch
 * Date du projet : Du 20 novembre 2024 au 18 décembre 2024
 */
import javax.swing.*;

/**
 * Fenêtre affichant les règles du jeu CPASVERSAILLESICI.
 * Cette fenêtre contient un texte détaillant les règles et les instructions pour jouer au jeu.
 */
public class RegleDuJeu extends JFrame {

    /**
     * Constructeur de la classe RegleDuJeu.
     * Initialise les composants et affiche les règles du jeu dans la fenêtre.
     */
    public RegleDuJeu() {
        initComponents();
        setTitle("Règles du Jeu");

        setSize(700, 720); // Taille de la fenêtre

        setLocationRelativeTo(null); // Placer la fenêtre au centre de l'écran
        // Initialiser les composants
        // Ajouter les règles du jeu après l'initialisation des composants
        String reglesJeu
                = """
                                                                
                                                                   Bienvenue dans CPASVERSAILLESICI !
            CPAVERSAILLESICI est un jeu de réflexion dans lequel votre objectif est d’éteindre toutes les lumières d'une grille. 
            Les lumières peuvent être allumées ou éteintes, et vous devez utiliser des stratégies pour résoudre la grille en un minimum de coups.
            
                                                                     1. Présentation du Plateau :
           
            Le plateau de jeu est constitué d'une grille de pions, chaque pion pouvant être dans l'un des deux états suivants :
            
            - Allumé : Le pion est Allumé (Jaune)
            - Éteint : Le pion est (Noire)
            
            Les pions sont disposés de manière aléatoire au début de chaque partie.
            
                                                                       2. Comment Jouer ?
            
            Pour changer l'état des pions, vous pouvez interagir avec le plateau de trois manières :
            
            - Activer une ligne : Cliquez sur un bouton représentant une ligne pour inverser l'état de tous les pions de cette ligne (allumé devient éteint et vice versa).
            - Activer une colonne : Cliquez sur un bouton représentant une colonne pour inverser l'état de tous les pions de cette colonne.
            - Activer une diagonale : Cliquez sur un bouton représentant une diagonale pour inverser l'état de tous les pions de cette diagonale.
            
                                                          Votre objectif est d’éteindre tous les pions.
            
                                                                    3. Niveaux de difficulté :
            
                                                  - **Facile** : Grille de 5x5 et 5 minutes pour résoudre.
                                           - **Moyen** : Grille de 7x7 et 3 minutes pour résoudre.
                                             - **Difficile** : Grille de 10x10 et 2 minutes pour résoudre.
            
                                                                        A VOUS DE JOUER
            """;

        // Mettre le texte dans la JTextArea
        jTextArea1.setText(reglesJeu);
        jTextArea1.setEditable(false); // Empêcher l'édition du texte
        jTextArea1.setLineWrap(true); // Activer le retour à la ligne automatique
        jTextArea1.setWrapStyleWord(true); // Le texte se coupe aux espaces pour éviter de couper des mots
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 700));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setBackground(new java.awt.Color(0, 153, 153));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setPreferredSize(new java.awt.Dimension(600, 600));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 660, 590));

        jButton3.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jButton3.setText("Retour");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 620, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 349, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(615, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        EcranAccueil ecranAccueil = new EcranAccueil();
        ecranAccueil.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(RegleDuJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegleDuJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegleDuJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegleDuJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RegleDuJeu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
