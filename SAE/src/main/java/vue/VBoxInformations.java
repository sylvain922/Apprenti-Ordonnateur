package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * La classe VBoxInformations représente une boîte verticale affichant des informations et des boutons pour l'interface utilisateur.
 * Elle contient des boutons pour les différentes actions, ainsi que des labels pour afficher les informations.
 */
public class VBoxInformations extends VBox implements Constantes{
    private final Button boutonSelection = new Button(TITRE_BOUTON_SELECTION);
    private final Button boutonHeuristique = new Button(TITRE_BOUTON_HEURISTIQUE);
    private final Button boutonReset = new Button(TITRE_BOUTON_RESET);
    private final Label labJouer = new Label("Nb Pas");
    private final Label labNbJouer = new Label("0");
    private final Label labNbSelection =  new Label("0");
    private final Label labNbSelectionCalcul = new Label("0");
    private final Label labNbHeuris =  new Label("0");
    private final Label labNbHeuristiqueCalcul = new Label("0");
    private final Label labBravo = new Label("");

    /**
     * Constructeur de VBoxInformations. Initialise les composants et les ajoute à la boîte verticale.
     */
    public VBoxInformations() {
        super(100);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));
        boutonHeuristique.setStyle(
                "-fx-background-color: BLACK;"+
                        "-fx-text-fill: WHITE"
        );
        boutonSelection.setStyle(
                "-fx-background-color: BLACK;"+
                        "-fx-text-fill: WHITE"
        );
        boutonReset.setStyle(
                "-fx-background-color: BLACK;"+
                        "-fx-text-fill: WHITE"
        );

        Separator separator1 = new Separator();
        gridPane.add(separator1, 0, 16, 2, 1);

        gridPane.add(labJouer, 0, 18);
        gridPane.add(labNbJouer, 1, 18);

        gridPane.add(boutonHeuristique, 0, 20);
        gridPane.add(new Label("NB Déplacements"), 0, 21);
        gridPane.add(labNbHeuris, 1, 21);
        gridPane.add(new Label("Déplacements Min"), 0, 22);
        gridPane.add(labNbHeuristiqueCalcul, 1, 22);

        gridPane.add(boutonSelection, 0, 24);
        gridPane.add(new Label("NB Déplacements"), 0, 25);
        gridPane.add(labNbSelection, 1, 25);
        gridPane.add(new Label("Déplacements Min"), 0, 26);
        gridPane.add(labNbSelectionCalcul, 1, 26);

        Separator separator2 = new Separator();
        gridPane.add(separator2, 0, 28, 2, 1);

        labBravo.setId("bravo");
        labBravo.setStyle(
                "-fx-text-fill: white"
        );
        boutonHeuristique.setUserData(TITRE_BOUTON_HEURISTIQUE);
        boutonSelection.setUserData(TITRE_BOUTON_SELECTION);
        boutonReset.setUserData(TITRE_BOUTON_RESET);
        boutonReset.setOnAction(HBoxRoot.getControleur());
        boutonSelection.setOnAction(HBoxRoot.getControleur());
        boutonHeuristique.setOnAction(HBoxRoot.getControleur());

        getChildren().addAll(gridPane, labBravo, boutonReset);
    }

    /**
     * Met à jour le nombre de déplacements pour le tri.
     * @param nbDeplTri Le nombre de déplacements pour le tri à afficher.
     */
    public void setNbDeplacementsSelection(int nbDeplTri) {
        labNbSelection.setText(Integer.toString(nbDeplTri));
    }

    /**
     * Met à jour le nombre de déplacements pour l'heuristique.
     * @param nbDeplHeuris Le nombre de déplacements pour l'heuristique à afficher.
     */
    public void setNbDeplacementsHeuristique(int nbDeplHeuris) {
        labNbHeuris.setText(Integer.toString(nbDeplHeuris));
    }

    /**
     * Met à jour le nombre de déplacements pour le mode manuel.
     * @param nbDepl Le nombre de déplacements pour le mode manuel à afficher.
     */
    public void setNbDeplacementsJouer(int nbDepl) {
        labNbJouer.setText(Integer.toString(nbDepl));
    }

    /**
     * Met à jour le nombre de déplacements calculés pour l'heuristique.
     * @param nbDeplHeuris Le nombre de déplacements calculés pour l'heuristique à afficher.
     */
    public void setNbDeplacementsHeuristiqueCalcul(int nbDeplHeuris) {
        labNbHeuristiqueCalcul.setText(Integer.toString(nbDeplHeuris));
    }

    /**
     * Met à jour le nombre de déplacements calculés pour le tri.
     * @param nbDeplHeuris Le nombre de déplacements calculés pour le tri à afficher.
     */
    public void setNbDeplacementsSelectionCalcul(int nbDeplHeuris) {
        labNbSelectionCalcul.setText(Integer.toString(nbDeplHeuris));
    }

    /**
     * Met à jour les nombres de déplacements calculés pour l'heuristique et le tri.
     * @param nbHeuris Le nombre de déplacements calculés pour l'heuristique à afficher.
     * @param nbTri Le nombre de déplacements calculés pour le tri à afficher.
     */
    public void setLabelNbCalcul(int nbHeuris, int nbTri) {
        labNbHeuristiqueCalcul.setText(Integer.toString(nbHeuris));
        labNbSelectionCalcul.setText(Integer.toString(nbTri));
    }

    /**
     * Affiche une information spécifique dans le label de bravo.
     * @param info L'information à afficher.
     */
    public void setInfo(String info) {
        labBravo.setText(info);
    }
}
