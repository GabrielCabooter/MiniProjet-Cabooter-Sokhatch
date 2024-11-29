/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet;

/**
 * La classe CelluleLumineuse représente une cellule lumineuse pouvant être
 * dans l'état "allumée" ou "éteinte". Elle fournit des méthodes pour manipuler
 * et afficher son état.
 */
public class CelluleLumineuse {
    /**
     * Attribut qui indique l'état de la cellule.
     * <code>true</code> signifie que la cellule est allumée,
     * <code>false</code> signifie qu'elle est éteinte.
     */
    private boolean etat;

    /**
     * Constructeur par défaut qui initialise l'état de la cellule à "éteinte" (false).
     */
    public CelluleLumineuse() {
        this.etat = false;
    }

    /**
     * Active la cellule lumineuse en inversant son état actuel.
     * Si la cellule est allumée, elle devient éteinte, et vice-versa.
     */
    public void allumerCellule() {
        this.etat = !this.etat;
    }

    /**
     * Éteint la cellule lumineuse en mettant son état à "éteinte" (false).
     * Si la cellule est déjà éteinte, cette méthode n'a aucun effet.
     */
    public void eteindreCellule() {
        this.etat = false;
    }

    /**
     * Vérifie si la cellule est éteinte.
     * 
     * @return <code>true</code> si la cellule est éteinte, sinon <code>false</code>.
     */
    public boolean estEteint() {
        return !this.etat;
    }

    /**
     * Obtient l'état actuel de la cellule.
     * 
     * @return <code>true</code> si la cellule est allumée, <code>false</code> si elle est éteinte.
     */
    public boolean getEtat() {
        return this.etat;
    }

    /**
     * Retourne une représentation lisible de l'état de la cellule.
     * 
     * @return "X" si la cellule est allumée, "O" si elle est éteinte.
     */
    @Override
    public String toString() {
        return this.etat ? "X" : "O";
    }
}