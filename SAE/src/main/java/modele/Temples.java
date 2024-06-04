package modele;

/**
 * La classe Temple représente un temple avec une position, une couleur et une couleur de cristal.
 * Elle permet de vérifier si le temple est aligné et de comparer les temples par couleur.
 */
public class Temple implements Comparable <Temple> {
    private Position chPosition;
    private int chCouleur, chCouleurCristal;

    /**
     * Constructeur de la classe Temple.
     *
     * @param parPosition      La position du temple.
     * @param parCouleur       La couleur du temple.
     * @param parCouleurCristal La couleur du cristal du temple.
     */
    public Temple(Position parPosition, int parCouleur, int parCouleurCristal) {
        chPosition = new Position(parPosition.getChAbscisse(), parPosition.getChOrdonnee());
        chCouleur = parCouleur;
        chCouleurCristal = parCouleurCristal;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du temple.
     *
     * @return Une chaîne de caractères représentant le temple.
     */
    public String toString() {
        return chPosition.toString() + ", " + chCouleur + ", " + chCouleurCristal;
    }

    /**
     * Retourne la position du temple.
     *
     * @return La position du temple.
     */
    public Position getPosition() {
        return chPosition;
    }

    /**
     * Retourne la couleur du temple.
     *
     * @return La couleur du temple.
     */
    public int getCouleur() {
        return chCouleur;
    }

    /**
     * Retourne la couleur du cristal du temple.
     *
     * @return La couleur du cristal du temple.
     */
    public int getCouleurCristal() {
        return chCouleurCristal;
    }

    /**
     * Définit la couleur du cristal du temple.
     *
     * @param parCoul La nouvelle couleur du cristal.
     */
    public void setCouleurCristal(int parCoul) {
        chCouleurCristal = parCoul;
    }

    /**
     * Vérifie si le temple est aligné (la couleur du cristal est différente de la couleur du temple).
     *
     * @return true si le temple est aligné, false sinon.
     */
    public boolean estAligne() {
        return chCouleurCristal != chCouleur;
    }

    /**
     * Compare ce temple à un autre temple par couleur.
     *
     * @param parTemple Le temple à comparer.
     * @return Une valeur négative, zéro ou positive si la couleur de ce temple est respectivement
     *         inférieure, égale ou supérieure à la couleur de l'autre temple.
     */
    public int compareTo(Temple parTemple) {
        return chCouleur - parTemple.chCouleur;
    }
}
