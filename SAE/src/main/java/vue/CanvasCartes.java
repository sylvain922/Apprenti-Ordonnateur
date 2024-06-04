package vue;

import javafx.application.Platform;
import modele.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * La classe CanvasCartes représente la carte du jeu affichée dans l'interface utilisateur.
 * Elle étend la classe Canvas de JavaFX et gère l'affichage des éléments graphiques du jeu.
 */
public class CanvasCartes extends Canvas implements Constantes{
    private VBoxInformations chVBoxInfo;
    private static GraphicsContext hGC;
    private Image chImageApprenti;
    private LectureScenario chScenario;
    private Apprenti chApprenti ;
    private TreeMap<Integer, ArrayList<Image>> templeImagesMap = new TreeMap<>();
    private TreeMap<Integer, ArrayList<Image>> cristalImageMap = new TreeMap<>();

    /**
     * Constructeur de la classe CanvasCartes.
     */
    public CanvasCartes() {
        super(LARGEUR_CANVAS, HAUTEUR_CANVAS);
        chVBoxInfo = HBoxRoot.getVBoxInfo();
        hGC = getGraphicsContext2D();
        affGrille();

        resetPlateau(HBoxRoot.getScenario());

        setOnMouseClicked(event -> {
            Position positionCliquee = new Position((int) event.getX() / CARRE, (int) event.getY() / CARRE);
            if (!chScenario.estAligne()) {
                Position pos = new Position(chApprenti.getPosition());
                ArrayList<Position> liste = new ArrayList<>();
                if (chScenario.getTemple(positionCliquee) == null) {
                    chVBoxInfo.setInfo("Cliquez un temple SVP !");
                    return;
                }
                liste.add(positionCliquee);
                deplApprenti(liste, MANUEL);
            }
        });
    }

    /**
     * Exécute l'action basée sur l'heuristique pour déplacer l'apprenti.
     */
    public void actionHeuristique() {
        if (chScenario.estAligne())
            return;
        deplApprenti(HBoxRoot.getScenario().getListeHeuristique(), HEURISTIQUE);
    }

    /**
     * Exécute l'action basée sur le tri par sélection pour déplacer l'apprenti.
     */
    public void actionSelection() {
        if (chScenario.estAligne())
            return;
        deplApprenti(HBoxRoot.getScenario().getChListeTriSelection(), SELECTION);
    }

    /**
     * Affiche la grille sur le canvas.
     */
    public void affGrille() {
        hGC.setStroke(Color.LIGHTGRAY);
        hGC.setLineWidth(1);

        for (int y = 0; y < HAUTEUR_CANVAS; y += CARRE)
            hGC.strokeLine(0, y, LARGEUR_CANVAS, y);
        for (int x = 0; x <= LARGEUR_CANVAS; x += CARRE)
            hGC.strokeLine(x, 0, x, HAUTEUR_CANVAS);
    }

