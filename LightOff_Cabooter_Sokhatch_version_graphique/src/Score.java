/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabri
 */
public class Score {
    private String joueur;
    private int nbCoups;
    private int tempsRestant;
    private String difficulte;

    public Score(String joueur, int nbCoups, int tempsRestant, String difficulte) {
        this.joueur = joueur;
        this.nbCoups = nbCoups;
        this.tempsRestant = tempsRestant;
        this.difficulte = difficulte;
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
        return tempsRestant - nbCoups * 10; // Exemple de calcul, ajuste selon ta logique
    }
}
