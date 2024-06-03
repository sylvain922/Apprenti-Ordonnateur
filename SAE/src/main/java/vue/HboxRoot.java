package vue;

import controleur.Controleur;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HboxRoot extends HBox {
    private static Controleur controleur;
    private static VBoxRoot vueCartes;
    private static VBoxInformations vueInfos;

    public HboxRoot(){
        super(40);
        controleur = new Controleur();

        vueCartes = new VBoxRoot();
        vueInfos = new VBoxInformations();
        getChildren().addAll(vueCartes, vueInfos);
    }

    public static Controleur getControleur(){return controleur;}
    public static VBoxRoot getVueCartes(){return vueCartes;}

    public static VBoxInformations getVueInfos(){return vueInfos;}
}
