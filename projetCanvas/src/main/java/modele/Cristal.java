package modele;

import java.util.*;

public class Cristal implements ConstanteCanvas{
    public static ArrayList<Cristal> listCristaux = new ArrayList<>();
    private Position chPosCristaux;
    private int chCouleurCristal;

    public Cristal(Position parPosCristaux, int parCouleurCristal) {
        chPosCristaux = parPosCristaux;
        chCouleurCristal = parCouleurCristal;
    }

    public Position getPos () {return chPosCristaux;}
    public String getCouleurCristal() {return LIST_COULEURS[chCouleurCristal];}

    public static void initCristaux() {
        Random rand = new Random();
        ArrayList<Integer> couleursDisponibles = new ArrayList<>();

        for (int i = 0; i < LIST_COULEURS.length; i++) {
            couleursDisponibles.add(i);
            int couleur = couleursDisponibles.get(i);

            // Générez une position aléatoire pour le cristal
            int x = rand.nextInt(NB_CARRE_LARGEUR);
            int y = rand.nextInt(NB_CARRE_HAUTEUR);

            Position randomPosition = new Position(x, y);
            Cristal cristal = new Cristal(randomPosition, couleur);
            listCristaux.add(cristal);
        }
    }
    public static ArrayList<Cristal> getListCristaux() {
        return listCristaux;
    }
}
