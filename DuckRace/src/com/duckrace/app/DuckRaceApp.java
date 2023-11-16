package com.duckrace.app;

import com.apps.util.Prompter;
import com.duckrace.Board;
import com.duckrace.Reward;
import com.duckrace.view.BoardView;
import java.util.Scanner;

/*
 * Application "controller."
 *
 * It directs the overall flow/sequence of the application,
 * and does all user prompting.
 */
public class DuckRaceApp {
    private final Board board = Board.getInstance();
    private final BoardView boardView = new BoardView(board);
    private final Scanner scanner = new Scanner(System.in);
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    private final int maxId = board.maxId();

    public void execute() {
        welcome();
        showBoard();
        int id = promptForId();
        Reward reward = promptForReward_Prompter();
        updateBoard(id, reward);
        showBoard();
    }

    private void updateBoard(int id, Reward reward) {
        board.update(id, reward);
    }

    private Reward promptForReward_Prompter() {
        String input = prompter.prompt("Please enter [D]ebit card or [P]rizes: ",
                "\\s*(D|d|P|p)\\s*", "").toUpperCase();

        return ("D".equals(input)) ? Reward.DEBIT_CARD : Reward.PRIZES;
    }

    private Reward promptForReward_Scanner() {
        Reward reward = null;

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please enter [D]ebit card or [P]rizes: ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.matches("D|P")) {
                validInput = true;
                reward = ("D".equals(input)) ? Reward.DEBIT_CARD : Reward.PRIZES;
            }
        }
        return reward;
    }

    private int promptForId() {
        int id = 0;

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please enter id of the winner [1-" + maxId + "]: ");
            String input = scanner.nextLine().trim();
            if (input.matches("\\d{1,2}")) {
                id = Integer.parseInt(input);  // it's safe to do this now
                if (1 <= id && id <= maxId) {
                    validInput = true;
                }
            }
        }
        return id;
    }

    private void showBoard() {
        boardView.show();
    }

    private void welcome() {
        System.out.println();
        System.out.println("-  -  -  -  -  -  -    -  -    -  -  -    -  -  -  -    -  -  -  -    -  -  -  -  -  -  -  -  -  -  -");
        System.out.println("W  E  L  C  O  M  E    T  O    T  H  E    D  U  C  K    R  A  C  E    A  P  P  L  I  C  A  T  I  O  N");
        System.out.println("-  -  -  -  -  -  -    -  -    -  -  -    -  -  -  -    -  -  -  -    -  -  -  -  -  -  -  -  -  -  -");
        System.out.println();
    }
}