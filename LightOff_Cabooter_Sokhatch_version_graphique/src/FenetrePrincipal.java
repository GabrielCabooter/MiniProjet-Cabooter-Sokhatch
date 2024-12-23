/**
 * Nom du projet : CPASVERSAILLESICI
 * Auteurs : Gabriel Cabooter et Arthur Sokhatch
 * Date du projet : Du 20 novembre 2024 au 18 décembre 2024
 */
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.Timer;
import miniprojet.GrilleDeCellules;

/**
 * Fenêtre principale du jeu CPASVERSAILLESICI, où l'utilisateur peut jouer en fonction de la grille
 * et du niveau de difficulté sélectionné. Le temps et le nombre de coups sont suivis et le jeu
 * se termine lorsque toutes les cellules sont éteintes ou que le temps est écoulé.
 */
public class FenetrePrincipal extends javax.swing.JFrame {

    private GrilleDeCellules grille;
    private int nbCoups;
    private int nbLignes;
    private int tempsRestant; // Variable pour suivre le temps restant
    private Timer timer; // Le Timer pour mettre à jour le temps

    /**
     * Constructeur de la fenêtre principale. Initialise la grille, les boutons de lignes et de colonnes,
     * et configure le temps de jeu en fonction du niveau de difficulté (nbColonnes).
     *
     * @param nbLignes Nombre de lignes dans la grille
     * @param nbColonnes Nombre de colonnes dans la grille
     */
    public FenetrePrincipal(int nbLignes, int nbColonnes) {
        initComponents(); // Initialise tous les composants de l'interface, y compris jLabel1
        setTitle("CPASVERSAILLESICI!!!");
        setLocationRelativeTo(null);
        this.nbLignes = nbLignes; // Initialise le nombre de lignes
        this.grille = new GrilleDeCellules(nbLignes, nbColonnes);

        // Configuration des panneaux
        configureGrillePanel(nbLignes, nbColonnes);
        configureColonneButtons(nbColonnes);
        configureLigneButtons(nbLignes);

        // Initialisation du compteur de coups
        nbCoups = 0;
        jLabel2.setText("Coups: " + nbCoups); // Initialisation du texte du JLabel des coups

        // Définition du temps en fonction du nombre de colonnes (niveau de difficulté)
        switch (nbColonnes) {
            case 5:
                tempsRestant = 2 * 60;
                break;
            case 7:
                tempsRestant = 1 * 60;
                break;
            case 10:
                tempsRestant = 1 * 30;
                break;
            default:
                tempsRestant = 5 * 60;
        }

        // Création du Timer qui met à jour le temps chaque seconde
        timer = new Timer(1000, e -> {
            tempsRestant--; // Décrémente le temps restant
            jLabel1.setText("Timer  " + formatTemps(tempsRestant)); // Met à jour le texte de jLabel1

            // Si le temps est écoulé, arrête le jeu et affiche une fenêtre de fin
            if (tempsRestant <= 0) {
                timer.stop(); // Arrête le timer
                afficherFenetreFin(); // Affiche la fenêtre de fin
            }
        });

        // Démarre le Timer
        timer.start();

        this.pack(); // Ajuste la fenêtre à son contenu
    }

    /**
     * Formate le temps restant sous la forme mm:ss.
     *
     * @param tempsRestant Temps restant en secondes.
     * @return Le temps formaté sous forme "mm:ss".
     */
    private String formatTemps(int tempsRestant) {
        int minutes = tempsRestant / 60;
        int secondes = tempsRestant % 60;
        return String.format("%02d:%02d", minutes, secondes);
    }

    /**
     * Affiche la fenêtre de fin lorsque le jeu est terminé.
     */
    private void afficherFenetreFin() {
        FenetreFin fenetreFin = new FenetreFin(nbCoups);
        fenetreFin.setVisible(true);
        dispose(); // Ferme la fenêtre actuelle
    }

    /**
     * Vérifie si le joueur a gagné, c'est-à-dire si toutes les cellules sont éteintes.
     * Si le joueur a gagné, une fenêtre de victoire est affichée.
     */
    private void verifierVictoire() {
        if (grille.cellulesToutesEteintes()) {  // Vérifie si toutes les cellules sont éteintes
            timer.stop();
            afficherFenetreVictoire(); // Affiche la fenêtre de victoire
        }
    }

    /**
     * Affiche la fenêtre de victoire lorsque le joueur gagne.
     */
    private void afficherFenetreVictoire() {
        FenetreVictoire fenetreVictoire = new FenetreVictoire(nbCoups, tempsRestant, nbLignes);
        fenetreVictoire.setVisible(true); // Affiche la fenêtre de victoire
        dispose(); // Ferme la fenêtre principale
    }

