package vue;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modele.Position;

public class VBoxInformations extends VBox {

    public VBoxInformations () {
        super();

        VBox informationsBox = new VBox();
        informationsBox.setAlignment(Pos.CENTER);
        Label infos = new Label(" Informations du joueur ");
        infos.setStyle(
                "-fx-font-family: 'Serif';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: black;"
        );
        Label labelNombreDePas = new Label("Nombre de pas : "+ Position.getNombreDePas());
        informationsBox.getChildren().addAll(infos, labelNombreDePas);
        VBox.setMargin(labelNombreDePas, new Insets(30));

        // Création de la GridPane pour l'interface de bienvenue
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Espacement horizontal entre les colonnes
        gridPane.setVgap(20); // Espacement vertical entre les lignes
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(50)); // marges autour de la GridPane

        // Titre de l'interface de bienvenue
        Label titleLabel = new Label("Bienvenue dans l'Apprenti Ordonnateur");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Bouton "Start" dans l'interface de bienvenue
        Button startButton = new Button("Start");
        startButton.setStyle("-fx-font-size: 18px;");
        startButton.setOnAction(event -> {
            // Ajouter ici le code pour afficher les statistiques et les informations de la partie
            // Par exemple, vous pouvez les ajouter à une VBox et les placer dans ce VBoxInformations
            VBox statistiquesBox = new VBox();
            // Ajoutez ici les statistiques et informations de la partie à la VBox

            this.getChildren().setAll(statistiquesBox, informationsBox);
            VBox.setVgrow(statistiquesBox, Priority.ALWAYS);
            VBox.setVgrow(informationsBox, Priority.NEVER);
            VBox.setMargin(statistiquesBox, new Insets(30, 0, 0, 0));
        });
        startButton.setMnemonicParsing(true);

        // Ajout des éléments à la GridPane
        gridPane.add(titleLabel, 0, 0, 2, 1); // le titre sur la première ligne
        gridPane.add(startButton, 1, 1, 2, 1); // le bouton sur la deuxième ligne

        // Centrer le bouton "Start" horizontalement
        GridPane.setHalignment(startButton, HPos.CENTER);

        // Centrer verticalement et horizontalement les VBox
        informationsBox.setAlignment(Pos.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);

        // Ajout de la GridPane à cette VBox
        this.getChildren().add(gridPane);
    }
}
