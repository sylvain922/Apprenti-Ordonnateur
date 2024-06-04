package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import vue.Constantes;
import vue.*;

/**
 * Cette classe est le contrôleur principal qui gère les événements des boutons de l'interface utilisateur.
 * Elle implémente l'interface EventHandler et utilise les constantes définies dans l'interface Constantes.
 */
public class Controleur implements EventHandler, Constantes {
    /**
     * Gère les événements déclenchés par les boutons de l'interface utilisateur.
     * Selon le bouton cliqué, cette méthode appelle différentes actions sur le CanvasMap et met à jour l'interface utilisateur.
     *
     * @param event L'événement déclenché par un bouton.
     */
    public void handle(Event event) {
        CanvasMap carte = HBoxRoot.getCarteCanvas();
        VBoxInformations vbox = HBoxRoot.getVBoxInfo();

        Button bouton = (Button) event.getSource();
        if (bouton.getUserData().equals(TITRE_BOUTON_RESET)) {      //Si le bouton Reset est cliqué
            System.out.println(TITRE_BOUTON_RESET);
            HBoxRoot.creerScenario();
            carte.resetPlateau(HBoxRoot.getScenario());
        }
        else if (bouton.getUserData().equals(TITRE_BOUTON_SELECTION)) {     //Si le bouton Sélection est cliqué
            carte.actionSelection();
        }
        else if (bouton.getUserData().equals(TITRE_BOUTON_HEURISTIQUE)) {       //Si le bouton Heuristique est cliqué
            carte.actionHeuristique();
        }
    }
}
