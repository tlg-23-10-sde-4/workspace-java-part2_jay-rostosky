package com.duckrace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * This is a lookup table of ids to student names.
 * When a duck wins for the first time, we need to find out who that is.
 * This lookup table could be hardcoded with the data, or we could read the data 
 * in from a file, so that no code changes would need to be made for each cohort.
 *
 * Map<Integer,String> studentIdMap;
 * 
 * Integer    String
 * =======    ======
 *    1       John
 *    2       Jane
 *    3       Danny
 *    4       Armando
 *    5       Sheila
 *    6       Tess
 * 
 *
 * We also need a data structure to hold the results of all winners.
 * This data structure should facilitate easy lookup, retrieval, and storage.
 *
 * Map<Integer,DuckRacer> racerMap;  // K: Integer, V: DuckRacer
 *
 * Integer    DuckRacer
 * =======    =========
 *            id    name     wins   rewards
 *            --    ----     ----   -------
 *    5        5    Sheila     2    PRIZES, PRIZES
 *    6        6    Tess       1    PRIZES
 *   13       13    Zed        3    PRIZES, DEBIT_CARD, DEBIT_CARD
 *   17       17    Dom        1    DEBIT_CARD
 */

class Board {
    private final Map<Integer,String> studentIdMap = loadStudentIdMap();
    private final Map<Integer,DuckRacer> racerMap  = new TreeMap<>();

    /*
     * Updates the board (racerMap) by making a DuckRacer "win."
     *
     * This could mean fetching an existing DuckRacer from racerMap (id is in the map),
     * or we might need to create a new DuckRacer (id is not in the map),
     * then we need to put that new DuckRacer in the map, and make it "win."
     *
     * Either way, it needs to "win."
     */
    public void update(int id, Reward reward) {
        DuckRacer racer = null;

        if (racerMap.containsKey(id)) {  // id present, so fetch DuckRacer next to it, make it "win"
            racer = racerMap.get(id);
        }
        else {  // id not present, so here we create new DuckRacer
            racer = new DuckRacer(id, studentIdMap.get(id));
            racerMap.put(id, racer);     // easy to forget this step
        }
        racer.win(reward);
    }

    /*
     * TODO: render the data show it looks like the board you see every day
     * Consider using a StringBuilder and append() instead of myriad sout calls.
     *
     * Duck Race Results
     * =================
     *
     * id    name    wins    rewards
     * --    ----    ----    -------
     *  1    Aaron     1     PRIZES
     *  3    Bullen    1     DEBIT_CARD
     *  4    CJ        2     DEBIT_CARD, DEBIT_CARD
     * 14    Scott     6     PRIZES, PRIZES, PRIZES, PRIZES, DEBIT_CARD, PRIZES
     */
    public void show() {
        // title and headings
        StringBuilder display = new StringBuilder();
        display.append("\nDuck Race Results\n");
        display.append("=================\n");
        display.append("\n");
        display.append("id    name      wins    rewards\n");
        display.append("--    ----      ----    -------\n");

        Collection<DuckRacer> racers = racerMap.values();
        for (DuckRacer racer : racers) {
            String rewardsString = racer.getRewards().toString();
            String rewards = rewardsString.substring(1, rewardsString.length() - 1);

            String row = String.format("%2s    %-8s %4s     %s\n",
                    racer.getId(), racer.getName(), racer.getWins(), rewards);
            display.append(row);
        }
        display.append("\n");
        System.out.println(display);
    }

    // TESTING PURPOSES ONLY
    void dumpStudentIdMap() {
        System.out.println(studentIdMap);
    }

    private Map<Integer,String> loadStudentIdMap() {
        Map<Integer,String> idMap = new HashMap<>();

        try {
            // read all lines from conf/student-ids.csv into a List<String>
            List<String> lines = Files.readAllLines(Path.of("conf/student-ids.csv"));

            // for each line, split it into "tokens," i.e., "1,Aaron" is split into "1" and "Aaron"
            for (String line : lines) {
                String[] tokens = line.split(",");  // tokens[0] is "1" and tokens[1] is "Aaron"
                idMap.put(Integer.valueOf(tokens[0]), tokens[1]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return idMap;
    }
}