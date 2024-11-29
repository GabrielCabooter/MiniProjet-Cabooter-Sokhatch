/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet;

import java.util.Random;


/**
 *
 * @author asokhatch
 */
public class GrilleDeCellules {
    /**
     * Matrice de cellules lumineuses organisées en lignes et colonnes.
     * Chaque cellule est une instance de la classe {@link CelluleLumineuse}.
     */
    private CelluleLumineuse[][] matriceCellules;

    /**
     * Le nombre de lignes dans la grille.
//     */
    private int nbLignes;

    /**
     * Le nombre de colonnes dans la grille.
     */
    private int nbColonnes;

    /**
     * Constructeur de la grille de cellules.
     * Initialise une matrice de cellules lumineuses avec des dimensions données.
     * 
     * @param p_nbLignes   Le nombre de lignes de la grille.
     * @param p_nbColonnes Le nombre de colonnes de la grille.
     */
    public GrilleDeCellules(int p_nbLignes, int p_nbColonnes) {
        this.nbLignes = p_nbLignes;
        this.nbColonnes = p_nbColonnes;

        // Initialisation de la matrice de cellules
        matriceCellules = new CelluleLumineuse[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new CelluleLumineuse(); // Initialisation de chaque cellule lumineuse
            }
        }
    }
// Méthode pour obtenir le nombre de lignes
    public int getNbLignes() {
        return nbLignes;
    }

    // Méthode pour obtenir le nombre de colonnes
    public int getNbColonnes() {
        return nbColonnes;
    }
    
    // Méthode pour obtenir une cellule spécifique
    public CelluleLumineuse getCellule(int ligne, int colonne) {
        if (ligne >= 0 && ligne < nbLignes && colonne >= 0 && colonne < nbColonnes) {
            return matriceCellules[ligne][colonne];
        } else {
            throw new IndexOutOfBoundsException("Indices de cellule invalides");
        }
    }
    /**
     * Éteint toutes les cellules de la grille.
     * Chaque cellule est mise à l'état "éteint".
     */
    public void eteindreToutesLesCellules() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j].eteindreCellule(); // Éteindre chaque cellule
            }
        }
    }

    /**
     * Active toutes les cellules d'une ligne spécifique.
     * 
     * @param idLigne L'indice de la ligne à activer (0-indexé).
     * @throws IndexOutOfBoundsException si l'indice de la ligne est invalide.
     */
    public void activerLigneDeCellules(int idLigne) {
        if (idLigne >= 0 && idLigne < nbLignes) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[idLigne][j].allumerCellule(); // Allumer chaque cellule de la ligne
            }
        }
    }

    /**
     * Active toutes les cellules d'une colonne spécifique.
     * 
     * @param idColonne L'indice de la colonne à activer (0-indexé).
     * @throws IndexOutOfBoundsException si l'indice de la colonne est invalide.
     */
    public void activerColonneDeCellules(int idColonne) {
        if (idColonne >= 0 && idColonne < nbColonnes) {
            for (int i = 0; i < nbLignes; i++) {
                matriceCellules[i][idColonne].allumerCellule(); // Allumer chaque cellule de la colonne
            }
        }
    }

    /**
     * Active la diagonale descendante de la grille (haut-gauche à bas-droite).
     * La diagonale descendante correspond aux cellules où l'indice de ligne est égal à l'indice de colonne.
     */
    public void activerDiagonaleDescendante() {
        for (int i = 0; i < Math.min(nbLignes, nbColonnes); i++) {
            matriceCellules[i][i].allumerCellule(); // Allumer les cellules de la diagonale descendante
        }
    }

    /**
     * Active la diagonale montante de la grille (bas-gauche à haut-droit).
     * La diagonale montante correspond aux cellules où l'indice de ligne est égal à nbColonnes - 1 - indice de colonne.
     */
    public void activerDiagonaleMontante() {
        for (int i = 0; i < Math.min(nbLignes, nbColonnes); i++) {
            matriceCellules[i][nbColonnes - 1 - i].allumerCellule(); // Allumer les cellules de la diagonale montante
        }
    }

    /**
     * Vérifie si toutes les cellules de la grille sont éteintes.
     * 
     * @return true si toutes les cellules sont éteintes, false sinon.
     */
    public boolean cellulesToutesEteintes() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].estEteint()) { // Vérifie si une cellule est allumée
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Active aléatoirement une ligne, une colonne ou une diagonale.
     * Choisi de manière aléatoire l'élément à activer (ligne, colonne ou diagonale).
     */
    public void activerLigneColonneOuDiagonaleAleatoire() {
        Random rand = new Random();
        int choix = rand.nextInt(4); // 0 à 3 pour choisir l'activation
        int index = rand.nextInt(Math.max(nbLignes, nbColonnes));
        switch (choix) {
            case 0 -> activerLigneDeCellules(index % nbLignes); // Activer une ligne
            case 1 -> activerColonneDeCellules(index % nbColonnes); // Activer une colonne
            case 2 -> activerDiagonaleDescendante(); // Activer la diagonale descendante
            case 3 -> activerDiagonaleMontante(); // Activer la diagonale montante
        }
    }

    /**
     * Mélange la grille en activant de manière aléatoire une ligne, une colonne ou une diagonale de manière répétée.
     * 
     * @param nbTours Le nombre de tours pendant lesquels les activations doivent être effectuées.
     */
    public void melangerMatriceAleatoirement(int nbTours) {
        eteindreToutesLesCellules(); // Commence par éteindre toutes les cellules
        for (int i = 0; i < nbTours; i++) {
            activerLigneColonneOuDiagonaleAleatoire(); // Activations aléatoires sur chaque tour
        }
    }

    /**
     * Renvoie une représentation visuelle de la grille.
     * Chaque cellule est représentée par "X" si elle est allumée et "O" si elle est éteinte.
     * 
     * @return Une chaîne de caractères représentant la grille.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   "); // Espacement pour les indices des colonnes
        for (int j = 0; j < nbColonnes; j++) {
            sb.append(j).append(" "); // Ajouter l'indice de la colonne
        }
        sb.append("\n");

        for (int i = 0; i < nbLignes; i++) {
            sb.append(i).append(" "); // Ajouter l'indice de la ligne
            if (i < 10) sb.append(" "); // Aligner les lignes dont l'indice est inférieur à 10

            for (int j = 0; j < nbColonnes; j++) {
                sb.append(matriceCellules[i][j].getEtat() ? "X " : "O "); // Afficher l'état des cellules (X pour allumée, O pour éteinte)
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    
}
