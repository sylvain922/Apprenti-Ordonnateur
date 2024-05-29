package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Temples implements ConstanteCanvas {
    private Position chPosTemple;
    private int chCouleurTemple;
    private int chCouleurCristal;

    public Temples(Position parPosTemple, int parCouleurTemple, int parCouleurCristal) {
        chPosTemple = parPosTemple;
        chCouleurTemple = parCouleurTemple;
        chCouleurCristal = parCouleurCristal;
    }

    public Position getPos() { return chPosTemple; }
    public void setChCouleurTemple(int couleurTemple){chCouleurTemple = couleurTemple;}
    public void setChCouleurCristal(int couleurCristal){chCouleurCristal = couleurCristal;}
    public String getCouleurTemple() { return LIST_COULEURS[chCouleurTemple];}
    public String getCouleurCristal() { return LIST_COULEURS[chCouleurCristal];}
}