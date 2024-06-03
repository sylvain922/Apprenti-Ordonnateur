package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class ApprentiOrdonnateurApplication extends Application implements Constantes {
    public void start(Stage stage)  {
        HBoxRoot root = new HBoxRoot();
        MenuScene menuScene = new MenuScene(stage);
        Scene scene = new Scene(menuScene, L_FENETRE, H_FENETRE);
        File file = new File("icones/fond.png");
        String cheminImage = file.toURI().toString();
        menuScene.setStyle(
                "-fx-background-image: url('" + cheminImage + "');"+
                        "-fx-background-size: cover;"
        );
        stage.setScene(scene);
        stage.setTitle("SAE L'Apprenti Ordonnateur");
        stage.show();

        File css = new File("css" + File.separator + "sae.css");
        scene.getStylesheets().add(css.toURI().toString());
    }

    public static void main(String [] arg) {
        Application.launch(arg);
    }
}
