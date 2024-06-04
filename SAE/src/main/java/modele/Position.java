package modele;

/**
 * La classe Position représente une position dans un espace 2D avec des coordonnées abscisse et ordonnée.
 * Elle gère également le nombre de déplacements effectués.
 */
public class Position {
    private int chAbscisse, chOrdonnee;
    private int chNbDeplacements = 0;

    /**
     * Constructeur de la classe Position.
     *
     * @param parAbscisse La coordonnée abscisse de la position.
     * @param parOrdonnee La coordonnée ordonnée de la position.
     */
    public Position(int parAbscisse, int parOrdonnee) {
        chAbscisse = parAbscisse;
        chOrdonnee = parOrdonnee;
    }

    /**
     * Constructeur par copie de la classe Position.
     *
     * @param parPosition La position à copier.
     */
    public Position(Position parPosition) {
        chAbscisse = parPosition.chAbscisse;
        chOrdonnee = parPosition.chOrdonnee;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la position.
     *
     * @return Une chaîne de caractères représentant la position.
     */
    public String toString() {
        return "[" + chAbscisse + ", " + chOrdonnee + "]";
    }

    /**
     * Retourne la coordonnée abscisse de la position.
     *
     * @return La coordonnée abscisse.
     */
    public int getChAbscisse() {
        return chAbscisse;
    }

    /**
     * Retourne la coordonnée ordonnée de la position.
     *
     * @return La coordonnée ordonnée.
     */
    public int getChOrdonnee() {
        return chOrdonnee;
    }

    /**
     * Retourne le nombre de déplacements effectués depuis la création de la position.
     *
     * @return Le nombre de déplacements.
     */
    public int getChNbDeplacements() {
        return chNbDeplacements;
    }

    /**
     * Définit le nombre de déplacements effectués.
     *
     * @param parNb Le nombre de déplacements.
     */
    public void setNbDeplacemenst(int parNb) {
        chNbDeplacements = parNb;
    }

    /**
     * Déplace la position d'une case vers la position cible spécifiée.
     *
     * @param parPos La position cible vers laquelle se déplacer.
     */
    public void deplacementUneCase(Position parPos) {
        chNbDeplacements++;
        if (chAbscisse < parPos.chAbscisse) {
            chAbscisse++;
            return;
        }
        if (chAbscisse > parPos.chAbscisse) {
            chAbscisse--;
            return;
        }
        if (chOrdonnee < parPos.chOrdonnee) {
            chOrdonnee++;
            return;
        }
        if (chOrdonnee > parPos.chOrdonnee) {
            chOrdonnee--;
            return;
        }
    }

    /**
     * Définit une nouvelle position.
     *
     * @param parPosition La nouvelle position.
     */
    public void setPosition(Position parPosition) {
        chAbscisse = parPosition.chAbscisse;
        chOrdonnee = parPosition.chOrdonnee;
    }

    /**
     * Calcule la distance entre cette position et une autre position spécifiée.
     *
     * @param parPosition La position cible.
     * @return La distance entre les deux positions.
     */
    public int distance(Position parPosition) {
        return Math.abs(chAbscisse - parPosition.chAbscisse) + Math.abs(chOrdonnee - parPosition.chOrdonnee);
    }

    /**
     * Vérifie si cette position est la même qu'une autre position spécifiée.
     *
     * @param parPosition La position à comparer.
     * @return true si les positions sont identiques, false sinon.
     */
    public boolean memePosition(Position parPosition) {
        return (parPosition.chAbscisse == chAbscisse && parPosition.chOrdonnee == chOrdonnee);
    }
}
