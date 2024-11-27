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
    private int nbTours; // Nombre de mélanges lors de l'initialisation
    private int niveauDifficulte;

    /**
     * Constructeur par défaut de la classe Partie.
     * Initialise une partie avec un niveau choisi par le joueur.
     */
    public Partie() {
        this.nbCoups = 0;
        choisirNiveauDifficulte();
        initialiserPartie();
    }

    /**
     * Permet au joueur de choisir le niveau de difficulté.
     */
    private void choisirNiveauDifficulte() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez un niveau de difficulté :");
        System.out.println("1. Facile (Grille 5x5, 10 mélanges)");
        System.out.println("2. Intermédiaire (Grille 7x7, 20 mélanges)");
        System.out.println("3. Difficile (Grille 10x10, 40 mélanges)");

        while (true) {
            System.out.print("Entrez le numéro de votre choix : ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    niveauDifficulte = 1;
                    grille = new GrilleDeCellules(5, 5);
                    nbTours = 10;
                    return;
                }
                case "2" -> {
                    niveauDifficulte = 2;
                    grille = new GrilleDeCellules(7, 7);
                    nbTours = 20;
                    return;
                }
                case "3" -> {
                    niveauDifficulte = 3;
                    grille = new GrilleDeCellules(10, 10);
                    nbTours = 40;
                    return;
                }
                default -> System.out.println("Option invalide. Veuillez choisir 1, 2 ou 3.");
            }
        }
    }

    /**
     * Initialise la partie en éteignant toutes les cellules de la grille,
     * puis en mélangeant la grille.
     */
    public void initialiserPartie() {
        grille.eteindreToutesLesCellules();

        // Mélange la grille avec le nombre de tours spécifique
        grille.melangerMatriceAleatoirement(nbTours);

        // Afficher l'état initial de la grille mélangée
        System.out.println("La partie commence avec la grille suivante :");
        afficherGrille();
    }

    /**
     * Méthode pour afficher l'état actuel de la grille.
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

            // Augmente le compteur de coups
            nbCoups++;

            // Afficher l'état mis à jour de la grille
            afficherGrille();

            // Vérifie si toutes les cellules sont éteintes, fin du jeu
            if (grille.cellulesToutesEteintes()) {
                System.out.println("Félicitations ! Vous avez éteint toutes les cellules !");
                System.out.println("Nombre total de coups joués : " + nbCoups);
                break; // Quitte la boucle si le joueur gagne
            }
        }
    }
}