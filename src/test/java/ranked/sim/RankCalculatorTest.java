package ranked.sim;

import org.junit.jupiter.api.Test;
import ranked.sim.logic.RankCalculator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy jednostkowe dla RankCalculator.
 * Sprawdzają, czy obliczenia MMR działają zgodnie z oczekiwaniami.
 */

public class RankCalculatorTest {

    /**
     * Sprawdza, czy RankCalculator poprawnie oblicza zmianę MMR po wygranej.
     * Zakłada, że wygrana zwiększa MMR.
     */
    @Test
    public void testWinIncreasesMMR() {
        double result = RankCalculator.calculateNewMMR(1000, 1000, true);
        assertTrue(result > 0);
    }

    /**
     * Sprawdza, czy RankCalculator poprawnie oblicza zmianę MMR po przegranej.
     * Zakłada, że przegrana zmniejsza MMR.
     */
    @Test
    public void testLossDecreasesMMR() {
        double result = RankCalculator.calculateNewMMR(1000, 1000, false);
        assertTrue(result < 0);
    }

    /**
     * Sprawdza, czy RankCalculator poprawnie oblicza zmianę MMR dla walki z silniejszym przeciwnikiem.
     * Zakłada, że walka z silniejszym przeciwnikiem zwiększa MMR bardziej niż walka z słabszym.
     */
    @Test
    public void testExpectedMMRChangeAgainstHigherOpponent() {
        double delta = RankCalculator.calculateNewMMR(1000, 1200, true);
        assertTrue(delta > 20); // bo trudniejszy przeciwnik
    }

    /**
     * Sprawdza, czy RankCalculator poprawnie oblicza zmianę MMR dla walki z słabszym przeciwnikiem.
     * Zakłada, że walka z słabszym przeciwnikiem zmniejsza MMR mniej niż walka z silniejszym.
     */
    @Test
    public void testHigherRewardForStrongerOpponent() {
        double deltaVsStronger = RankCalculator.calculateNewMMR(1000, 1300, true);
        double deltaVsWeaker = RankCalculator.calculateNewMMR(1000, 900, true);

        assertTrue(deltaVsStronger > deltaVsWeaker);
    }
}
