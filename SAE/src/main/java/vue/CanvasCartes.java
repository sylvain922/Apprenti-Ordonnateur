package vue;
import javafx.application.Platform;
import modele.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CanvasCartes extends Canvas implements Constantes{
    private VBoxInformations chVBoxInformations;
    private GraphicsContext hGC;
    private Image chImageApprenti;
    private Scenario chScenario;
    private Apprenti chApprenti ;

    public CanvasCartes() {
        super(L_CANVAS, H_CANVAS);
        //chApprenti = HBoxRoot.getApprenti();
        chVBoxInformations = HBoxRoot.getVBoxInfo();
        //chNomFich = nomFich;
        hGC = getGraphicsContext2D();
        affGrille();

        resetPlateau(HBoxRoot.getScenario());

        setOnMouseClicked(event -> {
            Position positionCliquee = new Position((int) event.getX() / CARRE, (int) event.getY() / CARRE);
            if (chScenario.estAligne() == false) {
                Position pos = new Position(chApprenti.getPosition());
                ArrayList<Position> liste = new ArrayList<>();
                if (chScenario.getTemple(positionCliquee) == null) {
                    chVBoxInformations.setInfo("Cliquez un temple SVP !");
                    return;
                }
                liste.add(positionCliquee);
                deplApprenti(liste, MANUEL);
            }
        });
    }

    public void actionHeuristique() {
        if (chScenario.estAligne() == true)
            return;
        //ArrayList <Position> liste = chScenario.heuristique();
        deplApprenti(HBoxRoot.getScenario().getListeHeuristique(), HEURISTIQUE);
    }

    public void actionTri() {
        if (chScenario.estAligne() == true)
            return;
        //ArrayList <Position> liste = chScenario.triSelection();
        deplApprenti(HBoxRoot.getScenario().getChListeTriSelection(), TRI);
    }

    public void affGrille() {
        hGC.setStroke(Color.LIGHTGRAY);      // grille
        hGC.setLineWidth(1);

        for (int y = 0; y < H_CANVAS; y += CARRE)
            hGC.strokeLine(0, y, L_CANVAS, y);
        for (int x = 0; x <= L_CANVAS; x += CARRE)
            hGC.strokeLine(x, 0, x, H_CANVAS);
    }

    /*public void setNomFich(String nom) {
        chNomFich = nom;
    }*/

    public void resetPlateau(Scenario parScenario) {
        if (chScenario != null) {                       // effacer tous temples
            for (Temple temple : chScenario.getTemples())
                effacerCase(temple.getPosition());
        }

        try {                           // placer l'apprenti
            chImageApprenti = new Image(new FileInputStream(ICONE_APPRENTI));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //if (chApprenti.getPosition().estMemePosition(POS_APPRENTI) == false)
          //  effacerCase(chApprenti.getPosition());
        chScenario = HBoxRoot.getScenario();
        chApprenti = parScenario.getApprenti();

        //chApprenti.setPosition(POS_APPRENTI);   // pour apprenti
        //chApprenti.setCouleurCristal(0);
        placerApprenti();
        chApprenti.getPosition().setNbDepl(0);

        chVBoxInformations.setNbDeplHeuris(0);         // pour les nb depl
        chVBoxInformations.setNbDeplManuel(0);
        chVBoxInformations.setNbDeplTri(0);
        chVBoxInformations.setInfo("");

        //System.out.println(chScenario);
        placerTemples(chScenario.getTemples());
    }

    public void placerUnCristal(Temple temple, int couleur) {
        int diam;
        hGC.setFill(COULEURS_TEMPLES[couleur]);
        if (temple.getCouleur() == couleur)
            diam = DIAM_CRISTAL + 2;
        else
            diam = DIAM_CRISTAL;
        hGC.fillOval(temple.getPosition().getPosX() * CARRE + (CARRE- diam)/2, temple.getPosition().getPosY() * CARRE + (CARRE- diam)/2, diam, diam);
    }

    public void placerUnTemple(Temple temple) {
        //System.out.println(temple);
        hGC.setFill(COULEURS_TEMPLES[temple.getCouleur()]);
        hGC.fillRect(temple.getPosition().getPosX()*CARRE+(CARRE-CARRE_TEMPLE)/2, temple.getPosition().getPosY()*CARRE+(CARRE-CARRE_TEMPLE)/2, CARRE_TEMPLE, CARRE_TEMPLE);
        placerUnCristal(temple, temple.getCouleurCristal());
    }

    public void placerTemples(ArrayList <Temple> temples) {
        for (Temple t : temples) {
            placerUnTemple(t);
        }
    }

    public void placerApprenti() {
        hGC.drawImage(chImageApprenti, chApprenti.getPosition().getPosX()*CARRE+4, chApprenti.getPosition().getPosY()*CARRE+2, CARRE-20, CARRE-4);
        if (chApprenti.getCouleurCristal() == 0) {
            //System.out.println("placerApprenti : " + apprenti.getPosition());
            return;
        }
        hGC.setFill(COULEURS_TEMPLES[chApprenti.getCouleurCristal()]);     // dessiner cristal
        hGC.fillRect(chApprenti.getPosition().getPosX()*CARRE+CARRE-19, chApprenti.getPosition().getPosY()*CARRE+5, L_CRISTAL, H_CRISTAL);
    }

    public void effacerCase(Position pos) {
        hGC.setFill(COULEUR_FOND);
        //hGC.fillRect(pos.getPosX()*CARRE+2, pos.getPosY()*CARRE+2, CARRE-4, CARRE-4);
        hGC.fillRect(pos.getPosX()*CARRE+1, pos.getPosY()*CARRE+1, CARRE-2, CARRE-2);
    }

    public void echangeCristals(Position posTemple) {
        Temple t = chScenario.getTemple(posTemple);
        if (t == null)
            return;

        if (chApprenti.getCouleurCristal() == 0) {        // 1er arrivage, apprenti enleve le cristal
            chApprenti.setCouleurCristal(t.getCouleurCristal());
            t.setCouleurCristal(0);
        }
        else {              // echange les cristals
            if (t.getCouleurCristal() != 0) {
                int tmp = chApprenti.getCouleurCristal();
                chApprenti.setCouleurCristal(t.getCouleurCristal());
                t.setCouleurCristal(tmp);
            }
            else {                  // Si coul cristal temple = 0, apprent ne change pad coul
                t.setCouleurCristal(chApprenti.getCouleurCristal());
            }

        }
    }

    public void deplApprenti(ArrayList <Position> listeTemple, int choix) {
        final int indiceListe[] = {0} ;
        final Position posTempleProchain = new Position(listeTemple.get(0));
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                effacerCase(chApprenti.getPosition());
                Temple temple = chScenario.getTemple(chApprenti.getPosition()) ;     // remet le temple effacé
                if (temple != null) {
                    placerUnTemple(temple);     // remet le temple cassé
                }
                chApprenti.getPosition().deplUneCase(posTempleProchain);
                placerApprenti();

                Platform.runLater(() -> {
                    //labDepl.setText("Deplacement : " + apprenti.getPosition().gerNbDepl());
                    switch (choix) {
                        case MANUEL :
                            chVBoxInformations.setNbDeplManuel(chApprenti.getPosition().gerNbDepl());
                            break;
                        case HEURISTIQUE:
                            chVBoxInformations.setNbDeplHeuris(chApprenti.getPosition().gerNbDepl());
                            break;
                        case TRI:
                            chVBoxInformations.setNbDeplTri(chApprenti.getPosition().gerNbDepl());
                            break;
                    }

                    if (chScenario.estAligne()) {
                        chVBoxInformations.setInfo("BRAVO");
                        return;
                    }
                });

                if (posTempleProchain.estMemePosition(chApprenti.getPosition())) {     // arriver au temple
                    echangeCristals(posTempleProchain);
                    placerApprenti();               // pour le nouveau cristal apprenti
                    indiceListe[0]++;
                    if (chScenario.estAligne() || indiceListe[0] == listeTemple.size()) {
                        if (chScenario.estAligne()) {
                            Temple last = chScenario.getTemple(chApprenti.getPosition());
                            chApprenti.setCouleurCristal(last.getCouleur());
                            placerApprenti();   // pour cristal apprenti a la m coul que temple
                            //System.out.println("aligne " + apprenti);
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
