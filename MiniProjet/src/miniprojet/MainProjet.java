/*
 * Projet LightsOff de Gabriel Cabooter et Arthur Sokhatch 
 * Du 20/11/2024 au ???
 */
package miniprojet;

/**
 *
 * 
 */
public class MainProjet {

    /**
     * Point d'entrée pour tester les fonctionnalités de la classe CelluleLumineuse.
     * Crée des instances de CelluleLumineuse, manipule leur état et affiche
     * les résultats pour vérifier les comportements attendus.
     * 
     * @param args arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
    CelluleLumineuse cellule = new CelluleLumineuse();
        System.out.println(cellule); // Affiche "O"
        
        cellule.activerCellule();
        System.out.println(cellule); // Affiche "X"

        cellule.eteindreCellule();
        System.out.println(cellule); // Affiche "O"
        
        System.out.println("Est éteinte: " + cellule.estEteint()); // Affiche true
        System.out.println("État: " + cellule.getEtat()); // Affiche false    
    }
    
}
