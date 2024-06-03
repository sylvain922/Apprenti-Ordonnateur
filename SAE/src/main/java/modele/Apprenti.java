package modele;

import java.util.Collection;
import java.util.Random;

public class Apprenti implements ConstanteCanvas{
    private Collection<Temples> temples;
    private Position chPosApprenti;
    private int chCristal;

    public Apprenti() {
        chPosApprenti = new Position((NB_CARRE_HAUTEUR/2),(NB_CARRE_LARGEUR/2));
    }

    public Position getPos() {return chPosApprenti;}

    public int getPossedeCristal () {return chCristal;}

    public void possedeCristal(){
        //1 si il en a un et 0 si il en a pas
    }

    public void setTemples(Collection<Temples> temples) {
        this.temples = temples;
    }
}
