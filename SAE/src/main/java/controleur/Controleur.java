package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import modele.LectureScenario;
import modele.Temples;
import vue.HboxRoot;
import vue.VBoxInformations;
import vue.VBoxRoot;

import java.io.File;
import java.util.Collection;

public class Controleur implements EventHandler {

    @Override
    public void handle(Event event) {
        Object userData = ((MenuItem)event.getSource()).getUserData();
        VBoxRoot map = HboxRoot.getVueCartes();
        VBoxInformations infos = HboxRoot.getVueInfos();
        if (userData instanceof File) {//l'ut. a choisi un scénario
            File fichierScenario = (File)userData;
            System.out.println(fichierScenario.getName());
            File scenario = fichierScenario;
            Collection<Temples> temples = LectureScenario.lecture(fichierScenario);
            VBoxRoot.getApprenti().setTemples(temples);
            System.out.println(VBoxRoot.getApprenti());
        }
        if (event.getSource() instanceof Button) {
            System.out.println(infos);
        }
    }
}