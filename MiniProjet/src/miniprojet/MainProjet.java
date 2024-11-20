/*
 * Projet LightsOff de Gabriel Cabooter et Arthur Sokhatch 
 * Du 20/11/2024 au ???
 */
package miniprojet;

/**
 * Classe principale pour tester le jeu des cellules lumineuses.
 */
public class MainProjet {
    public static void main(String[] args) {
        // Création d'une partie avec une grille de 5x5
        Partie partie = new Partie(5, 5);

        // Initialisation de la partie
        partie.initialiserPartie();

        // Lancement de la partie
        partie.lancerPartie();
    }
}