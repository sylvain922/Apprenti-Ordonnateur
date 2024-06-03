package modele;

public class Temple implements Comparable <Temple> {
    private Position chPosition;
    private int chCouleur, chCouleurCristal;

    public Temple(Position parPos, int parCoul, int parCoulCristal) {
        chPosition = new Position(parPos.getPosX(), parPos.getPosY());
        chCouleur = parCoul;
        chCouleurCristal = parCoulCristal;
    }

    public String toString() {
        return chPosition.toString() + ", " + chCouleur + ", " + chCouleurCristal;
    }

    public Position getPosition() {
        return chPosition;
    }

    public int getCouleur() {
        return chCouleur;
    }

    public int getCouleurCristal() {
        return chCouleurCristal;
    }

    public void setCouleurCristal(int parCoul) {
        chCouleurCristal = parCoul;
    }

    public boolean estAligne() {
        return chCouleurCristal == chCouleur;
    }

    public int compareTo(Temple parTemple) {
        return chCouleur - parTemple.chCouleur;
    }
}
