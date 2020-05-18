package tictactoe;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static boolean gameNotFinish = false;
    private static boolean winsX = false;
    private static boolean winsO = false;
    private static boolean impossible = false;
    private static final Scanner scanner = new Scanner(System.in);
    private static String[][] matrix = new String[3][3];

    public static boolean gameWin(String ch) {
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0].equals(ch) && matrix[i][1].equals(ch) && matrix[i][2].equals(ch))
                return true;
            if (matrix[0][i].equals(ch) && matrix[1][i].equals(ch) && matrix[2][i].equals(ch))
                return true;
        }
        if (matrix[0][0].equals(ch) && matrix[1][1].equals(ch) && matrix[2][2].equals(ch))
            return true;
        return matrix[0][2].equals(ch) && matrix[1][1].equals(ch) && matrix[2][0].equals(ch);
    }

    public static void notFinish() {
        if (!winsX && !winsO) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j].equals(" ")) {
                        gameNotFinish = true;
                        return;
                    } else gameNotFinish = false;
                }
            }
        }
    }

    public static void impossibleGame() {
        if (winsO && winsX)
            impossible = true;
        else if (gameNotFinish) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j].equals(" ")) {
                        if (matrix[i - 1 < 0 ? 2 : i - 1][j].equals(matrix[i + 1 > 2 ? 0 : i + 1][j])) {
                            impossible = true;
                            return;
                        }
                        if (matrix[i][j - 1 < 0 ? 2 : j - 1].equals(matrix[i][j + 1 > 2 ? 0 : j + 1])) {
                            impossible = true;
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void stateGame() {
        printMatrix();
        winsX = gameWin("X");
        winsO = gameWin("O");
        notFinish();
        impossibleGame();
        printResult();
    }

    public static void printResult() {
        if (impossible)
            System.out.println("Impossible");
        else if (winsX)
            System.out.println("X wins");
        else if (winsO)
            System.out.println("O wins");
        else if (gameNotFinish)
            System.out.println("Game not finished");
        else
            System.out.println("Draw");
    }

    public static void printMatrix() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void enterCoordinates(String ch) {
        while (true) {
            System.out.println("Enter the coordinates:");
            try {
                int j = scanner.nextInt();
                int i = scanner.nextInt();
                if ((i < 1 || i > 3) || (j < 1 || j > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                if (!matrix[Math.abs(i - 3)][j - 1].equals(" "))
                    System.out.println("This cell is occupied! Choose another one!");
                else {
                    matrix[Math.abs(i - 3)][j - 1] = ch;
                    return;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
            }
        }
    }

    public static void playGame() {
        printMatrix();
        while (true) {
           enterCoordinates("X");
           printMatrix();
           winsX = gameWin("X");
           if (winsX) {
               System.out.println("X wins");
               return;
           }
           notFinish();
           if (!gameNotFinish) {
               System.out.println("Draw");
               return;
           }
           enterCoordinates("O");
           printMatrix();
           winsO = gameWin("O");
           if (winsO) {
               System.out.println("O wins");
               return;
           }
           notFinish();
           if (!gameNotFinish) {
               System.out.println("Draw");
               return;
           }
        }
    }

    public static void main(String[] args) {
        // write your code here
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = " ";
            }
        }
        playGame();
    }
}
