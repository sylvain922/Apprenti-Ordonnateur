package vue;

import javafx.scene.paint.Color;
import modele.Position;

import java.io.File;

public interface Constantes {
    public final int CARRE = 32, CARRE_TEMPLE = CARRE - 2;
    public final int DIAM_CRISTAL = CARRE_TEMPLE - 12;
    public final int NB_L = 31, NB_H = 25;
    public final int L_CANVAS = NB_L * CARRE, H_CANVAS = NB_H * CARRE;
    public final int L_FENETRE = L_CANVAS + 400, H_FENETRE = H_CANVAS;

    public final int L_CRISTAL = 10, H_CRISTAL = 25;

    public final Color COULEUR_FOND = Color.web("#353535");

    public final Color COULEURS_TEMPLES[] = {Color.WHITE, Color.CYAN, Color.CHOCOLATE, Color.CORNFLOWERBLUE, Color.BROWN, Color.YELLOWGREEN, Color.VIOLET, Color.YELLOW, Color.DARKCYAN, Color.CRIMSON};

    public final String ICONE_APPRENTI = "icones" + File.separator + "aventurier.PNG";

    public final Position POS_APPRENTI = new Position(NB_L/2, NB_H/2);

    public final int MANUEL = 0, HEURISTIQUE = 1, TRI = 2;

    public final String TITRE_BOUTON_HEURIS = "Heuristique", TITRE_BOUTON_TRI = "Tri Selection", TITRE_BOUTON_RESET = "Reset";

    public String NOM_FICH = "scenario2.txt";

    public final int H_GRIDPANE = 200;
}
