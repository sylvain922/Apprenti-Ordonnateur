package modele;

public class Apprenti {
    private Position chPosition;
    private int chCouleurCristal;

    public Apprenti(Position parPosition, int parCouleur) {
        chPosition = new Position(parPosition.getPosX(), parPosition.getPosY());
        chCouleurCristal = parCouleur;
    }

    public String toString() {
        return chPosition.toString() + ", " + chCouleurCristal;
    }
    public Position getPosition() {
        return chPosition;
    }

    public int getCouleurCristal() {
        return chCouleurCristal;
    }

    public void setCouleurCristal(int parCoul) {
        chCouleurCristal = parCoul;
    }

    public void setPosition(Position parPos) {
        chPosition.setPosition(parPos);
    }
}
