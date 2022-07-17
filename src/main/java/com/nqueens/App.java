package com.nqueens;

import java.util.Scanner;

import com.google.common.base.Stopwatch;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int userInput;
            int requestedBoardSize;
            int requestedThreads;
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

            while (true) {
                System.out.println("> Enter the number of threads that should be used: ");
                try {
                    userInput = Integer.parseInt(scanner.nextLine());
                    if (userInput > 0) {
                        requestedThreads = userInput;
                        break;
                    } else {
                        System.out.println("> Please enter a positive integer.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("> Please enter a positive integer.");
                }
            }

            NQueensSolver solver = new NQueensSolver(requestedBoardSize, requestedThreads);

            Stopwatch stopwatch = Stopwatch.createStarted();
            solver.solve();
            stopwatch.stop();
            // solver.printSolutions();
            int solutionCount = solver.solutions.size();
            if (solutionCount > 0) {
                System.out.printf("\nFound %d solution(s) for the %d-Queen(s) problem in %s using %d thread(s).\n\n",
                        solutionCount,
                        solver.boardSize, stopwatch, solver.threadCount);

                System.out.println("> Do you want to print the solution boards to the console? [Y/n] ");
                String printBoardsInput = scanner.nextLine();
                if (printBoardsInput.equalsIgnoreCase("Y")) {
                    solver.printSolutions();
                }
                
            } else {
                solver.printSolutions();
            }

            while (true) {
                System.out.println("\n> Do you want to try again? [Y/n] ");

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
        System.out.println("");
        scanner.close();
    }
}
