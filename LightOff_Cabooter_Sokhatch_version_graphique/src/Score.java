
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

  int calculerScore() {
    // Déterminer le temps maximal et le multiplicateur de difficulté en fonction de la difficulté
    int tempsMaximal = 0;
    int multiplicateurDifficulte = 0;
    
    switch (difficulte) {
        case "Facile":
            tempsMaximal = 120;  
            multiplicateurDifficulte = 5000;  
            break;
        case "Moyenne":
            tempsMaximal = 60;  
            multiplicateurDifficulte = 2500;  
            break;
        case "Difficile":
            tempsMaximal = 30;   
            multiplicateurDifficulte = 0;  
            break;
        default:
            throw new IllegalArgumentException("Difficulté inconnue: " + difficulte);
    }

    // Calcul du temps joué
    int tempsJoue = tempsMaximal - tempsRestant;

    // Calcul du score avec temps joué et multiplicateur de difficulté
    int score = (tempsJoue * 100) + (nbCoups * 50) + multiplicateurDifficulte;

    // S'assurer que le score n'est jamais négatif
    return Math.max(score, 0);
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