package test;

import modele.Apprenti;
import modele.Position;
import modele.Scenario;
import modele.Temple;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Classe de test pour la classe {@link Scenario}.
 */
public class ScenarioTest {

    /**
     * Test du constructeur et des méthodes principales de la classe {@link Scenario}.
     */
    @Test
    public void testScenario() {
        Scenario scenario = new Scenario("test_scenario.txt");

        assertNotNull(scenario);

        ArrayList<Temple> temples = scenario.getTemples();
        assertNotNull(temples);
        assertEquals(3, temples.size());

        Apprenti apprenti = scenario.getApprenti();
        assertNotNull(apprenti);

        boolean aligne = scenario.estAligne();
        assertFalse(aligne);

        Temple templeProche = scenario.templeLePlusPres();
        assertNotNull(templeProche);

        Temple templeByColor = scenario.getTemple(1);
        assertNotNull(templeByColor);

        Temple templeByPosition = scenario.getTemple(new Position(0, 0));
        assertNotNull(templeByPosition);

        ArrayList<Position> listeHeuristique = scenario.getListeHeuristique();
        assertNotNull(listeHeuristique);
        assertEquals(4, listeHeuristique.size());

        ArrayList<Position> listeTriSelection = scenario.getChListeTriSelection();
        assertNotNull(listeTriSelection);
        assertEquals(9, listeTriSelection.size());

        int pasHeuristique = scenario.calculerNbPasHeuristique();
        assertEquals(6, pasHeuristique);

        int pasTriSelection = scenario.calculerNbPasTriSelection();
        assertEquals(12, pasTriSelection);
    }
}