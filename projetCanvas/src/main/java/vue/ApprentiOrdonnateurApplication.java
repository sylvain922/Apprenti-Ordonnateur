package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ApprentiOrdonnateurApplication extends Application {
    public void start(Stage stage) {
        HboxRoot root = new HboxRoot();
        Scene scene = new Scene(root, 1000, 970);
        root.setStyle(
                "-fx-background-image: url(https://i.pinimg.com/originals/60/72/f5/6072f5c3b87e6b4781c4a374aac20183.jpg)"
        );
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