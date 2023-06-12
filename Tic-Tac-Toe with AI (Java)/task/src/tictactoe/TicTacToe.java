package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

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

    private void makeHumanMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            System.out.println("Invalid move. Try again.");
            return;
        }

        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

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
