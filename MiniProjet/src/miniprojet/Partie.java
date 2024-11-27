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
    private int nbCoups;

    /**
     * Constructeur de la classe Partie.
     * Initialise une nouvelle grille avec les dimensions spécifiées et mélange la grille.
     * 
     * @param nbLignes   Le nombre de lignes de la grille.
     * @param nbColonnes Le nombre de colonnes de la grille.
     */
    public Partie(int nbLignes, int nbColonnes) {
        this.grille = new GrilleDeCellules(nbLignes, nbColonnes);
        this.nbCoups = 0;
        initialiserPartie();
    }

    /**
     * Initialise la partie en éteignant toutes les cellules de la grille,
     * puis en mélangeant la grille et en s'assurant qu'elle est résolvable.
     */
    public void initialiserPartie() {
        grille.eteindreToutesLesCellules();
        
        // Mélange la grille de manière aléatoire
        grille.melangerMatriceAleatoirement(10);  // Mélanger avec 10 tours ou un nombre adapté

        // S'assurer que la grille est résolvable
        if (!estGrilleResolvable()) {
            System.out.println("La grille générée n'est pas résolvable, une nouvelle grille est générée.");
            initialiserPartie();  // Réinitialiser si la grille n'est pas résolvable
        }

        // Afficher l'état initial de la grille mélangée
        System.out.println("La partie commence avec la grille suivante :");
        afficherGrille();
    }

    /**
     * Vérifie si la grille est résolvable.
     * 
     * @return true si la grille est résolvable, sinon false.
     */
    private boolean estGrilleResolvable() {
        // Une grille est considérée comme résolvable si le nombre de cellules allumées est pair
        int allumees = 0;
        for (int i = 0; i < grille.getNbLignes(); i++) {
            for (int j = 0; j < grille.getNbColonnes(); j++) {
                if (grille.getCellule(i, j).getEtat()) {
                    allumees++;
                }
            }
        }
        return allumees % 2 == 0; // Si le nombre de cellules allumées est pair, la grille est résolvable
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

    /**
     * Lance la partie et gère le déroulement du jeu.
     */
    public void lancerPartie() {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Bienvenue dans le jeu des cellules lumineuses !");
        System.out.println("Objectif : Éteignez toutes les cellules de la grille.");

        while (true) {
            System.out.println("""
                    Choisissez une action :
                    1. Activer une ligne
                    2. Activer une colonne
                    3. Activer la diagonale descendante
                    4. Activer la diagonale montante
                    5. Quitter""");
            System.out.print("Entrez le numéro de votre choix : ");
            input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    int ligne = demanderEntier(scanner, "Entrez l'indice de la ligne à activer : ");
                    grille.activerLigneDeCellules(ligne);
                }
                case "2" -> {
                    int colonne = demanderEntier(scanner, "Entrez l'indice de la colonne à activer : ");
                    grille.activerColonneDeCellules(colonne);
                }
                case "3" -> {
                    System.out.println("Activation de la diagonale descendante...");
                    grille.activerDiagonaleDescendante();
                }
                case "4" -> {
                    System.out.println("Activation de la diagonale montante...");
                    grille.activerDiagonaleMontante();
                }
                case "5" -> {
                    System.out.println("Merci d'avoir joué !");
                    scanner.close();
                    return; // Quitte la méthode
                }
                default -> System.out.println("Option invalide. Veuillez réessayer.");
            }

            // Afficher l'état mis à jour de la grille
            afficherGrille();

            // Vérifie si toutes les cellules sont éteintes, fin du jeu
            if (grille.cellulesToutesEteintes()) {
                System.out.println("Félicitations ! Vous avez éteint toutes les cellules !");
                break; // Quitte la boucle si le joueur gagne
            }
        }
    }
}