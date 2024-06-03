package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import vue.Constantes;
import vue.*;


public class Controleur implements EventHandler, Constantes {
    public void handle(Event event) {
        CarteCanvas canvas = HBoxRoot.getCarteCanvas();

        Button bouton = (Button) event.getSource();
        if (bouton.getUserData().equals("Reset")) {
            System.out.println("Reset");
            HBoxRoot.creerScenario();     // Recreation scenario indispensable, car temples sont modifiés
            canvas.resetPlateau(HBoxRoot.getScenario());
        }
        else if (bouton.getUserData().equals("Tri Selection")) {
            canvas.actionTri();
        }
        else if (bouton.getUserData().equals("Tri Heuristique")) {
            canvas.actionHeuristique();
        }
    }
}
