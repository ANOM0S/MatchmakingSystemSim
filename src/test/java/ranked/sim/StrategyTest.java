package ranked.sim;

import org.junit.jupiter.api.Test;
import ranked.sim.model.LastOutcome;
import ranked.sim.model.Strategy;
import ranked.sim.model.EmotionState;

import static org.junit.jupiter.api.Assertions.*;

/**
 * StrategyTest to jest klasa testowa, która sprawdza różne strategie gry w symulacji.
 * Każda strategia jest testowana pod kątem tego, czy gracz chce grać w danym epizodzie,
 * w zależności od ostatniego wyniku i stanu emocjonalnego.
 */
public class StrategyTest {

    /**
     * Testuje, czy strategia ONE_QUIT chce grać tylko w jednym meczu na epokę,
     * a następnie odmawia grania w kolejnym meczu.
     */
    @Test
    public void testOneQuitPlaysEveryOtherEpoch() {
        assertTrue(Strategy.ONE_QUIT.wantsToPlay(0, LastOutcome.NONE, EmotionState.NEUTRAL));
        assertFalse(Strategy.ONE_QUIT.wantsToPlay(1, LastOutcome.NONE, EmotionState.NEUTRAL));
    }

    /**
     * Testuje, czy strategia LOSE_QUIT chce grać po wygranej,
     * ale odmawia grania po przegranej.
     */
    @Test
    public void testLoseQuitRefusesAfterLoss() {
        assertTrue(Strategy.LOSE_QUIT.wantsToPlay(2, LastOutcome.WIN, EmotionState.NEUTRAL));
        assertFalse(Strategy.LOSE_QUIT.wantsToPlay(2, LastOutcome.LOSS, EmotionState.NEUTRAL));
    }

    /**
     * Testuje, czy strategia TILL_WIN chce grać po przegranej,
     * ale odmawia grania po wygranej.
     */
    @Test
    public void testTillWinPlaysAfterLoss() {
        assertTrue(Strategy.TILL_WIN.wantsToPlay(2, LastOutcome.LOSS, EmotionState.NEUTRAL));
    }

    /**
     * Testuje, czy strategia TILL_WIN odmawia grania po wygranej.
     */
    @Test
    public void testTillWinSkipsAfterWin() {
        assertFalse(Strategy.TILL_WIN.wantsToPlay(1, LastOutcome.WIN, EmotionState.NEUTRAL));
    }

    /**
     * Testuje, czy strategia SPAMMING będzie zawsze chciała grać,
     * nawet jeśli gracz jest w stanie emocjonalnym TILTED.
     */
    @Test
    public void testSpammerAlwaysPlays() {
        for (int i = 0; i < 10; i++) {
            assertTrue(Strategy.SPAMMING.wantsToPlay(i, LastOutcome.LOSS, EmotionState.TILTED));
        }
    }

    /**
     * Testuje, czy strategia ONE_QUIT odmawia grania po pierwszym meczu,
     * ale chce grać w pierwszym meczu następnej epoki.
     */
    @Test
    public void testOneQuitAlternatesProperly() {
        assertTrue(Strategy.ONE_QUIT.wantsToPlay(0, LastOutcome.NONE, EmotionState.NEUTRAL));
        assertFalse(Strategy.ONE_QUIT.wantsToPlay(1, LastOutcome.NONE, EmotionState.NEUTRAL));
    }
}