    /**
     * Réinitialise le plateau de jeu avec un nouveau scénario.
     * Efface les temples du plateau, charge les images des temples et des cristaux,
     * replace l'apprenti et les temples, et affiche les informations sur le côté droit.
     *
     * @param parScenario Le nouveau scénario à utiliser.
     */
    public void resetPlateau(LectureScenario parScenario) {
        if (chScenario != null) {
            for (Temple temple : chScenario.getTemples())
                effacerCase(temple.getPosition());
        }

        try {
            chImageApprenti = new Image(new FileInputStream(IMAGE_APPRENTI));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        chScenario = HBoxRoot.getScenario();
        chApprenti = parScenario.getApprenti();
        placerApprenti();
        loadCristalImages();
        loadTempleImages();
        chApprenti.getPosition().setNbDeplacemenst(0);

        chVBoxInfo.setNbDeplacementsHeuristique(0);
        chVBoxInfo.setNbDeplacementsJouer(0);
        chVBoxInfo.setNbDeplacementsSelection(0);
        chVBoxInfo.setInfo("");

        placerTemples(chScenario.getTemples(), templeImagesMap);
        placerCristaux();
    }

    /**
     * Place les cristaux sur les temples en fonction de leur couleur.
     */
    public void placerCristaux() {
        for (Temple t : chScenario.getTemples()) {
            ArrayList<Image> images = cristalImageMap.get(t.getCouleurCristal()-1);
            if (images != null) {
                Image image = images.get(t.getCouleurCristal());
                hGC.drawImage(image, t.getPosition().getChAbscisse() * CARRE+2, t.getPosition().getChOrdonnee() * CARRE+2, CARRE-4, CARRE-4);
            }
        }
    }

    /**
     * Charge les images des temples à partir des ressources et les stocke dans la carte des temples.
     */
    public void loadTempleImages() {
        for (int couleur = 0; couleur < COULEURS_TEMPLES.length; couleur++) {
            ArrayList<Image> images = new ArrayList<>();
            for (int cristal = 0; cristal <= COULEURS_TEMPLES.length; cristal++) {
                String imagePath = "/temples/temple_" + COULEURS_TEMPLES[couleur] + ".png";
                images.add(new Image(getClass().getResourceAsStream(imagePath)));
            }
            templeImagesMap.put(couleur, images);
        }
    }

    /**
     * Charge les images des cristaux à partir des ressources et les stocke dans la carte des cristaux.
     */
    public void loadCristalImages() {
        for (int couleur = 0; couleur < COULEURS_TEMPLES.length; couleur++) {
            ArrayList<Image> images = new ArrayList<>();
            for (int cristal = 0; cristal <= COULEURS_TEMPLES.length; cristal++) {
                String imagePath = "/cristaux/cristal_" + COULEURS_TEMPLES[couleur] + ".png";
                images.add(new Image(getClass().getResourceAsStream(imagePath)));
            }
            cristalImageMap.put(couleur, images);
        }
    }

    /**
     * Place les temples sur la carte en fonction de leur couleur et de leur cristal associé.
     *
     * @param Temples           La liste des temples à placer.
     * @param templeImagesMap   La carte associant les images de temple à leur couleur.
     */
    public static void placerTemples(ArrayList<Temple> Temples, TreeMap<Integer, ArrayList<Image>> templeImagesMap) {
        for (Temple t : Temples) {
            ArrayList<Image> images = templeImagesMap.get(t.getCouleur()-1);
            if (images != null) {
                Image image = images.get(t.getCouleurCristal());
                hGC.drawImage(image, t.getPosition().getChAbscisse() * CARRE+1, t.getPosition().getChOrdonnee() * CARRE+1, CARRE-2, CARRE-2);
            }
        }
    }

    /**
    * Place l'apprenti sur la carte.
    * Si l'apprenti ne tient pas de cristal, il n'est pas affiché.
    */
    public void placerApprenti() {
        hGC.drawImage(chImageApprenti, chApprenti.getPosition().getChAbscisse()*CARRE+2, chApprenti.getPosition().getChOrdonnee()*CARRE+2, CARRE-4, CARRE-4);
        if (chApprenti.getCouleurCristal() == 0) {
            return;
        }
    }

    /**
     * Efface une case sur la carte à la position spécifiée.
     *
     * @param pos La position de la case à effacer.
     */
    public void effacerCase(Position pos) {
        hGC.setFill(COULEUR_FOND);
        hGC.fillRect(pos.getChAbscisse()*CARRE+1, pos.getChOrdonnee()*CARRE+1, CARRE-2, CARRE-2);
    }

    /**
     * Échange les cristaux entre l'apprenti et le temple à la position spécifiée.
     *
     * @param posTemple La position du temple avec lequel l'échange est effectué.
     */
    public void echangeCristals(Position posTemple) {
        Temple t = chScenario.getTemple(posTemple);
        if (t == null)
            return;

        if (chApprenti.getCouleurCristal() == 0) {
            chApprenti.setCouleurCristal(t.getCouleurCristal());
            t.setCouleurCristal(0);
        }
        else {
            if (t.getCouleurCristal() != 0) {
                int tmp = chApprenti.getCouleurCristal();
                chApprenti.setCouleurCristal(t.getCouleurCristal());
                t.setCouleurCristal(tmp);
            }
            else {
                t.setCouleurCristal(chApprenti.getCouleurCristal());
            }
        }
    }

    /**
     * Déplace l'apprenti selon une liste de positions de temple, en utilisant une méthode spécifique.
     *
     * @param listeTemple La liste de positions de temple à parcourir.
     * @param choix       La méthode de déplacement à utiliser : MANUEL, HEURISTIQUE ou SELECTION.
     */
    public void deplApprenti(ArrayList <Position> listeTemple, int choix) {
        final int indiceListe[] = {0} ;
        final Position posTempleProchain = new Position(listeTemple.get(0));
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                effacerCase(chApprenti.getPosition());
                placerTemples(chScenario.getTemples(), templeImagesMap);
                placerCristaux();
                Temple temple = chScenario.getTemple(chApprenti.getPosition()) ;
                if (temple != null) {
                    placerTemples(chScenario.getTemples(),templeImagesMap);
                    placerCristaux();
                }
                chApprenti.getPosition().deplacementUneCase(posTempleProchain);
                placerApprenti();

                Platform.runLater(() -> {
                    switch (choix) {
                        case MANUEL :
                            chVBoxInfo.setNbDeplacementsJouer(chApprenti.getPosition().getChNbDeplacements());
                            break;
                        case HEURISTIQUE:
                            chVBoxInfo.setNbDeplacementsHeuristique(chApprenti.getPosition().getChNbDeplacements());
                            break;
                        case SELECTION:
                            chVBoxInfo.setNbDeplacementsSelection(chApprenti.getPosition().getChNbDeplacements());
                            break;
                    }

                    if (chScenario.estAligne()) {
                        placerCristaux();
                        chVBoxInfo.setInfo("VOUS AVEZ GAGNE !!!");
                        return;
                    }
                });

                if (posTempleProchain.memePosition(chApprenti.getPosition())) {
                    echangeCristals(posTempleProchain);
                    placerApprenti();
                    indiceListe[0]++;
                    if (chScenario.estAligne() || indiceListe[0] == listeTemple.size()) {
                        if (chScenario.estAligne()) {
                            Temple last = chScenario.getTemple(chApprenti.getPosition());
                            chApprenti.setCouleurCristal(last.getCouleur());
                            placerApprenti();
                        }
                        timer.cancel();
                    }
                    else {
                        posTempleProchain.setPosition(listeTemple.get(indiceListe[0]));
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, new Date(), 200);
    }
}
