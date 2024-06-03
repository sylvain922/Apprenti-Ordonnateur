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
            Scanner sc = new Scanner(new File("scenarios" + File.separator + parNomFich)); //.useDelimiter(",\\s*");
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
            //System.out.println("nb pas heuris : " + calculerNbPas(chListeHeuristique) + ", tri : " + calculerNbPas(chListeTriSelection));

        } catch (FileNotFoundException ex){
            System.err.println("FileNotFoundException");
            System.exit(-1);
        } catch (InputMismatchException e) {
            System.err.println("InputMismatchException");
            System.exit(-1);
        }
    }
