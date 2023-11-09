package com.duckrace.view;

import com.duckrace.Board;
import com.duckrace.DuckRacer;
import java.util.Collection;

public class BoardView {
    private final Board board;

    public BoardView(Board board) {
        this.board = board;
    }

    public void show() {
        Collection<DuckRacer> racers = board.duckRacers();

        // title and headings
        StringBuilder display = new StringBuilder();
        display.append("\nDuck Race Results\n");
        display.append("=================\n");
        display.append("\n");
        display.append("id    name      wins    rewards\n");
        display.append("--    ----      ----    -------\n");

        // race results
        if (racers.isEmpty()) {
            display.append("\nThere are currently no results to show\n");
        }
        else {
            for (DuckRacer racer : racers) {
                String rewardsString = racer.getRewards().toString();
                String rewards = rewardsString.substring(1, rewardsString.length() - 1);

                String row = String.format("%2s    %-8s %4s     %s\n",
                        racer.getId(), racer.getName(), racer.getWins(), rewards);
                display.append(row);
            }
        }
        display.append("\n");
        System.out.println(display);
    }
}