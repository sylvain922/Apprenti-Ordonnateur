package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LectureScenario implements ConstanteCanvas {
    public static ArrayList<Temples> templesDuScenario = new ArrayList<>();

    public static ArrayList<Temples> lecture(File fichierScenario) {
        try {
            //Scanner scanner = new Scanner(fichierScenario);
            Scanner scanner = new Scanner(new File("scenarios"+File.separator +"scenario1.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                int posX = lineScanner.nextInt() + LARGEUR_CANVAS / (2 * CARRE);
                int posY = lineScanner.nextInt() + HAUTEUR_CANVAS / (2 * CARRE);
                int couleur = lineScanner.nextInt();
                int cristal = lineScanner.nextInt();

                Temples temple = new Temples(new Position(posX, posY), couleur, cristal);
                templesDuScenario.add(temple);

                lineScanner.close();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return templesDuScenario;
    }

    public static ArrayList<Temples> getTemples() {
        return templesDuScenario;
    }
}