![Tests](https://github.com/ANOM0S/MatchmakingSystemSim/actions/workflows/maven.yml/badge.svg)
![Coverage](https://codecov.io/gh/ANOM0S/MatchmakingSystemSim/branch/main/graph/badge.svg)

# 🎮 Symulacja systemu rankingowego graczy (1v1)

Projekt implementuje agentową symulację systemu rankingowego inspirowanego grami sieciowymi typu PvP (Player vs Player). Celem symulacji jest odwzorowanie zachowań graczy, rozwoju ich statystyk oraz ewolucji pozycji w rankingu na przestrzeni wielu epok.

---

## 📌 Założenia symulacji

- Gracze rozgrywają mecze 1v1 w seriach zwanych **epokami**.
- Wynik meczu zależy od **statystyk graczy** (atak, obrona, combo).
- **MMR** (ranking gracza) aktualizowany jest zgodnie z algorytmem ELO.
- Gracze posiadają różne **strategie** (np. SPAMMING, ONE_QUIT) oraz **emocje** (TILTED, CONFIDENT), które wpływają na decyzje o grze i rozwój.
- Program umożliwia eksport danych do CSV, analizę wyników i tworzenie wykresów.

---

## ⚙️ Wymagania techniczne

- Java 17+
- Gradle (build tool)
- IntelliJ IDEA (zalecane)
- JUnit 5 (testy jednostkowe)

---

## 🚀 Jak uruchomić projekt

```bash
git clone <repo-url>
cd project-folder
./gradlew build
./gradlew run
```
## 📁 Struktura projektu
```
.
├── build.gradle          # konfiguracja Gradle
├── settings.gradle       # konfiguracja projektu
├── README.md             # ten plik
├── src/
│   ├── main/
│   │   └── java/
|   |       └──
│   │          ├── engine/     # GameEngine, Match, MatchSimulator
│   │          ├── model/      # Player, Stats, Rank, EmotionState, Strategy
│   │          ├── logic/      # Logger, RankCalculator
│   │          ├── simulation/ # Match, MatchResult, MatchSimulator
│   │          └── Main.java   # punkt wejścia
│   └── test/
│       └── java/         # testy JUnit
```

## 🧪 Testy jednostkowe
Projekt zawiera testy sprawdzające:
- logikę strategii gracza (StrategyTest)
- działanie systemu MMR i punktacji (RankCalculatorTest, RankTest)
- rozwój statystyk i emocji (PlayerTest)
- symulację rozgrywek (MatchSimulatorTest)

Uruchamianie testów:
```bash
./gradlew test
```

## 📊 Eksport danych
Podczas symulacji możliwe jest zapisywanie danych graczy do pliku epoch_stats.csv, który zawiera:

- epokę,
- nazwę gracza,
- aktualny MMR,
- punkty,
- strategię,
- stan emocjonalny.

## 💡 Możliwości rozwoju
Ten projekt można łatwo rozbudować m.in. o:

- tryb 3v3 lub 5v5 (symulacje drużynowe),
- dodatkowe strategie i bardziej złożony silnik emocji,
- system relacji (np. przyjaźń, wrogość, unikanie),
- GUI (np. JavaFX lub Swing) z widokiem rankingu i wykresami,
- eksport wyników do PDF / wykresów SVG,
- analizy porównawcze wpływu strategii na wyniki (np. wykresy MMR vs. emocje).
