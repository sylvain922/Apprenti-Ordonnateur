package vue;

import controleur.Controleur;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import modele.LectureScenario;

import java.io.File;

public class HBoxRoot extends HBox implements Constantes {
    private static VBoxInformations vueInfos;
    private static CanvasCartes vueCarte;
    private static Controleur controleur;
    private static LectureScenario scenario;
    private static String chNomFich = "";

    public HboxRoot(){
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
        //String fileSelected = "";
        for (File file : fileScenario) {
            //Scanner scanner = new Scanner(file.getName()).useDelimiter("\\.");
            RadioMenuItem item = new RadioMenuItem(file.getName());             // nom sans ".txt"
            item.setStyle(
                    "-fx-background-color: BLACK;"
            );
            item.setUserData(file);
            item.setToggleGroup(toggleGroup);
            if (nbItem == 1) {              // 1er fich est choisi par defaut
                item.setSelected(true);
                chNomFich = file.getName();
            }
            item.setOnAction(evt->{
                chNomFich = file.getName();
                scenario = new Scenario(chNomFich);
                //chCarteCanvas.setNomFich(nomFich);
                vueCarte.resetPlateau(scenario);
                setLabelsNbDeplBoxDroit();
            });
            menuFile.getItems().add(item);
            nbItem++;
        }
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle(
                "-fx-background-color: BLACK;"
        );
        menuBar.getMenus().add(menuFile);
        
        controleur = new Controleur();
        vueInfos = new VBoxInformations();
        scenario = new LectureScenario(chNomFich);
        vueCarte = new CanvasCartes();
        getChildren().addAll(menuBar, vueCarte, vueInfos);
        setLabelsNbDeplBoxDroit();
    }

    public static VBoxInformations getVBoxInfo() {return vueInfos;}

    public static CanvasCartes getCanvasCartes() {return vueCarte;}

    public static LectureScenario getScenario() {return scenario;}

    public static String getNomFich() {return chNomFich;}
    
    public static Controleur getControleur() {return controleur;}

    public static void creerScenario() {scenario = new Scenario(chNomFich);}
}
