package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

/**
 * La classe ApplicationOrdonnateur représente l'application principale.
 * Elle initialise et affiche la scène principale avec un menu et un arrière-plan personnalisé.
 */
public class ApplicationOrdonnateur extends Application implements Constantes {

    /**
     * Démarre l'application en initialisant et en affichant la scène principale.
     *
     * @param stage Le conteneur principal pour toutes les scènes de l'application.
     */
    public void start(Stage stage)  {
        MenuScene menuScene = new MenuScene(stage);
        Scene scene = new Scene(menuScene, LARGEUR_FENETRE, HAUTEUR_FENETRE);
        File file = new File("img/fond.jpg");
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

    /**
     * Le point d'entrée principal de l'application.
     *
     * @param arg Les arguments de ligne de commande.
     */
    public static void main(String [] arg) {
        Application.launch(arg);
    }
}
