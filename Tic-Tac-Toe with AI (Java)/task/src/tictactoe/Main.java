package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        char[][] board = createBoard();
//        printBoard(board);
//
//        getUserCoordinates(board, charStartWith(board));
//        System.out.println(isGameOver(board));
    TicTacToe game = new TicTacToe();
    game.play();

    }

    // Get user input to create the board
    public static char[][] createBoard() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the cells: > ");
        String input = " ".repeat(9);
//        input = input.toUpperCase().replaceAll("_", " ");

        char[][] board = new char[3][3];
        int index = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = input.charAt(index);
                index++;
            }
        }
        return board;
    }
    
    // Checks what character the user should start with given the board with
    // X's and O's
    private static char charStartWith(char[][] board) {

        int countX = 0;
        int countO = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 'X') {
                    countX++;
                }
                if (board[row][col] == 'O') {
                    countO++;
                }
            }
        }
        return countX > countO ? 'O' : 'X';
    }

    // Prints the board given the 2-dimensional array
    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    // Get the user coordinates to add an X or an O to the board
    public static void getUserCoordinates(char[][] board, char charStart) {
        Scanner scanner = new Scanner(System.in);

        boolean inputCorrect = false;
        // While loop to check if the user entered correct coordinates
        while (!inputCorrect) {
            System.out.print("Enter the coordinates: > ");
            try {
                // Try-Catch block to catch errors in code
                int num1 = Integer.parseInt(scanner.next()) - 1;
                int num2 = Integer.parseInt(scanner.next()) - 1;
                if ((num1 > 2 || num1 < 0) || (num2 > 2 || num2 < 0)) {
                    // Message if user enters number outside array range
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (board[num1][num2] == ' ') {
                    // add character to the board and stop the loop
                    board[num1][num2] = charStart;
                    inputCorrect = true;
                } else {
                    // Message if cell is occupied
                    scanner.nextLine(); // we use this to clear the scanner
                    // input and don't have an infinite loop
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (Exception e) {
                // catch for catching an exception, particularly when user
                // enters
                scanner.nextLine(); // we use this to clear the scanner
                // input and don't have an infinite loop
                System.out.println("You should enter numbers!");
            }
        }
        printBoard(board);
    }

    // Check for three in a row
    public static String isGameOver(char[][] board) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != ' ' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return String.format("%c wins", board[row][0]); // Row win
            }
        }
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != ' ' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return String.format("%c wins", board[0][col]); // Column win
            }
        }
        // Check diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return String.format("%c wins", board[0][0]); // Diagonal win
            // (top-left to bottom-right)
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return String.format("%c wins", board[0][2]); // Diagonal win
            // (top-right to bottom-left)
        }
        // Check if the game ended in a draw
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    // Found an empty cell, game is not a draw
                    return "Game not finished";
                }
            }
        }
        // If nothing else it ended in a draw
        return "Draw";
    }
}


