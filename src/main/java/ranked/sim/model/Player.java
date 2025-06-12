package ranked.sim.model;

import ranked.sim.simulation.Match;
import ranked.sim.simulation.MatchResult;

/**
 * Klasa reprezentująca gracza w symulacji.
 * Dziedziczy z klasy APlayer i implementuje interfejs IPlayer.
 * Zawiera metody i pola specyficzne dla gracza, takie jak aktualizacja stanu po meczu,
 * zarządzanie emocjami oraz strategią gry.
 */

public class Player extends APlayer {

    /**
     * Konstruktor klasy Player.
     * Tworzy instancję gracza z podaną nazwą, statystykami, rangą i strategią.
     *
     * @param name Nazwa gracza
     * @param stats Statystyki gracza
     * @param rank Ranga gracza
     * @param strategy Strategia gracza
     */
    public Player(String name, Stats stats, Rank rank, Strategy strategy) {
        super(name, stats, rank, strategy);
    }

    // Pola specyficzne dla gracza
    private int gamesInRow = 0;
    private boolean willPlay = true;
    private LastOutcome lastOutcome = LastOutcome.NONE;

    // Metody specyficzne dla gracza
    /**
     * Aktualizuje stan gracza po meczu.
     * Zmienia ostatni wynik, aktualizuje rangę, emocje i statystyki.
     *
     * @param result Wynik meczu
     * @param match Mecz, w którym brał udział gracz
     */
    @Override
    public void updateAfterMatch(MatchResult result, Match match) {
        boolean win = (match.getTeamA().getPlayers().contains(this) && result == MatchResult.TEAM_A_WIN)
                || (match.getTeamB().getPlayers().contains(this) && result == MatchResult.TEAM_B_WIN);

        lastOutcome = win ? LastOutcome.WIN : LastOutcome.LOSS;

        double opponentMMR = match.getOpponentAverageMMR(this);
        rank.updateAfterMatch(win, opponentMMR);

        adjustEmotion(win);

        if (win) {
            stats.adjust(1);
        } else if (stats.getPower() < 20) {
            stats.adjust((Math.random() * 1.5) - 1);
        }

        if (willPlay) {
            gamesInRow++;
        } else {
            gamesInRow = 0;
        }

        // Jeśli nie gra → emocje się resetują lub poprawiają
        if (!willPlay) {
            if (emotionState == EmotionState.TILTED) {
                emotionState = EmotionState.NEUTRAL;
            } else if (emotionState == EmotionState.NEUTRAL) {
                emotionState = EmotionState.RELAXED;
            } else if (emotionState == EmotionState.CONFIDENT) {
                emotionState = EmotionState.NEUTRAL;
            } else if (emotionState == EmotionState.OVERCONFIDENT) {
                emotionState = EmotionState.NEUTRAL;
            }
        }

    }

    /**
     * Dostosowuje stan emocjonalny gracza w zależności od wyniku meczu.
     * Zmienia stan emocjonalny na podstawie tego, czy gracz wygrał, czy przegrał.
     *
     * @param win Czy gracz wygrał mecz
     */
    public void adjustEmotion(boolean win) {
        if (win) {
            switch (emotionState) {
                case NEUTRAL -> emotionState = Math.random() < 0.7 ? EmotionState.CONFIDENT : EmotionState.NEUTRAL;
                case CONFIDENT -> emotionState = Math.random() < 0.5 ? EmotionState.OVERCONFIDENT : EmotionState.CONFIDENT;
                case TILTED, STRESSED -> emotionState = Math.random() < 0.9 ? EmotionState.NEUTRAL : EmotionState.CONFIDENT;
            }
        } else {
            switch (emotionState) {
                case NEUTRAL -> emotionState = Math.random() < 0.5 ? EmotionState.TILTED : EmotionState.NEUTRAL;
                case OVERCONFIDENT -> emotionState = Math.random() < 0.1 ? EmotionState.TILTED : EmotionState.NEUTRAL;
                case CONFIDENT -> emotionState = Math.random() < 0.5 ? EmotionState.CONFIDENT : EmotionState.NEUTRAL;
            }

        }

        // Spadek statystyk w przypadku zbyt dużej ilości gier przez zmęczenie
        if (strategy == Strategy.SPAMMING && emotionState == EmotionState.TILTED) {
            stats.adjust(-0.5);
        }
    }

    /**
     * Pobiera siłę gracza, która jest obliczana na podstawie statystyk i stanu emocjonalnego.
     *
     * @return Siła gracza
     */
    public boolean isWillingToPlay() {
        return willPlay;
    }

    /**
     * Przygotowuje gracza do nowej epoki, decydując, czy będzie grał.
     * Wykorzystuje strategię gracza do podjęcia decyzji.
     */
    public void prepareForEpoch() {
        willPlay = strategy.wantsToPlay(gamesInRow, lastOutcome, emotionState);
    }

    /**
     * Zwraca reprezentację statystyk gracza jako ciąg znaków.
     *
     * @return Statystyki gracza w formacie tekstowym
     */
    public String statsToString() {
        return "atack:" + stats.getAttack() +
               ", defense:" + stats.getDefense() +
               ", combo:" + stats.getCombo();
    }

    /**
     * Zwraca reprezentację gracza jako ciąg znaków.
     *
     * @return Nazwa gracza i jego statystyki
     */
    @Override
    public Stats getStats() {
        return super.getStats();
    }

    /**
     * Ustawia stan emocjonalny gracza.
     *
     * @param emotion Nowy stan emocjonalny gracza
     */
    public void setEmotion(EmotionState emotion) {
        this.emotionState = emotion;
    }

    /**
     * Resetuje stan emocjonalny gracza do neutralnego lub poprawia go w zależności od aktualnego stanu.
     * Jeśli gracz jest w stanie TILTED, przechodzi do NEUTRAL, a jeśli jest w stanie NEUTRAL, przechodzi do CONFIDENT.
     */
    public void rest() {
        if (emotionState == EmotionState.TILTED) {
            emotionState = EmotionState.NEUTRAL;
        } else if (emotionState == EmotionState.NEUTRAL) {
            emotionState = EmotionState.CONFIDENT;
        }
    }
}
