import vue.Constantes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LectureScenario implements Constantes {
    ArrayList<Temple> chTemples = new ArrayList<>();
    Apprenti chApprenti = new Apprenti(POS_APPRENTI, 0);
    ArrayList <Position> chListeHeuristique = new ArrayList<>();
    ArrayList <Position> chListeTriSelection = new ArrayList<>();


    public LectureScenario(String parNomFich) {
        try {
            Scanner sc = new Scanner(new File("scenarios" + File.separator + parNomFich));
            while (sc.hasNext()) {
                int x = sc.nextInt() + NB_L/2;
                int y = sc.nextInt() + NB_H/2;
                int coul =sc.nextInt();
                int coulCristal = sc.nextInt();
                Temple temple = new Temple(new Position(x, y), coul, coulCristal);
                chTemples.add(temple);
            }
            chListeHeuristique = heuristique();
            chListeTriSelection = triSelection();

        } catch (FileNotFoundException ex){
            System.err.println("FileNotFoundException");
            System.exit(-1);
        } catch (InputMismatchException e) {
            System.err.println("InputMismatchException");
            System.exit(-1);
        }
    }
    
    public String toString() {
        String str = "";
        for (Temple t : chTemples) {
            str += t.toString();
        }
        return str;
    }

    public ArrayList <Temple> getTemples() {
        return chTemples;
    }

    public Apprenti getApprenti() {
        return chApprenti;
    }
    
    public Temple templeLePlusPresApprenti() {
        int distanceMin = 100;
        Temple templeMin = chTemples.get(0);
        for (Temple t : chTemples) {
            if (chApprenti.getPosition().distance(t.getPosition()) < distanceMin) {
                distanceMin = chApprenti.getPosition().distance(t.getPosition());
                templeMin = t;
            }
        }
        return templeMin;
    }

    public Temple getTemple(int couleurTemple) {
        for (Temple t : chTemples) {
            if (t.getCouleur() == couleurTemple)
                return t;
        }
        return null;
    }

    public Temple getTemple(Position pos) {
        for (Temple t : chTemples) {
            if (pos.estMemePosition(t.getPosition()))
                return t;
        }
        return null;
    }

    private int indexMinimum(int []tab, int deb, int fin) {
        int iMin = deb;
        for (int i = deb+1; i <= fin; i++) {
            if (tab[i] < tab[iMin])
                iMin = i;
        }
        return iMin;
    }

    public ArrayList<Position> triSelection() {
        ArrayList<Position> listePosTemple = new ArrayList<>();
        Temple tabTemples [] = new Temple[chTemples.size()];
        int tabCoulCristal[] = new int[chTemples.size()];
        int i = 0;
        for (Temple t : chTemples) {
            tabTemples[i++] = t;
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

    public ArrayList <Position> heuristique() {
        ArrayList <Position> listePosTemple = new ArrayList<>();
        Temple templeProchain = templeLePlusPresApprenti();
        for (int i = 0; i < (chTemples.size()+1); i++) {
            listePosTemple.add(templeProchain.getPosition());
            int coulCristal = templeProchain.getCouleurCristal();
            templeProchain = getTemple(coulCristal);
        }
        return listePosTemple;
    }

    public int calculerNbPas(ArrayList <Position> listePos) {
        int pas = chApprenti.getPosition().distance(listePos.get(0));
        for (int i = 0; i < (listePos.size()-1); i++) {
            pas += listePos.get(i).distance(listePos.get(i+1));
        }
        return pas;
    }

    public int calculerNbPasTriSelection() {
        return calculerNbPas(chListeTriSelection);
    }

    public int calculerNbPasHeuristique() {
        return calculerNbPas(chListeHeuristique);
    }

    public ArrayList <Position> getListeHeuristique() {
        return chListeHeuristique;
    }

    public ArrayList <Position> getChListeTriSelection() {
        return chListeTriSelection;
    }
}
