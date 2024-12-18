/**
 * Nom du projet : CPASVERSAILLESICI
 * Auteurs : Gabriel Cabooter et Arthur Sokhatch
 * Date du projet : Du 20 novembre 2024 au 18 décembre 2024
 */

import java.io.*;
import java.util.*;

/**
 * Classe qui gère la sauvegarde, le chargement et la réinitialisation des scores dans un fichier texte.
 * Les scores sont également maintenus en mémoire sous forme de liste.
 */
public class ScoreManager {
    private static final String FILE_NAME = "scores.txt";  // Nom du fichier pour stocker les scores
    private static List<Score> scores = new ArrayList<>(); // Liste des scores en mémoire

    /**
     * Sauvegarde la liste des scores dans un fichier texte.
     * Chaque score est écrit sur une nouvelle ligne dans le fichier.
     *
     * @param scores La liste des scores à sauvegarder.
     */
    public static void sauvegarderScores(List<Score> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Score score : scores) {
                // Écrit chaque attribut du score séparé par une virgule
                writer.write(score.getJoueur() + "," +
                             score.getNbCoups() + "," +
                             score.getTempsRestant() + "," +
                             score.getDifficulte() + "," +
                             score.getScore());
                writer.newLine();  // Nouvelle ligne pour chaque score
            }
        } catch (IOException e) {
            // Affiche l'erreur en cas de problème de lecture/écriture
            
        }
    }

    /**
     * Charge les scores depuis un fichier texte.
     * Chaque ligne du fichier est lue et transformée en un objet Score.
     * 
     * @return La liste des scores chargés depuis le fichier.
     */
    public static List<Score> chargerScores() {
        List<Score> scores = new ArrayList<>();  // Liste pour stocker les scores chargés
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Divise chaque ligne en données séparées par des virgules
                String[] data = line.split(",");
                String joueur = data[0];
                int nbCoups = Integer.parseInt(data[1]);
                int tempsRestant = Integer.parseInt(data[2]);
                String difficulte = data[3];
                int score = Integer.parseInt(data[4]);  // Récupère le score

                // Crée un objet Score à partir des données lues et l'ajoute à la liste
                scores.add(new Score(joueur, nbCoups, tempsRestant, difficulte));  
            }
        } catch (IOException e) {
            // Affiche l'erreur en cas de problème de lecture
            
        }
        return scores;  // Retourne la liste des scores chargés
    }

    /**
     * Réinitialise les scores en mémoire et vide le fichier de scores.
     * La liste des scores en mémoire est effacée et un fichier vide est écrit.
     */
    public static void reinitialiserScores() {
        // Vider la liste des scores en mémoire
        scores.clear();

        // Optionnel : Vider le fichier de scores en réécrivant un fichier vide
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("");  // Écrire une chaîne vide pour réinitialiser le fichier
        } catch (IOException e) {
            // Affiche l'erreur en cas de problème d'écriture
            
        }
    }
}
