package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    // Tic-Tac-Toe with AI (Java)/task/src/tictactoe/Main.java

    // We need to create a class TicTacToe
    // We have three fields: board, currentPlayer and gameOver
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;

    // We have one constructor: TicTacToe(), which initializes the board, currentPlayer and gameOver fields
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        initializeBoard();
    }

    // The initializeBoard() method initializes the board. It sets all the
    // characters to ' ' making the board empty.
    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    // the printBoard() method prints the board. It prints the board in a 3x3 grid.
    public void printBoard() {
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

    // the makeHumanMove() method makes a move for the human player. It asks
    // the user to enter a row and a column. If the move is valid, it sets the character at that position to 'X'.
    // Then it sets the current player to 'O'.
    private void makeHumanMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            System.out.println("Invalid move. Try again.");
            return;
        }

        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // the makeComputerMove() method makes a move for the computer player. It
    // generates a random move row and column. If the character is ' ' it
    // sets the character to 'O'. Then it sets the current player to 'X'.
    private void makeComputerMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');

        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // The isBoardFull() method checks if the board is full. It returns true if the board is full and false otherwise.
    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // The hasPlayerWon() method checks if the player has won. It returns true if the player has won and false otherwise.
    // Note: We don't need to pass in the board because we already have it in
    // the constructor. We only need to pass in the currentPlayer.
    private boolean hasPlayerWon(char player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    // the play() method starts the game. It is one of two methods that are public.
    // We use a while loop within the method to keep playing the game.
    // We incorporate all the methods in the TicTacToe class.
    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            printBoard();
            if (currentPlayer == 'X') {
                System.out.print("Enter the coordinates: > ");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;
                makeHumanMove(row, col);
            } else {
                System.out.println("Making move level \"easy\"");
                makeComputerMove();
            }

            if (hasPlayerWon('X')) {
                printBoard();
                System.out.println("X wins");
                gameOver = true;
            } else if (hasPlayerWon('O')) {
                printBoard();
                System.out.println("O wins");
                gameOver = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a tie");
                gameOver = true;
            }
        }

        scanner.close();
    }
}
