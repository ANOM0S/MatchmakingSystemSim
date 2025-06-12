package ranked.sim.simulation;

import java.util.Random;

/**
 * MatchSimulator to klasa odpowiedzialna za symulację meczu pomiędzy dwoma drużynami.
 * Symulacja opiera się na sile drużyn, która jest obliczana na podstawie ich graczy.
 * Wynik meczu jest losowany na podstawie proporcji siły drużyn.
 */

public class MatchSimulator {
    private final Random random = new Random();

    /**
     * Symuluje mecz pomiędzy dwiema drużynami.
     *
     * @param match Mecz do symulacji, zawierający drużyny i ich siłę.
     * @return Wynik meczu, który może być TEAM_A_WIN lub TEAM_B_WIN.
     */
    public MatchResult simulateMatch(Match match) {
        double powerA = match.getTeamA().getTeamPower();
        double powerB = match.getTeamB().getTeamPower();

        double total = powerA + powerB;
        double chanceA = powerA / total;

        boolean aWins = random.nextDouble() < chanceA;
        MatchResult result = aWins ? MatchResult.TEAM_A_WIN : MatchResult.TEAM_B_WIN;

        match.setResult(result);
        return result;
    }
}
