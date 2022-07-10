package com.nqueens;

import java.util.ArrayList;

public class NQueensSolver {
    int boardSize; // Size of the chessboard
    ArrayList<int[]> solutions = new ArrayList<>(); // Used to store the solutions found

    public NQueensSolver(int boardSize) {
        this.boardSize = boardSize;
    }

    public void solve() {
        for (int i = 0; i < boardSize; i++) {
            // rows stores the row number in the element having the index of the column.
            // Example: rows[3]=6 means column 3 has a queen placed in row 6
            int[] rows = new int[boardSize];
            rows[0] = i;
            findSolution(rows, 1);
        }
    }

    private void findSolution(int[] rows, int col) {
        if (col == boardSize) {
            // Found complete solution
            solutions.add(rows.clone());

        } else {
            // Continue looking for a solution recursively
            for (int row = 0; row < boardSize; row++) {
                if (checkValidity(rows, col, row)) {
                    rows[col] = row; // Place queen on the board
                    findSolution(rows, col + 1);
                }
            }
        }
    }

    // Check if (col1, row1) is a valid spot for a queen by checking if there is a
    // queen in the same row or diagonal.
    // We do not need to check it for queens in the same column because
    // "findSolution" only places 1 queen at a time. We know this column is empty.
    private boolean checkValidity(int[] rows, int col1, int row1) {
        for (int col2 = 0; col2 < col1; col2++) {

            int row2 = rows[col2];

            // Check if columns have a queen in the same row
            if (row1 == row2) {
                return false;
            }

            // Check diagonals: If distance between the columns = distance between the rows,
            // they are in the same diagonal.
            int rowDistance = Math.abs(row2 - row1);

            // col1 > col2, no need for abs
            int colDistance = col1 - col2;
            if (rowDistance == colDistance) {
                return false;
            }
        }
        return true;
    }

    // Returns an array of the individual digit chars in a number
    private char[] getNumberDigits(int number) {
        return String.valueOf(number).toCharArray();
    }

    // Prints all the solutions found
    public void printSolutions() {
        System.out.printf("\n> %d solutions found for the %d-Queens problem.\n\n", solutions.size(),
                boardSize);

        // Counter of solutions printed
        int solutionCounter = 0;

        // Number of digits in the board size number
        int boardSizeLength = String.valueOf(boardSize - 1).length();

        // Size for the grid used to print the results
        int printGridSize = boardSize + boardSizeLength + 2;

        // Print a grid containing the queens positions for each solution found
        for (int[] solution : solutions) {
            solutionCounter++;
            System.out.printf(" Solution %d:\n", solutionCounter);
            System.out.print(" Queens placed at:");

            // Print the queen placement coordinates of the current solution
            for (int q = 0; q < solution.length; q++) {
                System.out.printf(" (%d, %d)", q, solution[q]);
                if (q != solution.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("\n\n");

            String[][] printGrid = new String[printGridSize][printGridSize];

            // Initialize all grid positions with " "
            for (int i = 0; i < printGridSize; i++) {
                for (int j = 0; j < printGridSize; j++) {
                    printGrid[i][j] = " ";
                }
            }

            // Set reference numbers at the bottom border of the grid
            for (int j = boardSizeLength + 1; j < printGridSize - 1; j++) {
                int indexNumber = j - boardSizeLength - 1;
                char[] digitChars = getNumberDigits(indexNumber);
                int l = 0; // digitChars index counter

                for (int i = printGridSize - boardSizeLength; i < printGridSize; i++) {
                    if (l < digitChars.length) {
                        printGrid[i][j] = String.format(" %c ", digitChars[l]);
                        l++;
                    } else {
                        printGrid[i][j] = "   ";
                    }
                }
            }

            // Set reference numbers at the left border of the grid
            for (int i = 1; i <= boardSize; i++) {
                int indexNumber = boardSize - i;
                char[] digitChars = getNumberDigits(indexNumber);
                int l = digitChars.length - 1; // digitChars index counter

                for (int j = boardSizeLength - 1; j >= 0; j--) {
                    if (l >= 0) {
                        printGrid[i][j] = Character.toString(digitChars[l]);
                        l--;
                    } else {
                        printGrid[i][j] = " ";
                    }
                }
            }

            // Set the + symbol at the grid corners
            printGrid[0][boardSizeLength] = "+"; // top left corner
            printGrid[printGridSize - boardSizeLength - 1][boardSizeLength] = "+"; // bottom left corner
            printGrid[printGridSize - boardSizeLength - 1][printGridSize - 1] = "+"; // bottom right corner
            printGrid[0][printGridSize - 1] = "+"; // top right corner

            // Set the | symbol at the left vertical grid border
            for (int i = 1; i < printGridSize - boardSizeLength - 1; i++) {
                printGrid[i][boardSizeLength] = "|";
            }

            // Set the - symbol at the top horizontal grid border
            for (int j = boardSizeLength + 1; j < printGridSize - 1; j++) {
                printGrid[0][j] = " - ";
            }

            // Set the | symbol at the right vertical grid border
            for (int i = 1; i < printGridSize - boardSizeLength - 1; i++) {
                printGrid[i][printGridSize - 1] = "|";
            }

            // Set the - symbol at the bottom horizontal grid border
            for (int j = boardSizeLength + 1; j < printGridSize - 1; j++) {
                printGrid[printGridSize - boardSizeLength - 1][j] = " - ";
            }

            // Initialize all chess board positions with ·
            for (int i = 1; i <= boardSize; i++) {
                for (int j = boardSizeLength + 1; j < printGridSize - 1; j++) {
                    printGrid[i][j] = " · ";
                }
            }

            // Add the queens positions to the board
            for (int q = 0; q < solution.length; q++) {
                printGrid[boardSize - q][boardSizeLength + 1 + solution[q]] = " Q ";
            }

            // Print the generated grid
            for (int i = 0; i < printGridSize; i++) {
                for (int j = 0; j < printGridSize; j++) {
                    System.out.print(printGrid[i][j]);
                }
                System.out.println();
            }

            System.out.print("\n\n");
        }

    }
}
