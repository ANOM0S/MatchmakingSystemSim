package ranked.sim;

import ranked.sim.engine.GameEngine;

/**
 * Klasa main, która uruchamia symulację gry.
 * Ta klasa jest punktem wejścia do programu.
 * Używa klasy GameEngine do uruchomienia symulacji.
 * * @author Dawid Miniewksi
 */

public class Main {
    public static void main(String[] args) {
        // Inicjalizacja silnika gry z określoną liczbą graczy
        GameEngine engine = new GameEngine(200);

        // Uruchomienie symulacji gry z określoną liczbą epok i liczbą gier w każdej epoce
        engine.run(200, 4);
    }
}