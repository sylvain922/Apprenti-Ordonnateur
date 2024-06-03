package vue;

import controleur.Controleur;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import modele.LectureScenario;

import java.io.File;

public class HBoxRoot extends HBox implements Constantes {
    private static VBoxInformations vueInfos;
    private static CanvasCartes vueCarte;
    private static Controleur controleur;
    private static LectureScenario scenario;
    private static String chNomFich = "";

    public HboxRoot(){
        super(30);
        controleur = new Controleur();
        vueInfos = new VBoxInformations();
        scenario = new LectureScenario(chNomFich);
        vueCarte = new CanvasCartes();
        getChildren().addAll(menuBar, vueCarte, vueInfos);
        setLabelsNbDeplBoxDroit();
    }

    public static VBoxInformations getVBoxInfo() {return vueInfos;}

    public static CanvasCartes getCanvasCartes() {return vueCarte;}

    public static LectureScenario getScenario() {return scenario;}

    public static String getNomFich() {return chNomFich;}
}
