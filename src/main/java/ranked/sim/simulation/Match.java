package ranked.sim.simulation;

import ranked.sim.model.Player;
import ranked.sim.model.Team;

/**
 * Match reprezentuje mecz pomiędzy dwoma drużynami.
 * Każdy mecz ma dwie drużyny, wynik oraz metodę do obliczenia średniego MMR przeciwnika dla danego gracza.
 * Drużyny mogą być różnej wielkości, ale każda musi mieć co najmniej jednego gracza.
 */

public class Match {
    private Team teamA;
    private Team teamB;
    private MatchResult result;

    /**
     * Konstruktor Match, który inicjalizuje drużyny.
     *
     * @param teamA Pierwsza drużyna w meczu.
     * @param teamB Druga drużyna w meczu.
     */

    public Match(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    // Gettery i settery dla drużyn i wyniku meczu
    // Zwraca drużynę A
    public Team getTeamA() {
        return teamA;
    }

    // Zwraca drużynę B
    public Team getTeamB() {
        return teamB;
    }

    // Ustawia wynik meczu
    public void setResult(MatchResult result) {
        this.result = result;
    }

    // Zwraca wynik meczu
    public MatchResult getResult() {
        return result;
    }

    /**
     * Metoda do obliczenia średniego MMR przeciwnika dla danego gracza.
     * Jeśli gracz jest w drużynie A, zwraca średnie MMR drużyny B i vice versa.
     *
     * @param player Gracz, dla którego obliczamy średnie MMR przeciwnika.
     * @return Średnie MMR przeciwnika.
     */
    public double getOpponentAverageMMR(Player player) {
        if (teamA.getPlayers().contains(player)) {
            return teamB.getAverageMMR();
        } else {
            return teamA.getAverageMMR();
        }
    }
}