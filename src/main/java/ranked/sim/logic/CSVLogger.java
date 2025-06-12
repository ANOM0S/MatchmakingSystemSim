package ranked.sim.logic;

import ranked.sim.model.Player;
import ranked.sim.simulation.Match;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * CSVLogger to klasa, która zapisuje wyniki meczów do pliku CSV.
 * Każdy mecz jest zapisywany w formacie:
 *
 */

public class CSVLogger implements ILogger {
    private final PrintWriter writer;

    /**
     * Konstruktor CSVLogger, który otwiera plik do zapisu.
     *
     * @param path Ścieżka do pliku, w którym będą zapisywane wyniki meczów.
     * @throws IOException Jeśli wystąpi błąd podczas otwierania pliku.
     */

    public CSVLogger(String path) throws IOException {
        this.writer = new PrintWriter(new FileWriter(path, false));
        writer.println("MatchNo,PlayerA,PlayerB,Result,MMR_A,MMR_B");
    }


    @Override
    public void logMatch(Match match) {
        List<Player> teamA = match.getTeamA().getPlayers();
        List<Player> teamB = match.getTeamB().getPlayers();

        Player p1 = teamA.get(0);
        Player p2 = teamB.get(0);


        writer.printf("%d,%s,%s,%s,%d,%d\n",
                MatchLogCounter.getNext(),
                p1.getName(),
                p2.getName(),
                match.getResult(),
                p1.getRank().getOverallPoints(),
                p2.getRank().getOverallPoints()
        );
        writer.flush();
    }
}
