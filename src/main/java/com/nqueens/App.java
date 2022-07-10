package com.nqueens;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int userInput;
            int requestedBoardSize;
            boolean tryAgain = true;

            System.out.println("\n\t\t\t\t*** N-Queens Puzzle Solver ***\n");

            while (true) {
                System.out.println("> Enter the dimension of the chess board:");
                try {
                    userInput = Integer.parseInt(scanner.nextLine());
                    if (userInput > 0) {
                        requestedBoardSize = userInput;
                        break;
                    } else {
                        System.out.println(
                                "> Please enter a positive integer.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("> Please enter a positive integer.");
                }
            }

            NQueensSolver solver = new NQueensSolver(requestedBoardSize);
            solver.solve();
            solver.printSolutions();

            while (true) {
                System.out.println("> Do you want to try again? [Y/n] ");

                String tryAgainInput = scanner.nextLine();
                if (tryAgainInput.equalsIgnoreCase("Y")) {
                    break;
                } else if (tryAgainInput.equalsIgnoreCase("N")) {
                    tryAgain = false;
                    break;
                } else {
                    tryAgain = false;
                    System.out.println("> Aborted");
                    break;
                }
            }

            if (!tryAgain) {
                break;
            }
        }
        scanner.close();
    }
}
