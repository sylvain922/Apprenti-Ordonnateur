package test;

import modele.Position;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test pour la classe {@link Position}.
 */
public class PositionTest {

    /**
     * Test du constructeur et des getters de la classe {@link Position}.
     */
    @Test
    public void testConstructeurEtGetters() {
        int abscisse = 2;
        int ordonnee = 3;
        Position position = new Position(abscisse, ordonnee);

        assertEquals(abscisse, position.getChAbscisse());
        assertEquals(ordonnee, position.getChOrdonnee());
        assertEquals(0, position.getChNbDeplacements());
    }

    /**
     * Test des setters de la classe {@link Position}.
     */
    @Test
    public void testSetters() {
        Position position = new Position(0, 0);

        int nouveauNbDepl = 5;
        position.setNbDeplacemenst(nouveauNbDepl);
        assertEquals(nouveauNbDepl, position.getChNbDeplacements());
    }

    /**
     * Test de la méthode {@link Position#deplacementUneCase(Position)}.
     */
    @Test
    public void testDeplUneCase() {
        Position position = new Position(0, 0);
        Position nouvellePosition = new Position(1, 0);

        position.deplacementUneCase(nouvellePosition);
        assertEquals(nouvellePosition, position);

        assertEquals(1, position.getChNbDeplacements());
    }

    /**
     * Test de la méthode {@link Position#distance(Position)}.
     */
    @Test
    public void testDistance() {
        Position position1 = new Position(0, 0);
        Position position2 = new Position(3, 4);

        assertEquals(7, position1.distance(position2));
    }

    /**
     * Test de la méthode {@link Position#memePosition(Position)}.
     */
    @Test
    public void testMemePosition() {
        Position position1 = new Position(0, 0);
        Position position2 = new Position(0, 0);
        Position position3 = new Position(1, 1);

        assertTrue(position1.memePosition(position2));
        assertFalse(position1.memePosition(position3));
    }
}