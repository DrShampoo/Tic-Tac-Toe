package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split("");
        System.out.println("---------");
        for (int i = 0; i < line.length; i += 3) {
            System.out.printf("| %s %s %s |", line[i], line[i+1], line[i+2]);
            System.out.println();
        }
        System.out.println("---------");
    }
}
