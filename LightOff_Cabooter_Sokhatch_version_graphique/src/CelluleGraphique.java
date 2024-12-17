
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import miniprojet.CelluleLumineuse;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * 
 */
public class CelluleGraphique extends JButton {

    int largeur; // largeur en pixel de la cellule 
    int hauteur; // hauteur en pixel de la cellule 
    CelluleLumineuse celluleLumineuseAssociee;

    // Couleurs pour les états allumée et éteinte
    private final Color COULEUR_ALLUMEE = Color.YELLOW;  // Couleur pour une cellule allumée
    private final Color COULEUR_ETEINTE = Color.GRAY;    // Couleur pour une cellule éteinte

    /**
     * Constructeur de CelluleGraphique
     *
     * @param celluleLumineuseAssociee la cellule logique associée
     * @param l largeur en pixels
     * @param h hauteur en pixels
     */
    public CelluleGraphique(CelluleLumineuse celluleLumineuseAssociee, int l, int h) {
        this.largeur = l;
        this.hauteur = h;
        this.celluleLumineuseAssociee = celluleLumineuseAssociee;

        // Configuration de base du bouton
        setOpaque(false); // Permet au bouton d'afficher sa couleur de fond
        setBorderPainted(false); // Supprime la bordure pour un meilleur rendu visuel
    }

    /**
     * Méthode pour dessiner la cellule graphique avec la couleur appropriée
     *
     * @param g l'objet Graphics utilisé pour dessiner
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Applique la couleur de fond en fonction de l'état de la cellule
        if (celluleLumineuseAssociee.estEteint()) {
            setBackground(COULEUR_ETEINTE); // Gris pour les cellules éteintes
        } else {           
            setBackground(COULEUR_ALLUMEE); // Jaune pour les cellules allumées
        }

        super.paintComponent(g); // Appel de la méthode parente pour appliquer le rendu
    }
}