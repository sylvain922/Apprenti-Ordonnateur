package vue;

import controleur.Controleur;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import modele.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static modele.LectureScenario.templesDuScenario;

public class VBoxRoot extends VBox implements ConstanteCanvas {
    private Canvas chCarte;

    private static Position positionApprenti = new Position(NB_CARRE_HAUTEUR/2, NB_CARRE_LARGEUR/2); //représente la position de l'apprenti sur la carte

    private GraphicsContext graphicsContext2D;
    private static Apprenti chApprenti ;
    private static Controleur controleur ;
    private ArrayList<Temples> chTemples;
    private ArrayList<Cristal> chCristaux;

    public VBoxRoot () {
        chApprenti = new Apprenti();
        controleur = new Controleur ();
        chTemples = LectureScenario.getTemples();
        chCristaux = Cristal.getListCristaux();
        // La barre de menus
        MenuBar menuBar = new MenuBar ();
        this.getChildren().add(menuBar);
        VBox.setMargin(menuBar, new Insets (9 ));
        // Le menu des scénarios
        Menu menuScenarios = new Menu (INTITULE_MENU_SCENARIOS);
        menuBar.getMenus().add(menuScenarios);
        // Les items du menu scénario
        File[]scenarios = new File("scenarios").listFiles();
        for (int i= 0; i < scenarios.length ; i++) {
            MenuItem menuItem = new MenuItem(scenarios[i].getName());
            menuItem.setUserData(scenarios[i]);
            menuItem.setOnAction(controleur);
            menuScenarios.getItems().add(menuItem);
        }

        // l'étiquette qui affiche le nombre de pas
        Label labelInfosJoueur = new Label ("    Informations du joueur    ");
        labelInfosJoueur.setStyle(
                "-fx-font-family: 'Serif';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: black;" +
                        "-fx-background-color: white;"
        );
        Label labelNombreDePas = new Label("Nombre de pas : "+Position.getNombreDePas());
        Label labelPosJoueur = new Label("Position du joueur : ("+chApprenti.getPos().getAbscisse()+", "+chApprenti.getPos().getOrdonnee()+")");
        Label labelCristal = new Label("Cristal sur le joueur : "+chApprenti.getCristal());
        labelNombreDePas.setStyle(
                "-fx-font-family: 'Serif';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );
        labelPosJoueur.setStyle(
                "-fx-font-family: 'Serif';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );
        labelCristal.setStyle(
                "-fx-font-family: 'Serif';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );
        // le canvas et son contexte graphique
        Canvas canvasCarte = new Canvas();
        canvasCarte.setWidth(LARGEUR_CANVAS);
        canvasCarte.setHeight(HAUTEUR_CANVAS);
        graphicsContext2D = canvasCarte.getGraphicsContext2D();
        graphicsContext2D.setFill(Color.BURLYWOOD);

        // Remplissage du rectangle de fond couvrant tout le canvas
        graphicsContext2D.fillRect(0, 0, graphicsContext2D.getCanvas().getWidth(), graphicsContext2D.getCanvas().getHeight());


        // les carrés
        graphicsContext2D.setStroke(Paint.valueOf(COULEUR_GRILLE));
        for (int i = 0; i < LARGEUR_CANVAS; i += CARRE) {
            for (int j = 0; j < HAUTEUR_CANVAS; j += CARRE) {
                graphicsContext2D.strokeRect(i, j, CARRE, CARRE);
            }
        }

        // ajout des composants graphiques à la racine (this)
        this.getChildren().addAll(labelInfosJoueur, labelNombreDePas, labelPosJoueur, labelCristal);
        VBox.setMargin(labelInfosJoueur, new Insets(10, 0, 0, 30));
        VBox.setMargin(labelNombreDePas, new Insets(10, 0, 0, 30));
        VBox.setMargin(labelPosJoueur, new Insets(10, 0, 0, 30));
        VBox.setMargin(labelCristal, new Insets(10, 0, 0, 30));
        this.getChildren().add(canvasCarte);
        VBox.setMargin(canvasCarte, new Insets(30));

        //les numéros de colonne
        /*int numCol = 1;
        graphicsContext2D.setFill(Paint.valueOf(COULEUR_GRILLE));
        for (int i = CARRE; i < LARGEUR_CANVAS; i += CARRE){
            graphicsContext2D.fillText(Integer.toString(numCol), i+CARRE/3, CARRE/2);
            numCol++;
        }

        //les numéros des lignes
        /*int numLigne = 1;
        graphicsContext2D.setFill(Paint.valueOf(COULEUR_GRILLE));
        for (int i = CARRE; i < HAUTEUR_CANVAS; i += CARRE){
            graphicsContext2D.fillText(Integer.toString(numLigne), CARRE/3, i+CARRE/2);
            numLigne++;
        }*/
        //Image image = new Image("Z://projetCanvas-20240517T090624Z-001/projetCanvas/img/cristal_bleu.png");
        //placerMilieuCase(graphicsContext2D.drawImage(image, 2*CARRE, 3*CARRE, CARRE, CARRE));
        placerMilieuCase();
        placerTemple(chTemples);
        placerCristaux(chCristaux);
        canvasCarte.setOnMouseClicked(event -> {
            int abscisse = (int) event.getX() / CARRE;
            int ordonnee = (int) event.getY() / CARRE;
            Position positionCliquee = new Position(abscisse, ordonnee);
            //System.out.println(positionCliquee);
            labelPosJoueur.setText("Position du joueur : ("+abscisse+", "+ordonnee+")");

            // Pour convertir les coordonnées de la grille en coordonnées de pixel
            //GraphicsContext gc = canvasCarte.getGraphicsContext2D();
            //gc.setFill(Color.BEIGE); // La couleur de remplissage pour le carré
            //gc.fillRect(abscisse * CARRE, ordonnee * CARRE, CARRE, CARRE); // Multipliez par CARRE pour les coordonnées de pixel
            deplacementAvecTimer(positionApprenti, positionCliquee);
            Platform.runLater(() -> {
                labelNombreDePas.setText("Nombre de pas : " + Position.getNombreDePas());
            });
        });
    }
    public static Apprenti getApprenti() {
        return chApprenti;
    }

    public void placerTemple(ArrayList<Temples> temples) {
        for (Temples t : temples){
            graphicsContext2D.setFill(Paint.valueOf(t.getCouleurTemple()));
            graphicsContext2D.fillOval(t.getPos().getAbscisse()*CARRE + CARRE/8, t.getPos().getOrdonnee()*CARRE + CARRE/4, LARGEUR_OVALE, HAUTEUR_OVALE);
            System.out.println("Temple placé en "+t.getPos());
        }
    }

    public void placerCristaux(ArrayList<Cristal> cristals) {
        Cristal.initCristaux();
        for (Cristal c : cristals){
            graphicsContext2D.setFill(Paint.valueOf(c.getCouleurCristal()));
            double x = c.getPos().getAbscisse() * CARRE + CARRE / 4; // Utilisez CARRE / 4 au lieu de CARRE / 8 pour centrer le cristal horizontalement
            double y = c.getPos().getOrdonnee() * CARRE + CARRE / 4; // Utilisez c.getPos().getOrdonnee() au lieu de c.getPos().getAbscisse() pour obtenir l'ordonnée de la position
            graphicsContext2D.fillOval(x, y, LARGEUR_ITEM/2, HAUTEUR_ITEM/2);
            System.out.println("Cristal placé en "+c.getPos());
        }
    }


    public void placerMilieuCase(){
        graphicsContext2D.setFill(Paint.valueOf(COULEUR_APPRENTI));
        graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + CARRE/8, positionApprenti.getOrdonnee()*CARRE + CARRE/4, LARGEUR_OVALE, HAUTEUR_OVALE);
    }

    public void effacerMilieuCase(){
        graphicsContext2D.setFill(Color.BURLYWOOD); // Utilisez la couleur de fond de votre grille
        graphicsContext2D.fillRect(positionApprenti.getAbscisse() * CARRE, positionApprenti.getOrdonnee() * CARRE, CARRE, CARRE);

        // Redessinez la grille pour cette case spécifique
        graphicsContext2D.setStroke(Paint.valueOf(COULEUR_GRILLE));
        graphicsContext2D.strokeRect(positionApprenti.getAbscisse() * CARRE, positionApprenti.getOrdonnee() * CARRE, CARRE, CARRE);
    }
    private void deplacementAvecTimer(Position positionCourante, Position positionCible){
        if (positionApprenti.getAbscisse()==positionApprenti.getAbscisse()+1){
            ;
        }
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                effacerMilieuCase();
                positionCourante.deplacementUneCase(positionCible);
                placerMilieuCase();
                if (positionCourante.equals(positionCible)){
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 200);
    }
}