package test;

import modele.Position;
import modele.Temple;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Classe de test pour la classe {@link Temple}.
 */
public class TempleTest {

    /**
     * Test du constructeur et des méthodes principales de la classe {@link Temple}.
     */
    @Test
    public void testTemple() {
        Position position = new Position(1, 1);
        int couleur = 1;
        int couleurCristal = 2;

        Temple temple = new Temple(position, couleur, couleurCristal);

        assertNotNull(temple);

        Position templePosition = temple.getPosition();
        assertNotNull(templePosition);
        assertEquals(1, templePosition.getChAbscisse());
        assertEquals(1, templePosition.getChOrdonnee());

        int templeCouleur = temple.getCouleur();
        assertEquals(1, templeCouleur);

        int templeCouleurCristal = temple.getCouleurCristal();
        assertEquals(2, templeCouleurCristal);

        temple.setCouleurCristal(3);
        assertEquals(3, temple.getCouleurCristal());

        boolean aligne = temple.estAligne();
        assertTrue(aligne);

        // Test de la méthode compareTo
        Temple autreTemple = new Temple(new Position(2, 2), 2, 2);
        assertEquals(-1, temple.compareTo(autreTemple));
    }
}