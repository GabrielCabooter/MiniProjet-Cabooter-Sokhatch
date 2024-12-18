
public class Score {
    private String joueur;
    private int nbCoups;
    private int tempsRestant;
    private String difficulte;
    private int score;

    public Score(String joueur, int nbCoups, int tempsRestant, String difficulte) {
        this.joueur = joueur;
        this.nbCoups = nbCoups;
        this.tempsRestant = tempsRestant;
        this.difficulte = difficulte;
        this.score = calculerScore();
    }

    private int calculerScore() {
        return (tempsRestant * 10) - (nbCoups * 5); // Exemple de calcul de score
    }

    public String getJoueur() {
        return joueur;
    }

    public int getNbCoups() {
        return nbCoups;
    }

    public int getTempsRestant() {
        return tempsRestant;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public int getScore() {
        return score;
    }
}