    /**
     * Configure le panneau de la grille avec le nombre de lignes et de colonnes spécifié.
     * Le mélange des cellules est effectué en fonction de la difficulté du niveau.
     *
     * @param nbLignes Nombre de lignes dans la grille
     * @param nbColonnes Nombre de colonnes dans la grille
     */
    private void configureGrillePanel(int nbLignes, int nbColonnes) {
        // Déterminer le nombre de mélanges en fonction du nombre de lignes
        int nbMelanges;
        switch (nbLignes) {
            case 5:
                nbMelanges = 50; // Facile
                break;
            case 7:
                nbMelanges = 100; // Moyen
                break;
            case 10:
                nbMelanges = 200; // Difficile
                break;
            default:
                nbMelanges = 10; // Valeur par défaut si autre taille
                break;
        }

        // Configuration du panneau de la grille
        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes));

        // Mélange initial de la grille
        grille.melangerMatriceAleatoirement(nbMelanges);

        // Création des cellules graphiques
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                CelluleGraphique boutonCellule = new CelluleGraphique(grille.matriceCellules[i][j], 36, 36);
                PanneauGrille.add(boutonCellule);
            }
        }

        PanneauGrille.revalidate();
        PanneauGrille.repaint();
    }

    /**
     * Configure les boutons représentant les colonnes de la grille.
     *
     * @param nbColonnes Nombre de colonnes dans la grille.
     */
    private void configureColonneButtons(int nbColonnes) {
        PanneauBoutonsHorizontaux.removeAll();
        PanneauBoutonsHorizontaux.setLayout(new GridLayout(1, nbColonnes));
        for (int i = 0; i < nbColonnes; i++) {
            // Remplacement de "C1", "C2" par "▼"
            JButton btnColonne = new JButton("▼");
            int colIndex = i;
            btnColonne.addActionListener(evt -> {
                gererCoup(() -> grille.activerColonneDeCellules(colIndex));
            });
            PanneauBoutonsHorizontaux.add(btnColonne);
        }

        PanneauBoutonsHorizontaux.revalidate();
        PanneauBoutonsHorizontaux.repaint();
    }

    /**
     * Configure les boutons représentant les lignes de la grille.
     *
     * @param nbLignes Nombre de lignes dans la grille.
     */
    private void configureLigneButtons(int nbLignes) {
        PanneauBoutonsVerticaux.removeAll();
        PanneauBoutonsVerticaux.setLayout(new GridLayout(nbLignes, 1));
        for (int i = 0; i < nbLignes; i++) {
            // Remplacement de "L1", "L2" par "▶"
            JButton btnLigne = new JButton("▶");
            int rowIndex = i;
            btnLigne.addActionListener(evt -> {
                gererCoup(() -> grille.activerLigneDeCellules(rowIndex));
            });
            PanneauBoutonsVerticaux.add(btnLigne);
        }

        PanneauBoutonsVerticaux.revalidate();
        PanneauBoutonsVerticaux.repaint();
    }

    /**
     * Gère un coup effectué par le joueur (activation d'une ligne, colonne ou diagonale).
     * Incrémente le nombre de coups, met à jour l'affichage et vérifie si le joueur a gagné.
     *
     * @param action L'action à exécuter (activation d'une ligne, colonne ou diagonale).
     */
    private void gererCoup(Runnable action) {
        action.run(); // Exécute l'action (ligne, colonne ou diagonale)
        nbCoups++; // Incrémente le compteur de coups
        jLabel2.setText("Coups: " + nbCoups); // Met à jour le texte du JLabel avec le nombre de coups
        repaint(); // Redessine la grille

        verifierVictoire(); // Vérifie si le joueur a gagné
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauBoutonsVerticaux = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        PanneauBoutonsHorizontaux = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        PanneauBoutonsDiagonales = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        PanneauGrille = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PanneauTimer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanneauBoutonsVerticaux.setBackground(new java.awt.Color(204, 204, 204));
        PanneauBoutonsVerticaux.setLayout(new java.awt.GridLayout(10, 1));

        jButton1.setText("L1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        PanneauBoutonsVerticaux.add(jButton1);

        jButton2.setText("L2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PanneauBoutonsVerticaux.add(jButton2);

        jButton3.setText("L3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        PanneauBoutonsVerticaux.add(jButton3);

        jButton4.setText("L4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        PanneauBoutonsVerticaux.add(jButton4);

        jButton5.setText("L5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        PanneauBoutonsVerticaux.add(jButton5);

        jButton6.setText("L6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        PanneauBoutonsVerticaux.add(jButton6);

        jButton7.setText("L7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        PanneauBoutonsVerticaux.add(jButton7);

        jButton8.setText("L8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        PanneauBoutonsVerticaux.add(jButton8);

        jButton9.setText("L9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        PanneauBoutonsVerticaux.add(jButton9);

        jButton10.setText("L10");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        PanneauBoutonsVerticaux.add(jButton10);

        getContentPane().add(PanneauBoutonsVerticaux, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 90, 500));

        PanneauBoutonsHorizontaux.setLayout(new java.awt.GridLayout(1, 10));

        jButton13.setText("C1");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        PanneauBoutonsHorizontaux.add(jButton13);

        jButton14.setText("C2");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        PanneauBoutonsHorizontaux.add(jButton14);

        jButton15.setText("C3");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        PanneauBoutonsHorizontaux.add(jButton15);

        jButton16.setText("C4");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        PanneauBoutonsHorizontaux.add(jButton16);

        jButton17.setText("C5");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        PanneauBoutonsHorizontaux.add(jButton17);

        jButton18.setText("C6");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        PanneauBoutonsHorizontaux.add(jButton18);

        jButton19.setText("C7");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        PanneauBoutonsHorizontaux.add(jButton19);

        jButton20.setText("C8");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        PanneauBoutonsHorizontaux.add(jButton20);

        jButton21.setText("C9");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        PanneauBoutonsHorizontaux.add(jButton21);

        jButton22.setText("C10");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        PanneauBoutonsHorizontaux.add(jButton22);

        getContentPane().add(PanneauBoutonsHorizontaux, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 510, 50));

        PanneauBoutonsDiagonales.setLayout(new java.awt.GridLayout(1, 3));

        jButton11.setText("↗");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        PanneauBoutonsDiagonales.add(jButton11);

        jButton12.setText("↘");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        PanneauBoutonsDiagonales.add(jButton12);

        getContentPane().add(PanneauBoutonsDiagonales, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 570, 230, 50));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(null);

        PanneauGrille.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanneauGrilleLayout = new javax.swing.GroupLayout(PanneauGrille);
        PanneauGrille.setLayout(PanneauGrilleLayout);
        PanneauGrilleLayout.setHorizontalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        PanneauGrilleLayout.setVerticalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jPanel2.add(PanneauGrille);
        PanneauGrille.setBounds(140, 60, 510, 500);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new java.awt.GridLayout(1, 1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("nbCoups");
        jPanel1.add(jLabel2);

        jPanel2.add(jPanel1);
        jPanel1.setBounds(710, 260, 220, 70);

        PanneauTimer.setBackground(new java.awt.Color(0, 153, 153));
        PanneauTimer.setLayout(new java.awt.GridLayout(1, 1));

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Timer");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 50));
        PanneauTimer.add(jLabel1);

        jPanel2.add(PanneauTimer);
        PanneauTimer.setBounds(690, 140, 260, 70);

        jButton23.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jButton23.setText("Menu");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton23);
        jButton23.setBounds(700, 390, 240, 60);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        gererCoup(() -> this.grille.activerLigneDeCellules(0));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        gererCoup(() -> this.grille.activerLigneDeCellules(9));
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        gererCoup(() -> this.grille.activerLigneDeCellules(1));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        gererCoup(() -> this.grille.activerLigneDeCellules(2));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        gererCoup(() -> this.grille.activerLigneDeCellules(3));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        gererCoup(() -> this.grille.activerLigneDeCellules(4));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        gererCoup(() -> this.grille.activerLigneDeCellules(5));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        gererCoup(() -> this.grille.activerLigneDeCellules(6));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        gererCoup(() -> this.grille.activerLigneDeCellules(7));
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        gererCoup(() -> this.grille.activerLigneDeCellules(8));
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        gererCoup(() -> this.grille.activerColonneDeCellules(0));
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        gererCoup(() -> this.grille.activerColonneDeCellules(1));
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        gererCoup(() -> this.grille.activerColonneDeCellules(2));
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        gererCoup(() -> this.grille.activerColonneDeCellules(3));
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        gererCoup(() -> this.grille.activerColonneDeCellules(4));
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        gererCoup(() -> this.grille.activerColonneDeCellules(5));
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        gererCoup(() -> this.grille.activerColonneDeCellules(6));
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        gererCoup(() -> this.grille.activerColonneDeCellules(7));
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        gererCoup(() -> this.grille.activerColonneDeCellules(8));
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        gererCoup(() -> this.grille.activerColonneDeCellules(9));
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        gererCoup(() -> this.grille.activerDiagonaleDescendante());
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        gererCoup(() -> this.grille.activerDiagonaleMontante());
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        timer.stop();
        EcranAccueil ecranAccueil = new EcranAccueil();
        ecranAccueil.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton23ActionPerformed

    private FenetrePrincipal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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
            java.util.logging.Logger.getLogger(FenetrePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FenetrePrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanneauBoutonsDiagonales;
    private javax.swing.JPanel PanneauBoutonsHorizontaux;
    private javax.swing.JPanel PanneauBoutonsVerticaux;
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JPanel PanneauTimer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
