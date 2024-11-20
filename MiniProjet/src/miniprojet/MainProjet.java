/*
 * Projet LightsOff de Gabriel Cabooter et Arthur Sokhatch 
 * Du 20/11/2024 au ???
 */
package miniprojet;

/**
 *
 * 
 */
public class MainProjet {

    /**
     * Point d'entrée pour tester les fonctionnalités de la classe CelluleLumineuse.
     * Crée des instances de CelluleLumineuse, manipule leur état et affiche
     * les résultats pour vérifier les comportements attendus.
     * 
     * @param args arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
    CelluleLumineuse cellule = new CelluleLumineuse();
        System.out.println(cellule); // Affiche "O"
        
        cellule.allumerCellule();
        System.out.println(cellule); // Affiche "X"

        cellule.eteindreCellule();
        System.out.println(cellule); // Affiche "O"
        
        System.out.println("Est éteinte: " + cellule.estEteint()); // Affiche true
        System.out.println("État: " + cellule.getEtat()); // Affiche false    
    
    // Création d'une grille de 5 lignes et 5 colonnes
        GrilleDeCellules grille = new GrilleDeCellules(5, 5);
        
        // Affichage initial de la grille (toutes les cellules sont éteintes)
        System.out.println("Grille initiale (toutes les cellules éteintes) :");
        System.out.println(grille);
        
        // Mélange de la grille (activation aléatoire de cellules)
        grille.melangerMatriceAleatoirement(10);
        System.out.println("Grille après mélange aléatoire (10 tours) :");
        System.out.println(grille);
        
        // Test de la méthode activerLigneDeCellules
        System.out.println("Activation de la ligne 2 :");
        grille.activerLigneDeCellules(2);
        System.out.println(grille);

        // Test de la méthode activerColonneDeCellules
        System.out.println("Activation de la colonne 3 :");
        grille.activerColonneDeCellules(3);
        System.out.println(grille);

        // Test de la méthode activerDiagonaleDescendante
        System.out.println("Activation de la diagonale descendante :");
        grille.activerDiagonaleDescendante();
        System.out.println(grille);

        // Test de la méthode activerDiagonaleMontante
        System.out.println("Activation de la diagonale montante :");
        grille.activerDiagonaleMontante();
        System.out.println(grille);

        // Test de la méthode eteindreToutesLesCellules
        System.out.println("Extinction de toutes les cellules :");
        grille.eteindreToutesLesCellules();
        System.out.println(grille);

        // Test de la méthode cellulesToutesEteintes
        System.out.println("Toutes les cellules sont-elles éteintes ? " + grille.cellulesToutesEteintes());

        // Test de la méthode activerLigneColonneOuDiagonaleAleatoire
        System.out.println("Activation d'une ligne, colonne ou diagonale aléatoire :");
        grille.activerLigneColonneOuDiagonaleAleatoire();
        System.out.println(grille);
        
        // Test de la méthode cellulesToutesEteintes après une activation aléatoire
        System.out.println("Toutes les cellules sont-elles éteintes ? " + grille.cellulesToutesEteintes());
    }

}
