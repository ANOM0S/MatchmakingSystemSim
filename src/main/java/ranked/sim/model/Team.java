package ranked.sim.model;

import java.util.List;

/**
 * Klasa reprezentująca drużynę w symulacji.
 * Drużyna składa się z listy graczy.
 */

public class Team {
    private List<Player> players;

    public Team(List<Player> players) {
        this.players = players;
    }

    // Gettery i Settery
    // Zwracają listę graczy w drużynie.
    public List<Player> getPlayers() {
        return players;
    }

    // Pobiera moc drużyny, sumując moc wszystkich graczy.
    public double getTeamPower() {
        return players.stream()
                .mapToDouble(Player::getPower)
                .sum();
    }

    // Pobiera średni MMR drużyny, obliczając średnią z MMR wszystkich graczy.
    public double getAverageMMR() {
        return players.stream()
                .mapToDouble(p -> p.getRank().getMMR())
                .average()
                .orElse(1000.0);
    }
}

