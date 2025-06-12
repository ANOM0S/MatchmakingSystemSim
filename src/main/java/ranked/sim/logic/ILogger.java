package ranked.sim.logic;

import ranked.sim.simulation.Match;

/**
 * ILogger to interfejs, który definiuje metodę do logowania wyników meczów.
 * Każda implementacja tego interfejsu powinna dostarczać własną logikę
 */
public interface ILogger {
    void logMatch(Match match);
}
