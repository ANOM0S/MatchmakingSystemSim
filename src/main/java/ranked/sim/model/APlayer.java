package ranked.sim.model;

/**
 * Klasa abstrakcyjna APlayer implementująca interfejs IPlayer.
 * Reprezentuje gracza w symulacji, zawiera podstawowe informacje o graczu,
 * takie jak nazwa, statystyki, ranga, stan emocjonalny i strategia.
 */

public abstract class APlayer implements IPlayer {
    protected String name;
    protected Stats stats;
    protected Rank rank;
    protected EmotionState emotionState;
    protected Strategy strategy;

    /**
     * Konstruktor klasy APlayer.
     *
     * @param name          Nazwa gracza.
     * @param stats         Statystyki gracza.
     * @param rank          Ranga gracza.
     * @param strategy      Strategia gracza.
     */
    public APlayer(String name, Stats stats, Rank rank, Strategy strategy) {
        this.name = name;
        this.stats = stats;
        this.rank = rank;
        this.strategy = strategy;
        this.emotionState = EmotionState.NEUTRAL;
    }

    // Metody
    /**
     * Pobiera nazwę gracza.
     *
     * @return Nazwa gracza.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Pobiera statystyki gracza.
     *
     * @return Statystyki gracza.
     */
    @Override
    public Stats getStats() {
        return stats;
    }

    /**
     * Pobiera rangę gracza.
     *
     * @return Ranga gracza.
     */
    @Override
    public Rank getRank() {
        return rank;
    }

    /**
     * Pobiera stan emocjonalny gracza.
     *
     * @return Stan emocjonalny gracza.
     */
    @Override
    public EmotionState getEmotionState() {
        return emotionState;
    }

    /**
     * Pobiera strategię gracza.
     *
     * @return Strategia gracza.
     */
    @Override
    public Strategy getStrategy() {
        return strategy;
    }


    /**
     * Zwraca siłę gracza.
     *
     * @return Siła gracza, która jest obliczana na podstawie statystyk i stanu emocjonalnego.
     */
    @Override
    public double getPower() {
        // Oblicza siłę gracza na podstawie statystyk i stanu emocjonalnego
        double emotionMultiplier = switch (emotionState) {
            case NEUTRAL -> 1.0;
            case TILTED -> 0.9;
            case RELAXED -> 1.05;
            case CONFIDENT -> 1.1;
            case OVERCONFIDENT -> 0.95;
            case STRESSED -> 0.98;
        };

        // Mnoży siłę statystyk przez mnożnik rangi i mnożnik emocji
        return stats.getPower() * emotionMultiplier;
    }
}