package vue;

import controleur.Controleur;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HboxRoot extends HBox {
    private static Controleur controleur;
    private static VBoxRoot vueCartes;

    public HboxRoot(){
        super(40);
        controleur = new Controleur();

        vueCartes = new VBoxRoot();
        getChildren().addAll(vueCartes);
    }

    public static Controleur getControleur(){return controleur;}
    public static VBoxRoot getVueCartes(){return vueCartes;}
}