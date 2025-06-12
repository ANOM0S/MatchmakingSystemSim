package ranked.sim;

import org.junit.jupiter.api.Test;
import ranked.sim.model.*;
import ranked.sim.simulation.Match;
import ranked.sim.simulation.MatchResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy jednostkowe dla klasy Player.
 * Sprawdzają reset emocji po odpoczynku oraz wzrost statystyk po wygranej.
 * Wymagają poprawnej implementacji metod w Player i Match.
 */

public class playerTest {

    /**
     * Test sprawdza, czy emocje gracza resetują się po odpoczynku.
     * Zakłada, że odpoczynek resetuje stan emocjonalny gracza.
     */
    @Test
    public void testEmotionResetsAfterResting() {
        Player p = new Player("Test", new Stats(10, 10, 10), new Rank(820, RankName.Silver, 1000), Strategy.ONE_QUIT);
        p.setEmotion(EmotionState.TILTED);

        p.rest(); // bez sprawdzania willPlay

        assertNotEquals(EmotionState.TILTED, p.getEmotionState());
    }

    /**
     * Test sprawdza, czy statystyki gracza rosną po wygranej.
     * Zakłada, że wygrana zwiększa statystyki gracza.
     */
    @Test
    public void testPlayerStatGrowthAfterWin() {
        Player p = new Player("Test", new Stats(10, 10, 10), new Rank(820, RankName.Silver, 1000), Strategy.SPAMMING);
        Match match = new Match(new Team(List.of(p)), new Team(List.of(new Player("Fake", new Stats(5, 5, 5), new Rank(820, RankName.Silver, 1000), Strategy.SPAMMING))));
        ranked.sim.simulation.MatchResult result = MatchResult.TEAM_A_WIN;

        p.updateAfterMatch(result, match);

        assertTrue(p.getStats().getAttack() > 10.0);
    }
}
