package vue;

import controleur.Controleur;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import modele.LectureScenario;
import java.io.File;

/**
 * Classe HBoxRoot qui représente la racine de l'interface graphique.
 * Elle contient les éléments principaux de l'application, tels que le menu,
 * le canvas pour l'affichage du jeu, et les informations de l'interface utilisateur.
 */
public class HBoxRoot extends HBox implements Constantes {

    /** VBox contenant les informations à droite de l'interface. */
    private static VBoxInformations chVboxInfo ;

    /** Canvas pour dessiner la carte du jeu. */
    private static CanvasCartes chCanvasMap ;

    /** Contrôleur pour gérer les interactions de l'utilisateur. */
    private static Controleur chControleur;

    /** Scénario actuel du jeu. */
    private static LectureScenario chScenario;

    /** Nom du fichier du scénario. */
    private static String chNomFich = "";

    /**
     * Constructeur de HBoxRoot. Initialise les composants de l'interface graphique.
     */
    public HBoxRoot() {
        super(30);
        File css = new File("css" + File.separator + "sae.css");
        HBoxRoot.this.getStylesheets().add(css.toURI().toString());

        HBoxRoot.this.setStyle(
                "-fx-background-color: #353535"
        );

        Menu menuFile = new Menu("Scenarios");
        menuFile.setStyle(
                "-fx-background-color: BLACK;"
        );
        ToggleGroup toggleGroup = new ToggleGroup();

        File[] fileScenario = new File("scenarios").listFiles();
        int nbItem = 1;
        for (File file : fileScenario) {
            RadioMenuItem item = new RadioMenuItem(file.getName());
            item.setStyle(
                    "-fx-background-color: BLACK;"
            );
            item.setUserData(file);
            item.setToggleGroup(toggleGroup);
            if (nbItem == 1) {
                item.setSelected(true);
                chNomFich = file.getName();
            }
            item.setOnAction(evt->{
                chNomFich = file.getName();
                chScenario = new Scenario(chNomFich);
                chCanvasMap.resetPlateau(chScenario);
                setLabelsNbDeplacementsVBoxInfos();
            });
            menuFile.getItems().add(item);
            nbItem++;
        }
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle(
                "-fx-background-color: BLACK;"
        );
        menuBar.getMenus().add(menuFile);

        chControleur = new Controleur();
        chVboxInfo = new VBoxInformations();
        chScenario = new LectureScenario(chNomFich);
        chCanvasMap = new CanvasCartes();
        getChildren().addAll(menuBar, chCanvasMap,  chVboxInfo);
        setLabelsNbDeplacementsVBoxInfos();
    }

    /**
     * Met à jour les labels dans VBoxInformations avec le nombre de pas calculé.
     */
    public void setLabelsNbDeplacementsVBoxInfos() {
        int nbHeuristique = getScenario().calculerNbPasHeuristique();
        int nbSelection = getScenario().calculerNbPasTriSelection();
        chVboxInfo.setLabelNbCalcul(nbHeuristique, nbSelection);
    }

    /**
     * Retourne la VBox contenant les informations.
     * @return VBoxInformations contenant les informations.
     */
    public static VBoxInformations getVBoxInfo() {
        return chVboxInfo;
    }

    /**
     * Retourne le Canvas pour dessiner la carte du jeu.
     * @return CanvasMap pour dessiner la carte.
     */
    public static CanvasCartes getCarteCanvas() {
        return chCanvasMap;
    }

    /**
     * Retourne le scénario actuel.
     * @return Scenario actuel.
     */
    public static LectureScenario getScenario() {
        return chScenario;
    }

    /**
     * Retourne le contrôleur pour gérer les interactions de l'utilisateur.
     * @return Controleur de l'application.
     */
    public static Controleur getControleur() {
        return chControleur;
    }

    /**
     * Crée un nouveau scénario basé sur le fichier de scénario sélectionné.
     */
    public static void creerScenario() {
        chScenario = new Scenario(chNomFich);
    }
}
