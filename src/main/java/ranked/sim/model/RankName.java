package ranked.sim.model;

/**
 * Enum reprezentujący nazwy rang w grze.
 * Każda ranga odpowiada określonemu przedziałowi punktów.
 * Rangi są uporządkowane od najniższej do najwyższej:
 */
public enum RankName {
    Iron,        // 0-400 punktów
    Bronze,      // 400-800 punktów
    Silver,      // 800-1200 punktów
    Gold,        // 1200-1600 punktów
    Platinum,    // 1600-2000 punktów
    Emerald,     // 2000-2400 punktów
    Diamond,     // 2400-2800 punktów
    Master,      // 2800-3600 punktów
    Grandmaster, // 3400-4200 punktów
    Challenger,  // 4200+ punktów
    ;
}
