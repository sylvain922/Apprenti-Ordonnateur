package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ApprentiOrdonnateurApplication extends Application {
    public void start(Stage stage) {
        HboxRoot root = new HboxRoot();
        Scene scene = new Scene(root, 1500, 950);
        /*File [] fichiersCss = new File("css").listFiles();
        for (File fichier : fichiersCss) {
            //scene.getStylesheets().add(fichier.toURI().toString());
        }*/
        stage.setScene(scene);
        stage.setTitle("Apprenti Ordonnateur");
        stage.show();
    }
    public static void main (String [] args) {
        Application.launch(args);
    }
}