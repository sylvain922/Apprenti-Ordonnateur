package modele;

import javafx.scene.image.Image;

public interface ConstanteCanvas {
    int LARGEUR_CANVAS = 750;
    int HAUTEUR_CANVAS = 750;
    int CARRE = 25;
    int NB_CARRE_HAUTEUR = HAUTEUR_CANVAS/CARRE;
    int NB_CARRE_LARGEUR = LARGEUR_CANVAS/CARRE;
    String [] LIST_COULEURS = {"BLUE", "YELLOW", "ORANGE", "PINK", "RED", "GREEN"};
    String COULEUR_GRILLE = "BLACK";
    String COULEUR_APPRENTI = "BLACK";
    int LARGEUR_OVALE = 15;
    int HAUTEUR_OVALE = 15;
    int LARGEUR_ITEM = LARGEUR_CANVAS/CARRE;
    int HAUTEUR_ITEM = LARGEUR_CANVAS/CARRE;
    String INTITULE_MENU_SCENARIOS = "Scénarios";

    //Image[] LIST_IMAGES = {new Image("Z://projetCanvas-20240517T090624Z-001/projetCanvas/img/cristal_bleu.png"),new Image("Z://projetCanvas-20240517T090624Z-001/projetCanvas/img/cristal_jaune.png"),new Image("Z://projetCanvas-20240517T090624Z-001/projetCanvas/img/cristal_orange.png"),new Image("Z://projetCanvas-20240517T090624Z-001/projetCanvas/img/cristal_rose.png"),new Image("Z://projetCanvas-20240517T090624Z-001/projetCanvas/img/cristal_rouge.png"),new Image("Z://projetCanvas-20240517T090624Z-001/projetCanvas/img/cristal_vert.png")};
}