package ranked.sim.model;

import static java.lang.Math.round;

/**
 * Klasa reprezentująca statystyki gracza w grze Ranked.
 * Zawiera informacje o ataku, obronie i kombinacji.
 * Statystyki te są używane do obliczenia siły gracza.
 */

public class Stats {
    private double attack;
    private double defense;
    private double combo;

    /**
     * Konstruktor klasy Stats.
     * Inicjalizuje statystyki ataku, obrony i kombinacji.
     *
     * @param attack  wartość ataku
     * @param defense wartość obrony
     * @param combo   wartość kombinacji
     */
    public Stats(double attack, double defense, double combo) {
        this.attack = attack;
        this.defense = defense;
        this.combo = combo;
    }

    /**
     * Metoda zwracająca siłę gracza, która jest sumą ataku, obrony i kombinacji.
     *
     * @return siła gracza
     */
    public double getPower() {
        return attack + defense + combo;
    }

    /**
     * Metoda dostosowująca statystyki gracza na podstawie współczynnika.
     * Wartości ataku, obrony i kombinacji są zwiększane o losową wartość pomnożoną przez współczynnik.
     *
     * @param factor współczynnik, o który mają być dostosowane statystyki
     */
    public void adjust(double factor) {

        this.attack += (factor * round(Math.random()*125)/100.0);
        this.defense += (factor * round(Math.random()*125)/100.0);
        this.combo += (factor * round(Math.random()*125)/100.0);
    }

    /**
     * Metoda zwracająca wartość ataku, zaokrągloną do dwóch miejsc po przecinku.
     *
     * @return wartość ataku
     */
    public double getAttack() {
        return Math.round(this.attack * 100.0) / 100.0;
    }
    /**
     * Metoda zwracająca wartość obrony, zaokrągloną do dwóch miejsc po przecinku.
     *
     * @return wartość obrony
     */
    public double getDefense() {
        return Math.round(this.defense * 100.0) / 100.0;
    }
    /**
     * Metoda zwracająca wartość kombinacji, zaokrągloną do dwóch miejsc po przecinku.
     *
     * @return wartość kombinacji
     */
    public double getCombo() {
        return Math.round(this.combo * 100.0) / 100.0;
    }

}
