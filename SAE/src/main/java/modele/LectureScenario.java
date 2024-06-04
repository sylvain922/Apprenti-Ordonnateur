package modele;

import vue.Constantes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * La classe Scenario représente un scénario avec des temples et un apprenti.
 * Elle permet de charger un scénario à partir d'un fichier, de calculer les temples non alignés,
 * et de déterminer des chemins basés sur des heuristiques et un tri par sélection.
 */
public class Scenario implements Constantes {
    ArrayList<Temple> chTemples = new ArrayList<>();
    Apprenti chApprenti = new Apprenti(POSITION_APPRENTI, 0);
    ArrayList <Position> chListeHeuristique = new ArrayList<>();
    ArrayList <Position> chListeTriSelection = new ArrayList<>();

    /**
     * Constructeur de la classe Scenario.
     *
     * @param parNomFichier Le nom du fichier contenant les données du scénario.
     */
    public Scenario(String parNomFichier) {
        try {
            Scanner scanner = new Scanner(new File("scenarios" + File.separator + parNomFichier)); //.useDelimiter(",\\s*");
            while (scanner.hasNext()) {
                int x = scanner.nextInt() + LARGEUR/2;
                int y = scanner.nextInt() + HAUTEUR/2;
                int coul =scanner.nextInt();
                int coulCristal = scanner.nextInt();
                Temple temple = new Temple(new Position(x, y), coul, coulCristal);
                chTemples.add(temple);
            }
            chListeHeuristique = heuristique();
            chListeTriSelection = triSelection();

        } catch (FileNotFoundException ex){
            System.err.println("Fichier Introuvable");
            System.exit(-1);
        } catch (InputMismatchException e) {
            System.err.println("Saisie Incompatible");
            System.exit(-1);
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du scénario.
     *
     * @return Une chaîne de caractères représentant le scénario.
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Temple temple : chTemples) {
            string.append(temple.toString()).append("\n");
        }
        return string.toString();
    }

    /**
     * Retourne la liste des temples.
     *
     * @return La liste des temples.
     */
    public ArrayList <Temple> getTemples() {
        return chTemples;
    }

    /**
     * Retourne l'apprenti du scénario.
     *
     * @return L'apprenti.
     */
    public Apprenti getApprenti() {
        return chApprenti;
    }

    /**
     * Vérifie si tous les temples sont alignés.
     *
     * @return true si tous les temples sont alignés, false sinon.
     */
    public boolean estAligne() {
        for (Temple t : chTemples) {
            if (t.estAligne())
                return false;
        }
        return true;
    }

    /**
     * Trouve le temple le plus proche de l'apprenti parmi les temples non alignés.
     *
     * @return Le temple le plus proche.
     */
    public Temple templeLePlusPres() {
        int distanceMin = 100;
        Temple templeMin = chTemples.get(0);
        for (Temple temple : chTemples) {
            if (chApprenti.getPosition().distance(temple.getPosition()) < distanceMin) {
                distanceMin = chApprenti.getPosition().distance(temple.getPosition());
                templeMin = temple;
            }
        }
        return templeMin;
    }

    /**
     * Trouve un temple par sa couleur parmi les temples non alignés.
     *
     * @param couleurTemple La couleur du temple recherché.
     * @return Le temple correspondant à la couleur, ou null s'il n'est pas trouvé.
     */
    public Temple getTemple(int couleurTemple) {
        for (Temple temple : chTemples) {
            if (temple.getCouleur() == couleurTemple)
                return temple;
        }
        return null;
    }

    /**
     * Trouve un temple par sa position.
     *
     * @param parPosition La position du temple recherché.
     * @return Le temple correspondant à la position, ou null s'il n'est pas trouvé.
     */
    public Temple getTemple(Position parPosition) {
        for (Temple temple : chTemples) {
            if (parPosition.memePosition(temple.getPosition()))
                return temple;
        }
        return null;
    }

