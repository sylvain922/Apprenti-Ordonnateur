package modele;

import java.util.Collection;

public class Apprenti implements ConstanteCanvas{
    private Collection<Temples> temples;
    private Position chPosApprenti;
    private int chCristal;

    public Apprenti() {
        chPosApprenti = new Position((NB_CARRE_HAUTEUR/2),(NB_CARRE_LARGEUR/2));
    }

    public Position getPos() {return chPosApprenti;}
    public void setPos(Position pos) {chPosApprenti = pos;}
    public String getCristal() {return LIST_COULEURS[chCristal];}
    public void setCristal(int cristal) {chCristal = cristal;}

    public void ramasserCristal(int couleur){
        this.chCristal = couleur;
    }

    public void deposerCristal(){
        this.chCristal = Integer.parseInt(null);
    }
    public void setTemples(Collection<Temples> temples) {
        this.temples = temples;
    }
}