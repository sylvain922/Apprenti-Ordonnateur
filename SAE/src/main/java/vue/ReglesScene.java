package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;

public class ReglesScene extends VBox {
    public ReglesScene(Stage primaryStage) {
        super(20);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setBackground();

        // Titre principal
        Label titrePrincipal = new Label("Règles du jeu");
        titrePrincipal.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titrePrincipal.setTextFill(Color.WHITE);

        // Texte des règles
        String texteRegles = "Sujet SAE 2.02 : Exploration algorithmique d'un problème\n\n" +
                "L'Apprenti Ordonnateur\n\n" +
                "Introduction\nAu royaume de Krystalia, les sorciers, magiciens et autres enchanteurs pullulent dans tous les coins. En effet, les énergies magiques abondent, grâce aux cristaux de pouvoir, répartis dans le pays dans les différents temples de magie. Chaque temple est dédié à une couleur, et possède en son cœur un cristal qui doit être de la même couleur : on dit que les cristaux sont alignés. De temps en temps, des sorciers dépassent un peu les bornes de ce qui est permis par la magie des mortels, ce qui déclenche des tempêtes de magie. Suite à une telle tempête, les cristaux changent de couleur. Ainsi, le cristal du temple vert devient rouge, celui du temple rouge est jaune, etc. Il convient alors de réaligner les cristaux, et la méthode la plus simple est de les déplacer pour les amener au temple de leur couleur. C'est le travail du Grand Ordonnateur de Krystalia de procéder à ce réalignement après chaque tempête. Malheureusement, le grand Ordonnateur est en vacances quand se déclenche une énorme tempête qui a permuté tous les cristaux. Et c'est à vous, l'apprenti ordonnateur, qu'incombe la difficile tâche du réalignement des cristaux...\n\n" +
                "La carte\nUn scénario est caractérisé par la liste des temples avant le réalignement, donnés par leurs coordonnées, leur couleur puis la couleur du cristal qu'ils contiennent. S'il y a N temples, les couleurs seront encodées par les entiers de 1 à N. Un scénario est encodé dans un fichier texte, et contient N lignes contenant 4 entiers, comme \"12 -4 3 7\", ce qui signifie :\n" +
                "    - la position du temple est aux coordonnées (12,-4)\n" +
                "    - la couleur du temple est 3\n" +
                "    - le temple contient le cristal de couleur 7\n\n" +
                "Les règles\nL'apprenti commence sur la case (0,0) et ne peut se déplacer que d'une case par tour, dans les quatre directions de base.\n" +
                "L'apprenti ne peut porter qu'un seul cristal à la fois, à cause des interférences. Au départ, l'apprenti ne porte aucun cristal.\n" +
                "Si l'apprenti ne possède pas de cristal, en entrant sur la case d'un temple il peut emporter le cristal du temple. S'il porte un cristal, il peut échanger les cristaux, en déposant au temple celui qu'il portait et en emportant celui du temple.\n" +
                "L'apprenti ne peut pas déposer un cristal sur une case vide ou dans un temple contenant déjà un cristal, sauf échange dans un temple comme expliqué ci-dessus.\n" +
                "L'apprenti peut également traverser un temple comme si c'était une case vide, sans déposer ni prendre de cristal.\n\n" +
                "L'objectif de l'apprenti\nL'objectif de l'apprenti est de réaligner les cristaux le plus rapidement possible, c'est à dire en minimisant le nombre total de déplacements. Typiquement, en partant de la case (0,0), il se rendra à un premier temple pour prendre un cristal, ira à un second temple déposer le premier cristal et prendre l'autre, et ainsi de suite. Vous pouvez vous rendre compte que les seules positions à réellement étudier sont celles où l'apprenti se trouve sur un temple, et les données pertinentes à ce moment pour caractériser la situation sont les positions des cristaux et de l'apprenti. Dès que l'apprenti apporte au bon temple le dernier cristal et que tous les cristaux sont alignés, l'objectif est considéré réalisé et on compte à ce moment le nombre total de déplacements effectués.";

        // Label pour afficher les règles
        Label labelRegles = new Label(texteRegles);
        labelRegles.setTextAlignment(TextAlignment.JUSTIFY);
        labelRegles.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        labelRegles.setTextFill(Color.WHITE);

        // Ajout des éléments à la VBox
        getChildren().addAll(titrePrincipal, labelRegles);
    }

    private void setBackground() {
        File fileImage = new File("icones/fond.png");
        String cheminImage = fileImage.toURI().toString();
        this.setStyle(
                "-fx-background-image: url('" + cheminImage + "');" +
                        "-fx-background-size: cover;"
        );
    }
}