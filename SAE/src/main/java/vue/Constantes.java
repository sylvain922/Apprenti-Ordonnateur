package vue;

import javafx.scene.paint.Color;
import modele.Position;
import java.io.File;

public interface Constantes {
    public final int CARRE = 32;
    public final int LARGEUR = 31, HAUTEUR = 25;  //16, 13
    public final int LARGEUR_CANVAS = LARGEUR * CARRE, HAUTEUR_CANVAS = HAUTEUR * CARRE;
    public final int LARGEUR_FENETRE = LARGEUR_CANVAS + 400, HAUTEUR_FENETRE = HAUTEUR_CANVAS;

    public final Color COULEUR_FOND = Color.web("#353535");    //Couleur 0x202020 en héxadécimal

    public final String [] LIST_COULEURS = {"bleu","jaune","orange","rose","rouge","vert","violet","marron","gris"};

    public final String IMAGE_APPRENTI = "img" + File.separator + "aventurier.PNG";

    public final Position POSITION_APPRENTI = new Position(LARGEUR/2, HAUTEUR/2);

    public final int MANUEL = 0, HEURISTIQUE = 1, SELECTION = 2;

    public final String TITRE_BOUTON_HEURISTIQUE = "Heuristique", TITRE_BOUTON_SELECTION = "Tri Selection", TITRE_BOUTON_RESET = "Reset";
}

