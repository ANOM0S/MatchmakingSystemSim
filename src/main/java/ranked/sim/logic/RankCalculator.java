package ranked.sim.logic;

/**
 * RankCalculator to klasa, która oblicza nowy MMR (Matchmaking Rating) gracza
 * na podstawie aktualnego MMR, MMR przeciwnika i wyniku meczu.
 * Wzór na obliczenie nowego MMR jest oparty na algorytmie Elo, który jest
 * powszechnie stosowany w grach rankingowych.
 */

public class RankCalculator {
    private static final int K = 50;

    /**
     * Metoda calculateNewMMR oblicza nowy MMR gracza na podstawie aktualnego MMR,
     * MMR przeciwnika i wyniku meczu.
     *
     * @param currentMMR Aktualny MMR gracza.
     * @param opponentMMR MMR przeciwnika.
     * @param win Flaga wskazująca, czy gracz wygrał mecz (true) czy przegrał (false).
     * @return Nowy MMR gracza po meczu.
     */
    public static double calculateNewMMR(double currentMMR, double opponentMMR, boolean win) {
        double expectedScore = 1.0 / (1.0 + Math.pow(10, (opponentMMR - currentMMR) / 400));
        return K * ((win ? 1.0 : 0.0) - expectedScore);
    }
}
