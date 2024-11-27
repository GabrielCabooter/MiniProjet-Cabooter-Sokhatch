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
        // Créez une nouvelle partie avec une grille de 5 lignes et 5 colonnes
        Partie partie = new Partie(5, 5);
        partie.lancerPartie();  // Lance la partie
    }
}
