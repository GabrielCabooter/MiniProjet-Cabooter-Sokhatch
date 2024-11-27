/*
 * 
 */
package miniprojet;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Classe Partie : représente une partie du jeu des cellules lumineuses.
 * Elle gère l'interaction avec la grille, le choix du niveau, et le déroulement du jeu.
 */
public class Partie {
    private GrilleDeCellules grille;
    private int nbCoups; // Compteur de coups joués par le joueur
    private int nbTours; // Nombre de mélanges lors de l'initialisation
    private int niveauDifficulte;
    private int tempsLimite; // Temps limite pour terminer la partie
    private Timer timer; // Chronomètre
    private boolean partieEnCours; // Indique si la partie est en cours

    /**
     * Constructeur par défaut de la classe Partie.
     * Initialise une partie avec un niveau choisi par le joueur.
     */
    public Partie() {
        this.nbCoups = 0; // Initialisation du compteur de coups à 0
        afficherMessageBienvenue();
        choisirNiveauDifficulte();
        initialiserPartie();
    }

    /**
     * Affiche le message de bienvenue et explique le but du jeu.
     */
    private void afficherMessageBienvenue() {
        System.out.println("Bienvenue dans le jeu des cellules lumineuses !");
        System.out.println("Objectif : Éteignez toutes les cellules de la grille.");
        System.out.println("Chaque action (activer une ligne, une colonne ou une diagonale) inverse l'état des cellules.");
        System.out.println("Bonne chance !");
    }

    /**
     * Permet au joueur de choisir le niveau de difficulté.
     */
    private void choisirNiveauDifficulte() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoisissez un niveau de difficulté :");
        System.out.println("1. Facile (Grille 5x5, 10 mélanges, 90 secondes)");
        System.out.println("2. Intermédiaire (Grille 7x7, 20 mélanges, 120 secondes)");
        System.out.println("3. Difficile (Grille 10x10, 40 mélanges, 150 secondes)");

        while (true) {
            System.out.print("Entrez le numéro de votre choix : ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    niveauDifficulte = 1;
                    grille = new GrilleDeCellules(5, 5);
                    nbTours = 10;
                    tempsLimite = 15; // Temps limite en secondes
                    return;
                }
                case "2" -> {
                    niveauDifficulte = 2;
                    grille = new GrilleDeCellules(7, 7);
                    nbTours = 20;
                    tempsLimite = 120;
                    return;
                }
                case "3" -> {
                    niveauDifficulte = 3;
                    grille = new GrilleDeCellules(10, 10);
                    nbTours = 40;
                    tempsLimite = 150;
                    return;
                }
                default -> System.out.println("Option invalide. Veuillez choisir 1, 2 ou 3.");
            }
        }
    }

    /**
     * Initialise la partie en mélangeant la grille.
     */
    public void initialiserPartie() {
        grille.eteindreToutesLesCellules();
        grille.melangerMatriceAleatoirement(nbTours);

        System.out.println("\nLa partie commence avec la grille suivante :");
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
     * Lance la partie et gère le chronomètre.
     */
    public void lancerPartie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Temps limite : " + tempsLimite + " secondes. Bonne chance !");
        partieEnCours = true;
        String input;

        // Initialisation du chronomètre
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int tempsRestant = tempsLimite;

            @Override
            public void run() {
                if (!partieEnCours) {
                    timer.cancel();
                    return;
                }
                if (tempsRestant == 0) {
                    System.out.println("\nTemps écoulé ! Vous avez perdu.");
                    terminerPartie(scanner);
                    timer.cancel();
                } else if (tempsRestant == 10) {
                    System.out.println("\nAttention ! Il reste 10 secondes !");
                }
                tempsRestant--;
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);

        while (partieEnCours) {
            System.out.println("""
                    Choisissez une action :
                    1. Activer une ligne
                    2. Activer une colonne
                    3. Activer la diagonale descendante
                    4. Activer la diagonale montante
                    5. Relancer la partie
                    6. Quitter le jeu""");
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
                    System.out.println("Relance de la partie...");
                    relancerPartie();
                    return;
                }
                case "6" -> {
                    System.out.println("Merci d'avoir joué !");
                    timer.cancel();
                    System.exit(0);
                }
                default -> System.out.println("Option invalide. Veuillez réessayer.");
            }

            nbCoups++;
            afficherGrille();
            System.out.println("Nombre de coups joués : " + nbCoups);

            if (grille.cellulesToutesEteintes()) {
                System.out.println("Félicitations ! Vous avez éteint toutes les cellules !");
                System.out.println("Nombre total de coups joués : " + nbCoups);
                terminerPartie(scanner);
                timer.cancel();
            }
        }
    }

    /**
     * Relance une nouvelle partie.
     */
    private void relancerPartie() {
        timer.cancel();
        new Partie().lancerPartie();
    }

    /**
     * Termine la partie et propose de rejouer ou de quitter.
     * 
     * @param scanner Scanner pour lire l'entrée utilisateur.
     */
    private void terminerPartie(Scanner scanner) {
        partieEnCours = false;
        timer.cancel();

        System.out.println("\nVoulez-vous rejouer ?");
        System.out.println("1. Oui");
        System.out.println("2. Non");

        while (true) {
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> {
                    relancerPartie();
                    return;
                }
                case "2" -> {
                    System.out.println("Merci d'avoir joué !");
                    System.exit(0);
                }
                default -> System.out.println("Option invalide. Veuillez choisir 1 ou 2.");
            }
        }
    }
}