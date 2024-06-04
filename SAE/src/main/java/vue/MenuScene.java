package vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Classe MenuScene qui représente la scène du menu principal de l'application.
 * Elle permet à l'utilisateur de naviguer vers le jeu ou les règles.
 */
public class MenuScene extends VBox {

    /**
     * Constructeur de MenuScene. Initialise le menu principal avec ses composants.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */
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
        separator.setMaxWidth(500);

        // Titre principal
        Text titrePrincipal = new Text("SAE IUT Velizy-Villacoublay");
        titrePrincipal.setFill(Color.WHITE);
        titrePrincipal.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label espace = new Label("");
        espace.setPrefHeight(20); // Hauteur de l'espace en pixels

        // Conteneur pour l'image et le titre
        VBox vboxContenu = new VBox(10); // Espacement entre l'image et le texte
        vboxContenu.setAlignment(Pos.CENTER);
        vboxContenu.getChildren().addAll(imageView, espace, titrePrincipal);

        // Titre pour le menu
        Text titre = new Text("Menu du Jeu");
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titre.setFill(Color.WHITE);

        Button btnJeu = new Button("Jouer");
        Button btnRegles = new Button("Règles");

        // Style des boutons
        btnJeu.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-background-color: black;"+
                        "-fx-text-fill: white"
                );
        btnRegles.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-background-color: black;"+
                        "-fx-text-fill: white"
        );

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
            Scene scene = new Scene(root, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);
            primaryStage.setScene(scene);
        });

        btnRegles.setOnAction(e -> {
            // Changer vers la scène des règles
            ReglesScene reglesScene = new ReglesScene(primaryStage);
            Scene scene = new Scene(reglesScene, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);
            primaryStage.setScene(scene);
        });

        // Création du deuxième séparateur
        Separator secondSeparator = new Separator();
        secondSeparator.setMaxWidth(500);

        // Ajouter les éléments à la VBox
        getChildren().addAll(vboxContenu, separator, titre, btnJeu, btnRegles, secondSeparator, labelCredits, labelClasse);
    }
}
