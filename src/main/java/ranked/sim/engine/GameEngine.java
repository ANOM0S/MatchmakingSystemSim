package ranked.sim.engine;

import ranked.sim.logic.CSVLogger;
import ranked.sim.model.*;
import ranked.sim.simulation.Match;
import ranked.sim.simulation.MatchResult;
import ranked.sim.simulation.MatchSimulator;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Klasa GameEngine jest odpowiedzialna za zarządzanie rozgrywkami w grze.
 * Zawiera logikę do tworzenia graczy, przeprowadzania meczów i logowania wyników.
 * Każdy mecz jest symulowany przy użyciu MatchSimulator, a wyniki są zapisywane w pliku CSV.
 * Gracze są tworzeni z różnymi strategiami i statystykami, a ich rankingi są aktualizowane po każdym meczu.
 * Gracze są podzieleni na grupy i rozgrywają mecze w epoce, a ich wyniki są analizowane i wyświetlane.
 * Każda epoka składa się z wielu gier, a gracze są losowo mieszani co kilka epok.
 * Gracze są sortowani według MMR (Matchmaking Rating) przed rozpoczęciem każdej epoki.
 * Gracze mogą mieć różne strategie, a ich emocje są aktualizowane w zależności od wyników meczów.
 * Każdy gracz ma swoje statystyki, rankingi i strategię, które są aktualizowane po każdym meczu.
 */

public class GameEngine {
    private List<Player> players;
    private CSVLogger logger;
    private MatchSimulator matchSimulator;

    /**
     * Konstruktor GameEngine inicjalizuje graczy i logger.
     * Tworzy listę graczy z różnymi strategiami i statystykami.
     *
     * @param numberOfPlayers Liczba graczy do utworzenia
     */
    public GameEngine(int numberOfPlayers) {
        // Inicjalizacja listy graczy i loggera
        players = new ArrayList<>();
        try {
            logger = new CSVLogger("match_log.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Inicjalizacja MatchSimulator
        matchSimulator = new MatchSimulator();

        // Tworzenie graczy z różnymi strategiami i statystykami
        for (int i = 0; i < numberOfPlayers; i++) {
            Stats stats = new Stats(10, 10, 10);
            Rank rank = new Rank( 820, RankName.Silver, 1000);
            Strategy strategy = Strategy.values()[i % Strategy.values().length];
            players.add(new Player("Player" + i, stats, rank, strategy));
        }
    }

    /**
     * Metoda run uruchamia symulację gier w epoce.
     * Gracze są dzieleni na grupy, rozgrywają mecze, a wyniki są logowane.
     * Po każdej epoce wyświetlany jest ranking graczy.
     *
     * @param epochCount Liczba epok do rozegrania
     * @param gamesPerPlayer Liczba gier, które każdy gracz ma zagrać w jednej epoce
     */

    public void run(int epochCount, int gamesPerPlayer) {
        final int GAMES_PER_PLAYER = gamesPerPlayer;
        final int GROUP_SIZE = 12;

        for (int epoch = 1; epoch <= epochCount; epoch++) {
            System.out.println("\n== Epoch " + epoch + " ==");

            // Reset stanu graczy
            for (Player p : players) {
                p.prepareForEpoch();
            }

            // Co kilka epok: przetasuj globalnie (zmieszaj środek stawki)
            if (epoch % 3 == 0) {
                Collections.shuffle(players);
            }

            // Posortuj wg MMR
            players.sort(Comparator.comparingDouble(p -> p.getRank().getMMR()));

            // Podziel graczy na grupy po GROUP_SIZE
            List<List<Player>> groups = new ArrayList<>();
            for (int i = 0; i < players.size(); i += GROUP_SIZE) {
                int end = Math.min(i + GROUP_SIZE, players.size());
                groups.add(new ArrayList<>(players.subList(i, end)));
            }

            for (List<Player> group : groups) {
                // Przygotuj mapę ile razy gracz zagrał
                Map<Player, Integer> gamesPlayed = new HashMap<>();
                for (Player p : group) {
                    gamesPlayed.put(p, 0);
                }

                int totalGames = group.size() * GAMES_PER_PLAYER / 2;
                int gameCounter = 0;
                Random rand = new Random();

                while (gameCounter < totalGames) {
                    // Lista graczy, którzy mogą jeszcze grać
                    List<Player> available = group.stream()
                            .filter(p -> p.isWillingToPlay() && gamesPlayed.get(p) < GAMES_PER_PLAYER)
                            .collect(Collectors.toList());

                    if (available.size() < 2) break;

                    // Przetasuj dostępnych i wybierz dwóch
                    Collections.shuffle(available);
                    Player p1 = available.get(0);
                    Player p2 = available.get(1);

                    Team t1 = new Team(List.of(p1));
                    Team t2 = new Team(List.of(p2));
                    Match match = new Match(t1, t2);

                    MatchResult result = matchSimulator.simulateMatch(match);
                    p1.updateAfterMatch(result, match);
                    p2.updateAfterMatch(result, match);
                    logger.logMatch(match);

                    gamesPlayed.put(p1, gamesPlayed.get(p1) + 1);
                    gamesPlayed.put(p2, gamesPlayed.get(p2) + 1);
                    gameCounter++;
                }
            }

            showRanking("After epoch " + epoch);
        }

        showRanking("Final Rankings");
    }


    /**
     * Wyświetla ranking graczy.
     * Gracze są sortowani według punktów ogólnych i wyświetlane są ich statystyki, strategia, stan emocjonalny oraz tier.
     *
     * @param label Etykieta do wyświetlenia przed rankingiem
     */
    private void showRanking(String label) {
        players.sort(Comparator.comparingDouble(p -> -p.getRank().getOverallPoints()));
        System.out.println("\n" + label);
        for (Player p : players) {
            System.out.printf("Stats: %s | Strategy: %s | Emotion: %s | Overall: %d | Tier: %s\n",
                    p.statsToString(),
                    p.getStrategy(),
                    p.getEmotionState(),
                    p.getRank().getOverallPoints(),
                    p.getRank().getRank());
        }
    }

}

