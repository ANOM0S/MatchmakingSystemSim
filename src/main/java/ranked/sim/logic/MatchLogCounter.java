package ranked.sim.logic;

/**
 * MatchLogCounter to klasa, która generuje unikalne numery dla każdego meczu.
 * Każde wywołanie metody getNext() zwraca kolejny numer meczu,
 */

public class MatchLogCounter {
    private static int matchNo = 1;

    /**
     * Metoda getNext zwraca kolejny numer meczu.
     *
     * @return Unikalny numer meczu.
     */
    public static int getNext() {
        return matchNo++;
    }
}
