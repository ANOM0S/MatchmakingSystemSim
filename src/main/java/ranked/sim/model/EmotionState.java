package ranked.sim.model;

/**
 * Enum reprezentujący różne stany emocjonalne gracza.
 * Każdy stan emocjonalny może wpływać na skuteczność gracza w grze.
 * Stany emocjonalne są używane do symulacji wpływu emocji na grę,
 * np. stres, pewność siebie itp.
 */

public enum EmotionState {
    NEUTRAL,       // Podstawowy stan emocjonalny, brak wpływu na grę
    TILTED,        // Negatywny stan emocjonalny obniża skuteczność gracza
    RELAXED,       // Stan emocjonalny, który może poprawić skuteczność gracza
    CONFIDENT,     // Pozytywny stan emocjonalny zwiększa skuteczność gracza
    OVERCONFIDENT, // Nadmierna pewność siebie może prowadzić do błędów
    STRESSED,      // Stan stresu może obniżać skuteczność gracza
}
