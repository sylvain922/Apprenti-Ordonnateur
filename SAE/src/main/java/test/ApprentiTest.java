package test;

import modele.Apprenti;
import modele.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Classe de test pour la classe {@link Apprenti}.
 */
public class ApprentiTest {

    /**
     * Test du constructeur et des getters de la classe {@link Apprenti}.
     */
    @Test
    public void testConstructeurEtGetters() {
        Position position = new Position(0, 0);
        int couleurCristal = 1;
        Apprenti apprenti = new Apprenti(position, couleurCristal);

        assertEquals(position, apprenti.getPosition());
        assertEquals(couleurCristal, apprenti.getCouleurCristal());
    }

    /**
     * Test des setters de la classe {@link Apprenti}.
     */
    @Test
    public void testSetters() {
        Apprenti apprenti = new Apprenti(new Position(0, 0), 1);

        Position nouvellePosition = new Position(2, 3);
        apprenti.setPosition(nouvellePosition);
        assertEquals(nouvellePosition, apprenti.getPosition());

        int nouvelleCouleurCristal = 2;
        apprenti.setCouleurCristal(nouvelleCouleurCristal);
        assertEquals(nouvelleCouleurCristal, apprenti.getCouleurCristal());
    }

    /**
     * Test de la méthode {@link Apprenti#toString()}.
     */
    @Test
    public void testToString() {
        Position position = new Position(0, 0);
        int couleurCristal = 1;
        Apprenti apprenti = new Apprenti(position, couleurCristal);

        String attendu = "Position: (0, 0), Couleur du cristal: 1";
        assertEquals(attendu, apprenti.toString());
    }
}