package vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuScene extends VBox {
    public MenuScene(Stage primaryStage) {
        super(20);
        setAlignment(Pos.CENTER);
        setSpacing(20);

        // Chargement de l'image
        Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/1/10/UVSQ_Logo.svg/2560px-UVSQ_Logo.svg.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200); // Ajuster la largeur selon les besoins
        imageView.setPreserveRatio(true);

        Separator separator = new Separator();
        separator.setMaxWidth(600);

        // Titre principal
        Text titrePrincipal = new Text("SAE IUT Velizy-Villacoublay");
        titrePrincipal.setFill(Color.WHITE);
        titrePrincipal.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Conteneur pour l'image et le titre
        VBox vboxContenu = new VBox(10); // Espacement entre l'image et le texte
        vboxContenu.setAlignment(Pos.CENTER);
        vboxContenu.getChildren().addAll(imageView, titrePrincipal);

        // Titre pour le menu
        Text titre = new Text("Menu du Jeu");
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titre.setFill(Color.WHITE);

        Button btnJeu = new Button("Jouer");
        Button btnRegles = new Button("Règles");

        // Style des boutons
        btnJeu.setStyle("-fx-font-size: 18px;");
        btnRegles.setStyle("-fx-font-size: 18px;");

        Label labelCredits = new Label("Jeu développé par : Luca DA SILVA, Sylvain COUTURIER et Esteban COLOMBANI");
        labelCredits.setStyle("-fx-font-size: 17px;" +
                "-fx-text-fill: white");

        Label labelClasse = new Label("BUT INFO 1 | INF1-A");
        labelClasse.setStyle("-fx-font-size: 17px;" +
                "-fx-text-fill: white;");

        // Action des boutons
        btnJeu.setOnAction(e -> {
            // Changer vers la scène de jeu
            HBoxRoot root = new HBoxRoot();
            Scene scene = new Scene(root, Constantes.L_FENETRE, Constantes.H_FENETRE);
            primaryStage.setScene(scene);
        });

        btnRegles.setOnAction(e -> {
            // Changer vers la scène des règles
            ReglesScene reglesScene = new ReglesScene(primaryStage);
            Scene scene = new Scene(reglesScene, Constantes.L_FENETRE, Constantes.H_FENETRE);
            primaryStage.setScene(scene);
        });

        // Création du deuxième séparateur
        Separator secondSeparator = new Separator();
        secondSeparator.setMaxWidth(600);

        // Ajouter les éléments à la VBox
        getChildren().addAll(vboxContenu, separator, titre, btnJeu, btnRegles, secondSeparator, labelCredits, labelClasse);
    }
}