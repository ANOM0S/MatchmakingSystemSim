package ranked.sim;

import org.junit.jupiter.api.Test;
import ranked.sim.model.*;
import ranked.sim.simulation.Match;
import ranked.sim.simulation.MatchResult;
import ranked.sim.simulation.MatchSimulator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testy dla MatchSimulator, który symuluje mecze między drużynami.
 * Sprawdzają, czy silniejsza drużyna wygrywa częściej niż słabsza.
 */
public class matchSimulatorTest {
    /**
     * Testuje, czy silniejsza drużyna wygrywa częściej niż słabsza.
     * Tworzy dwie drużyny: jedną silniejszą i jedną słabszą,
     * a następnie symuluje 100 meczów między nimi.
     * Sprawdza, czy silniejsza drużyna wygrała co najmniej 70% meczów.
     */
    @Test
    public void testStrongerTeamWinsMoreOften() {
        MatchSimulator simulator = new MatchSimulator();
        Player strong = new Player("Strong", new Stats(50, 50, 50), new Rank(820, RankName.Silver, 1000), Strategy.SPAMMING);
        Player weak = new Player("Weak", new Stats(10, 10, 10), new Rank(820, RankName.Silver, 1000), Strategy.SPAMMING);

        int wins = 0;
        for (int i = 0; i < 100; i++) {
            Match match = new Match(new Team(List.of(strong)), new Team(List.of(weak)));
            if (simulator.simulateMatch(match) == MatchResult.TEAM_A_WIN) {
                wins++;
            }
        }

        assertTrue(wins > 70); // silniejszy wygrywa w większości
    }
}

