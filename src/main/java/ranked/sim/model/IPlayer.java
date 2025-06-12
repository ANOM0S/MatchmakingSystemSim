package ranked.sim.model;

import ranked.sim.simulation.Match;
import ranked.sim.simulation.MatchResult;

/**
 * Klasa reprezentująca gracza w symulacji.
 * Dziedziczy z klasy APlayer i implementuje interfejs IPlayer.
 * Zawiera metody i pola specyficzne dla gracza, takie jak aktualizacja stanu po meczu,
 * zarządzanie emocjami oraz strategią gry.
 */

public interface IPlayer {
    // Metody
    String getName(); // Pobiera nazwę gracza
    Stats getStats(); // Pobiera statystyki gracza
    Rank getRank(); // Pobiera rangę gracza
    EmotionState getEmotionState(); // Pobiera stan emocjonalny gracza
    Strategy getStrategy(); // Pobiera strategię gracza
    void updateAfterMatch(MatchResult result, Match match); // Aktualizuje stan gracza po meczu
    double getPower(); // Pobiera siłę gracza, która jest obliczana na podstawie statystyk i stanu emocjonalnego

}
