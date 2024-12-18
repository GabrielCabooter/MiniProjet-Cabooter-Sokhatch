
/**
 * Nom du projet : CPASVERSAILLESICI
 * Auteurs : Gabriel Cabooter et Arthur Sokhatch
 * Date du projet : Du 20 novembre 2024 au 18 décembre 2024
 * 
 * Classe représentant un score dans le jeu. Elle contient les informations concernant le joueur,
 * le nombre de coups, le temps restant, la difficulté et le calcul du score basé sur ces paramètres.
 */
public class Score {
    private final String joueur;  // Nom du joueur
    private final int nbCoups;  // Nombre de coups effectués
    private final int tempsRestant;  // Temps restant à la fin de la partie
    private final String difficulte;  // Difficulté du jeu (Facile, Moyenne, Difficile)
    private final int score;  // Score calculé du joueur

    /**
     * Constructeur de la classe Score.
     * Initialise les informations du joueur, du nombre de coups, du temps restant et de la difficulté,
     * puis calcule le score.
     *
     * @param joueur Le nom du joueur.
     * @param nbCoups Le nombre de coups effectués pendant la partie.
     * @param tempsRestant Le temps restant à la fin de la partie.
     * @param difficulte La difficulté choisie (Facile, Moyenne, Difficile).
     */
    public Score(String joueur, int nbCoups, int tempsRestant, String difficulte) {
        this.joueur = joueur;
        this.nbCoups = nbCoups;
        this.tempsRestant = tempsRestant;
        this.difficulte = difficulte;
        this.score = calculerScore();  // Calcul du score en fonction des paramètres
    }

    /**
     * Calcule le score du joueur en fonction du nombre de coups, du temps restant et de la difficulté.
     * Le score est calculé en prenant en compte le temps joué, le nombre de coups effectués,
     * et un multiplicateur basé sur la difficulté.
     *
     * @return Le score calculé du joueur.
     */
    int calculerScore() {
        // Déterminer le temps maximal et le multiplicateur de difficulté en fonction de la difficulté
        int tempsMaximal = 0;
        int multiplicateurDifficulte = 0;
        
        // Définir les valeurs selon la difficulté
        switch (difficulte) {
            case "Facile":
                tempsMaximal = 120;  // 2 minutes pour la difficulté Facile
                multiplicateurDifficulte = 5000;  // Multiplicateur élevé pour "Facile"
                break;
            case "Moyenne":
                tempsMaximal = 60;  // 1 minute pour la difficulté Moyenne
                multiplicateurDifficulte = 2500;  // Multiplicateur modéré pour "Moyenne"
                break;
            case "Difficile":
                tempsMaximal = 30;  // 30 secondes pour la difficulté Difficile
                multiplicateurDifficulte = 0;  // Pas de multiplicateur pour "Difficile"
                break;
            default:
                throw new IllegalArgumentException("Difficulté inconnue: " + difficulte);  // Si la difficulté est inconnue
        }

        // Calcul du temps joué : différence entre le temps maximal et le temps restant
        int tempsJoue = tempsMaximal - tempsRestant;

        // Calcul du score en fonction du temps joué, des coups et du multiplicateur de difficulté
        int score = (tempsJoue * 100) + (nbCoups * 50) + multiplicateurDifficulte;

        // S'assurer que le score n'est jamais négatif
        return Math.max(score, 0);
    }

    /**
     * Retourne le nom du joueur.
     *
     * @return Le nom du joueur.
     */
    public String getJoueur() {
        return joueur;
    }

    /**
     * Retourne le nombre de coups effectués pendant la partie.
     *
     * @return Le nombre de coups.
     */
    public int getNbCoups() {
        return nbCoups;
    }

    /**
     * Retourne le temps restant à la fin de la partie.
     *
     * @return Le temps restant en secondes.
     */
    public int getTempsRestant() {
        return tempsRestant;
    }

    /**
     * Retourne la difficulté choisie pour la partie.
     *
     * @return La difficulté (Facile, Moyenne, Difficile).
     */
    public String getDifficulte() {
        return difficulte;
    }

    /**
     * Retourne le score calculé pour ce joueur.
     *
     * @return Le score du joueur.
     */
    public int getScore() {
        return score;
    }
}