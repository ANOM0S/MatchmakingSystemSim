package ranked.sim;

import org.junit.jupiter.api.Test;
import ranked.sim.model.Stats;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testy jednostkowe dla klasy Stats.
 * Sprawdzają, czy statystyki zwiększają się poprawnie po zastosowaniu adjust.
 */
public class statsTest {

    /**
     * Testuje, czy statystyki zwiększają się poprawnie po zastosowaniu adjust.
     * Sprawdza, czy wartości ataku, obrony i combo są większe niż początkowe wartości.
     */
    @Test
    public void testStatsIncreaseProperly() {
        Stats stats = new Stats(10, 10, 10);
        stats.adjust(1.1);

        assertTrue(stats.getAttack() > 10);
        assertTrue(stats.getDefense() > 10);
        assertTrue(stats.getCombo() > 10);
    }

}
