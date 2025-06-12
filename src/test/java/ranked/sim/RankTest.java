package ranked.sim;

import org.junit.jupiter.api.Test;
import ranked.sim.model.Rank;
import ranked.sim.model.RankName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy jednostkowe dla klasy Rank.
 * Sprawdzają, czy MMR i punkty rankingu są aktualizowane poprawnie po meczu.
 */
public class RankTest {

    /**
     * Testuje, czy MMR i punkty rankingu są poprawnie zwiększane po wygranej.
     * Sprawdza, czy MMR wzrasta i punkty są dodatnie.
     */
    @Test
    public void testMMRAndPointsIncreaseAfterWin() {
        Rank rank = new Rank(820, RankName.Silver, 1000);
        double oldMMR = rank.getMMR();

        rank.updateAfterMatch(true, 1000);

        assertTrue(rank.getMMR() > oldMMR);
        assertTrue(rank.getOverallPoints() > 0);
    }

    /**
     * Testuje, czy MMR i punkty rankingu są poprawnie zmniejszane po przegranej.
     * Sprawdza, czy MMR maleje i punkty są dodatnie.
     */
    @Test
    public void testMMRDecreaseAfterLoss() {
        Rank rank = new Rank(820, RankName.Silver, 1000);
        double oldMMR = rank.getMMR();

        rank.updateAfterMatch(false, 1000);

        assertTrue(rank.getMMR() < oldMMR);
    }

    /**
     * Testuje, czy MMR i punkty rankingu są poprawnie aktualizowane i nie spadają poniżej zera.
     * Sprawdza, czy punkty rankingu nie są ujemne po wielu stratach.
     */
    @Test
    public void testNoNegativeRankPoints() {
        Rank rank = new Rank(820, RankName.Silver, 1000);
        for (int i = 0; i < 100; i++) {
            rank.updateAfterMatch(false, 1000); // strata
        }

        assertTrue(rank.getOverallPoints() >= 0); // nie może spaść poniżej 0
    }
}
