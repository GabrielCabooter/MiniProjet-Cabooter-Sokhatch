
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
    // constructeur (appelé depuis FenetrePrincipale) 

    public CelluleGraphique(CelluleLumineuse celluleLumineuseAssociee, int l, int h) {
        this.largeur = l;
        this.hauteur = h;
        this.celluleLumineuseAssociee = celluleLumineuseAssociee;
    }
// Methode gérant le dessin de la cellule  

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setText(celluleLumineuseAssociee.toString());
    }
}
