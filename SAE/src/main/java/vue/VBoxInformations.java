package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
public class VBoxInformations extends VBox implements Constantes{
    private Button boutonTri = new Button(TITRE_BOUTON_TRI);
    private Button boutonHeurist = new Button(TITRE_BOUTON_HEURIS);
    private Button boutonReset = new Button(TITRE_BOUTON_RESET);
    private Label labManuel = new Label("Nb Déplacements");
    private Label labNbManuel = new Label("0");
    private Label labNbTri =  new Label("0"), labNbTriCalcul = new Label("0");
    private Label labNbHeuris =  new Label("0"), labNbHeurisCalcul = new Label("0");
    private Label labBravo = new Label("Info");


    public VBoxInformations() {
        super(100);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));
        Label labTemps = new Label("Temps du jeu");
        labTemps.setStyle(
                "-fx-font-size: 1.7em;"+
                        "-fx-text-fill: white"
        );
        Label labChrono = new Label("00 : 00 : 00");
        labChrono.setStyle(
                "-fx-font-size: 1.4em;"+
                        "-fx-text-fill: WHITE"
        );
        boutonHeurist.setStyle(
                "-fx-background-color: BLACK;"+
                        "-fx-text-fill: WHITE"
        );
        boutonTri.setStyle(
                "-fx-background-color: BLACK;"+
                        "-fx-text-fill: WHITE"
        );
        boutonReset.setStyle(
                "-fx-background-color: BLACK;"+
                        "-fx-text-fill: WHITE"
        );
        gridPane.add(labTemps, 0, 12, 2, 1); // Ajout d'un colspan de 2 pour étendre sur deux colonnes
        gridPane.add(labChrono, 0, 13, 2, 1);

        Separator separator1 = new Separator();
        gridPane.add(separator1, 0, 16, 2, 1); // Ajout d'un séparateur sous le label "Temps du jeu"

        gridPane.add(labManuel, 0, 18);
        gridPane.add(labNbManuel, 1, 18);

        gridPane.add(boutonHeurist, 0, 20);
        gridPane.add(new Label("NB Déplacements"), 0, 21);
        gridPane.add(labNbHeuris, 1, 21);
        gridPane.add(new Label("Déplacements Min"), 0, 22);
        gridPane.add(labNbHeurisCalcul, 1, 22);

        gridPane.add(boutonTri, 0, 24);
        gridPane.add(new Label("NB Déplacements"), 0, 25);
        gridPane.add(labNbTri, 1, 25);
        gridPane.add(new Label("Déplacements Min"), 0, 26);
        gridPane.add(labNbTriCalcul, 1, 26);

        Separator separator2 = new Separator();
        gridPane.add(separator2, 0, 28, 2, 1); // Ajout d'un séparateur après le label "Temps du jeu"

        labBravo.setId("bravo");
        labBravo.setStyle(
                "-fx-text-fill: white"
        );
        boutonHeurist.setUserData(TITRE_BOUTON_HEURIS);
        boutonTri.setUserData(TITRE_BOUTON_TRI);
        boutonReset.setUserData(TITRE_BOUTON_RESET);
        boutonReset.setOnAction(HBoxRoot.getControleur());
        boutonTri.setOnAction(HBoxRoot.getControleur());
        boutonHeurist.setOnAction(HBoxRoot.getControleur());
        //System.out.println(HBoxRoot.getControleur());

        getChildren().addAll(gridPane, labBravo, boutonReset);
    }

    public void setLabChrono(){

    }

    public void setNbDeplTri(int nbDeplTri) {
        labNbTri.setText(Integer.toString(nbDeplTri));
    }

    public void setNbDeplHeuris(int nbDeplHeuris) {
        labNbHeuris.setText(Integer.toString(nbDeplHeuris));
    }

    public void setNbDeplManuel(int nbDepl) {
        labNbManuel.setText(Integer.toString(nbDepl));
    }

    public void setNbDeplHeurisCalcul(int nbDeplHeuris) {
        labNbHeurisCalcul.setText(Integer.toString(nbDeplHeuris));
    }

    public void setNbDeplTriCalcul(int nbDeplHeuris) {
        labNbTriCalcul.setText(Integer.toString(nbDeplHeuris));
    }

    public void setLabNbCalcul(int nbHeuris, int nbTri) {
        labNbHeurisCalcul.setText(Integer.toString(nbHeuris));
        labNbTriCalcul.setText(Integer.toString(nbTri));
    }
    public void setInfo(String info) {
        labBravo.setText(info);
    }
}
