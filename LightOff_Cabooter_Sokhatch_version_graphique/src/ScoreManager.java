
import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String FILE_NAME = "scores.txt";  // Fichier pour stocker les scores

    // Sauvegarde des scores dans un fichier texte
    public static void sauvegarderScores(List<Score> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Score score : scores) {
                writer.write(score.getJoueur() + "," +
                             score.getNbCoups() + "," +
                             score.getTempsRestant() + "," +
                             score.getDifficulte() + "," +
                             score.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Chargement des scores depuis un fichier texte
    public static List<Score> chargerScores() {
        List<Score> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String joueur = data[0];
                int nbCoups = Integer.parseInt(data[1]);
                int tempsRestant = Integer.parseInt(data[2]);
                String difficulte = data[3];
                int score = Integer.parseInt(data[4]);  // Ajout du score ici
                scores.add(new Score(joueur, nbCoups, tempsRestant, difficulte));  // Passage du score dans le constructeur
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }
}
