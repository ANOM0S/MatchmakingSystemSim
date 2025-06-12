package ranked.sim.model;

/**
 * Strategia gracza w symulacji.
 * Określa, kiedy gracz chce grać na podstawie liczby rozegranych gier, ostatniego wyniku i stanu emocjonalnego.
 */

public enum Strategy {
    SPAMMING,
    LOSE_QUIT,
    TILL_WIN,
    TILL_LOSE,
    ONE_QUIT,
    TILT_QUIT;

    /**
     * Sprawdza, czy gracz chce grać w danej rundzie na podstawie strategii.
     *
     * @param gamesPlayedInRow Liczba gier rozegranych z rzędu.
     * @param lastOutcome Ostatni wynik (wygrana, przegrana lub brak).
     * @param emotion Stan emocjonalny gracza.
     * @return true, jeśli gracz chce grać, false w przeciwnym razie.
     */
    public boolean wantsToPlay(int gamesPlayedInRow, LastOutcome lastOutcome, EmotionState emotion) {
        return switch (this) {
            case SPAMMING -> true;

            case ONE_QUIT -> gamesPlayedInRow % 2 == 0;

            case LOSE_QUIT -> {
                // jeśli wygrał ostatnio — gra, jeśli nie — gra co 3 epokę, żeby się „ruszyć”
                yield lastOutcome == LastOutcome.WIN || gamesPlayedInRow % 3 == 0;
            }

            case TILL_WIN -> {
                // jeśli ostatnio przegrał — gra dalej
                // jeśli ostatnio wygrał — pauzuje 1 epokę
                yield lastOutcome == LastOutcome.LOSS || gamesPlayedInRow % 2 == 0;
            }

            case TILL_LOSE -> {
                // gra dopóki nie przegra, potem przerwa
                // po przerwie wraca co 2 epokę
                yield lastOutcome == LastOutcome.WIN || gamesPlayedInRow % 2 == 0;
            }

            case TILT_QUIT -> {
                // gra dopóki nie będzie w stanie TILTED
                // jeśli jest w stanie TILTED, to pauzuje 1 epokę
                yield emotion != EmotionState.TILTED || gamesPlayedInRow % 2 == 0;
            }
        };
    }
}