    /**
     * Trouve l'indice du minimum dans un tableau entre deux indices donnés.
     *
     * @param tab   Le tableau à parcourir.
     * @param debut L'indice de début.
     * @param fin   L'indice de fin.
     * @return L'indice du minimum.
     */
    private int indexMinimum(int []tab, int debut, int fin) {
        int iMin = debut;
        for (int i = debut+1; i <= fin; i++) {
            if (tab[i] < tab[iMin])
                iMin = i;
        }
        return iMin;
    }

    /**
     * Tri les positions des temples par sélection en fonction de leur couleur de cristal.
     *
     * @return Une liste des positions des temples triées.
     */
    public ArrayList<Position> triSelection() {
        ArrayList<Position> listePosTemple = new ArrayList<>();
        Temple[] tabTemples;
        tabTemples = new Temple[chTemples.size()];
        int[] tabCoulCristal;
        tabCoulCristal = new int[chTemples.size()];
        int i = 0;
        for (Temple temple : chTemples) {
            tabTemples[i++] = temple;
        }
        Arrays.sort(tabTemples);
        for (i = 0; i < chTemples.size(); i++)
            tabCoulCristal[i] = tabTemples[i].getCouleurCristal();

        for (i = 0; i < chTemples.size(); i++) {
            int iMin = indexMinimum(tabCoulCristal, i, chTemples.size()-1);
            if (iMin == i)
                continue;

            int tmp = tabCoulCristal[i];
            tabCoulCristal[i] = tabCoulCristal[iMin];
            tabCoulCristal[iMin] = tmp;

            listePosTemple.add(tabTemples[i].getPosition());
            listePosTemple.add(tabTemples[iMin].getPosition());
            listePosTemple.add(tabTemples[i].getPosition());
        }
        return  listePosTemple;
    }

    /**
     * Calcule un chemin heuristique pour visiter les temples non alignés.
     *
     * @return Une liste des positions des temples suivant l'ordre heuristique.
     */
    public ArrayList <Position> heuristique() {
        ArrayList <Position> listePosTemple = new ArrayList<>();
        Temple templeProchain = templeLePlusPres();
        for (int i = 0; i < (chTemples.size()+1); i++) {
            listePosTemple.add(templeProchain.getPosition());
            int couleurCristal = templeProchain.getCouleurCristal();
            templeProchain = getTemple(couleurCristal);
        }
        return listePosTemple;
    }

    /**
     * Calcule le nombre de pas nécessaires pour parcourir une liste de positions.
     *
     * @param listePos La liste des positions à parcourir.
     * @return Le nombre de pas nécessaires.
     */
    public int calculerNbPas(ArrayList <Position> listePos) {
        int pas = chApprenti.getPosition().distance(listePos.get(0));
        for (int i = 0; i < (listePos.size()-1); i++) {
            pas += listePos.get(i).distance(listePos.get(i+1));
        }
        return pas;
    }

    /**
     * Calcule le nombre de pas nécessaires pour parcourir la liste de positions triée par sélection.
     *
     * @return Le nombre de pas nécessaires.
     */
    public int calculerNbPasTriSelection() {
        return calculerNbPas(chListeTriSelection);
    }

    /**
     * Calcule le nombre de pas nécessaires pour parcourir la liste de positions heuristique.
     *
     * @return Le nombre de pas nécessaires.
     */
    public int calculerNbPasHeuristique() {
        return calculerNbPas(chListeHeuristique);
    }

    /**
     * Retourne la liste des positions des temples suivant l'ordre heuristique.
     *
     * @return La liste des positions heuristiques.
     */
    public ArrayList <Position> getListeHeuristique() {
        return chListeHeuristique;
    }

    /**
     * Retourne la liste des positions des temples triée par sélection.
     *
     * @return La liste des positions triée par sélection.
     */
    public ArrayList <Position> getChListeTriSelection() {
        return chListeTriSelection;
    }
}
