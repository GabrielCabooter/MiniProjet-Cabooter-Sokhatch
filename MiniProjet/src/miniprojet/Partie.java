/*
 * 
 */
package miniprojet;

import java.util.Scanner;

/**
 * Classe Partie : représente une partie du jeu de cellules lumineuses.
 * Elle gère l'interaction avec la grille et le déroulement du jeu.
 */
public class Partie {
    private GrilleDeCellules grille;

    /**
     * Constructeur de la classe Partie.
     * Initialise une nouvelle grille avec les dimensions spécifiées.
     * 
     * @param nbLignes   Le nombre de lignes de la grille.
     * @param nbColonnes Le nombre de colonnes de la grille.
     */
    public Partie(int nbLignes, int nbColonnes) {
        this.grille = new GrilleDeCellules(nbLignes, nbColonnes);
    }

    /**
     * Initialise la partie en éteignant toutes les cellules de la grille.
     */
    public void initialiserPartie() {
        grille.eteindreToutesLesCellules();
        System.out.println("La partie a été initialisée. Toutes les cellules sont éteintes.");
        afficherGrille();
    }

    /**
     * Affiche l'état actuel de la grille de jeu.
     */
    public void afficherGrille() {
        System.out.println(grille);
    }
    /**
   * Demande à l'utilisateur de saisir un entier valide.
   * 
   * @param scanner Le scanner utilisé pour lire l'entrée utilisateur.
   * @param message Le message à afficher pour guider l'utilisateur.
   * @return Un entier valide saisi par l'utilisateur.
   */
   private int demanderEntier(Scanner scanner, String message) {
        int valeur;
        while (true) {
        System.out.print(message);
        String input = scanner.nextLine();
        try {
            valeur = Integer.parseInt(input);
            break; // Sort de la boucle si la conversion est réussie
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
        }
    }
    return valeur;
}

    public void lancerPartie() {
    Scanner scanner = new Scanner(System.in);
    String input;

    System.out.println("Bienvenue dans le jeu des cellules lumineuses !");
    System.out.println("Objectif : Éteignez toutes les cellules de la grille.");
    afficherGrille();

    while (true) {
        System.out.println("""
                Choisissez une action :
                1. Activer une ligne
                2. Activer une colonne
                3. Activer la diagonale descendante
                4. Activer la diagonale montante
                5. Mélanger la grille
                6. Quitter""");
        System.out.print("Entrez le numéro de votre choix : ");
        input = scanner.nextLine();

        switch (input) {
            case "1" -> {
                int ligne = demanderEntier(scanner, "Entrez l'indice de la ligne à activer : ");
                grille.activerLigneDeCellules(ligne);
                afficherGrille();
            }
            case "2" -> {
                int colonne = demanderEntier(scanner, "Entrez l'indice de la colonne à activer : ");
                grille.activerColonneDeCellules(colonne);
                afficherGrille();
            }
            case "3" -> {
                System.out.println("Activation de la diagonale descendante...");
                grille.activerDiagonaleDescendante();
                afficherGrille();
            }
            case "4" -> {
                System.out.println("Activation de la diagonale montante...");
                grille.activerDiagonaleMontante();
                afficherGrille();
            }
            case "5" -> {
                int nbTours = demanderEntier(scanner, "Combien de tours pour mélanger la grille ? ");
                grille.melangerMatriceAleatoirement(nbTours);
                afficherGrille();
            }
            case "6" -> {
                System.out.println("Merci d'avoir joué !");
                return; // Quitte la méthode
            }
            default -> System.out.println("Option invalide. Veuillez réessayer.");
        }

        if (grille.cellulesToutesEteintes()) {
            System.out.println("Félicitations ! Vous avez éteint toutes les cellules !");
            break; // Quitte la boucle si le joueur gagne
        }
    }

    scanner.close();
  }
}