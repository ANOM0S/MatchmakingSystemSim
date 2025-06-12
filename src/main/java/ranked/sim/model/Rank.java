package ranked.sim.model;

import ranked.sim.logic.RankCalculator;

/**
 * Reprezentuje rangę gracza w systemie rankingowym.
 * Zawiera informacje o punktach ogólnych, nazwie rangi i MMR (Matchmaking Rating).
 * Ranga jest aktualizowana po każdym meczu na podstawie wyniku i MMR przeciwnika.
 */

public class Rank {
    private int overallPoints;
    private RankName rank;
    private int MMR;

    /**
     * Konstruktor klasy Rank.
     *
     * @param overallPoints Punkty ogólne gracza.
     * @param rank Nazwa rangi gracza.
     * @param MMR Matchmaking Rating gracza.
     */
    public Rank(int overallPoints, RankName rank, int MMR) {
        this.overallPoints = overallPoints;
        this.rank = rank;
        this.MMR = MMR;
    }


    /**
     * Zwraca punkty ogólne gracza.
      * @return Punkty ogólne gracza.
     */
    public int getOverallPoints() {
        return overallPoints;
    }
    /**
     * Zwraca nazwę rangi gracza.
     *
     * @return Nazwa rangi gracza.
     */
    public RankName getRank() {
        return rank;
    }
    /**
     * Zwraca Matchmaking Rating (MMR) gracza.
     *
     * @return MMR gracza.
     */
    public int getMMR() {
        return MMR;
    }

    /**
     * Aktualizuje stan rangi gracza po meczu.
     * Zmienia MMR na podstawie wyniku meczu i MMR przeciwnika.
     * Aktualizuje punkty ogólne w zależności od wyniku meczu.
     *
     * @param win Czy gracz wygrał mecz.
     * @param opponentMMR MMR przeciwnika.
     */
    public void updateAfterMatch(boolean win, double opponentMMR) {
        double delta = RankCalculator.calculateNewMMR(this.MMR, opponentMMR, win);
        MMR += delta;

        if (win) {
            overallPoints += 20;
        } else {
            overallPoints -= 15;
        }

        if (overallPoints < 0) overallPoints = 0;

        updateRankName();
    }

    /**
     * Aktualizuje nazwę rangi na podstawie punktów ogólnych.
     * Przypisuje odpowiednią rangę w zależności od wartości punktów ogólnych.
     */
    private void updateRankName() {
        if (overallPoints < 400) rank = RankName.Iron;
        else if (overallPoints < 800) rank = RankName.Bronze;
        else if (overallPoints < 1200) rank = RankName.Silver;
        else if (overallPoints < 1600) rank = RankName.Gold;
        else if (overallPoints < 2000) rank = RankName.Platinum;
        else if (overallPoints < 2400) rank = RankName.Emerald;
        else if (overallPoints < 2800)rank = RankName.Diamond;
        else if (overallPoints < 3400) rank = RankName.Master;
        else if (overallPoints < 4200) rank = RankName.Grandmaster;
        else rank = RankName.Challenger;
    }

}
