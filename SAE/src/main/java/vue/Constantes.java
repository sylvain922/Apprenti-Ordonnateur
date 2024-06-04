package vue;

import javafx.scene.paint.Color;
import modele.Position;
import java.io.File;

/**
 * Interface Constantes définissant les constantes utilisées dans l'application.
 * Ces constantes incluent les dimensions des éléments du jeu, les couleurs,
 * les chemins des fichiers et les identifiants pour les algorithmes.
 */
public interface Constantes {

    /** Taille d'une case sur la grille et d'un temple en pixels. */
    public final int CARRE = 32, CARRE_TEMPLE = CARRE - 2;

    /** Diamètre d'un cristal en pixels. */
    public final int DIAMETRE_CRISTAL = CARRE_TEMPLE - 12;

    /** Largeur et hauteur de la grille en nombre de cases. */
    public final int LARGEUR = 31, HAUTEUR = 25;  //16, 13

    /** Largeur et hauteur du canvas en pixels. */
    public final int LARGEUR_CANVAS = LARGEUR * CARRE, HAUTEUR_CANVAS = HAUTEUR * CARRE;

    /** Largeur et hauteur de la fenêtre de l'application en pixels. */
    public final int LARGEUR_FENETRE = LARGEUR_CANVAS + 400, HAUTEUR_FENETRE = HAUTEUR_CANVAS;

    /** Largeur et hauteur d'un cristal en pixels. */
    public final int LARGEUR_CRISTAL = 10, HAUTEUR_CRISTAL = 25;

    /** Couleur de fond de la grille. */
    public final Color COULEUR_FOND = Color.web("#353535");    //Couleur 0x202020 en héxadécimal

    /** Tableau des couleurs des temples. */
    public final String[] COULEURS_TEMPLES = {"bleu", "gris", "jaune", "marron", "orange", "rose", "rouge", "vert", "violet"};

    /** Chemin de l'image de l'apprenti. */
    public final String IMAGE_APPRENTI = "img" + File.separator + "aventurier.PNG";

    /** Position initiale de l'apprenti. */
    public final Position POSITION_APPRENTI = new Position(LARGEUR/2, HAUTEUR/2);

    /** Identifiant pour l'algorithme de déplacement manuel, heuristique et de tri par sélection. */
    public final int MANUEL = 0, HEURISTIQUE = 1, SELECTION = 2;

    /** Titre du bouton pour l'algorithme heuristique, de tri par sélection et de Reset. */
    public final String TITRE_BOUTON_HEURISTIQUE = "Heuristique", TITRE_BOUTON_SELECTION = "Tri Selection", TITRE_BOUTON_RESET = "Reset";
}
