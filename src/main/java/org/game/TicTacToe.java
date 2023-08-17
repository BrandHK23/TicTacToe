package org.game;

import java.util.Scanner;

public class TicTacToe {
    private char[][] board; // Arreglo bidimensional de caracteres que representa el tablero
    private char currentPlayer; // Un caracter que representa el jugador actual (X u O)

    public TicTacToe() { //Constructor, inicializa el tablero y el jugador actual como X
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() { //Método que inicializa el tablero con guiones (-) para indicar que está vacío
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    public void printBoard() { //Método que imprime el tablero en consola con  los simbolos de cada jugador
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(int row, int col) { //Método que recibe la fila y columna donde se quiere hacer el movimiento
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '-') {
            return false; // Invalid move
        }

        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        return true;
    }


    public boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();
    }

    private boolean checkRowsForWin() { //Método que revisa si hay un ganador en las filas
        for (int row = 0; row < 3; row++) {
            if (checkRowCol(board[row][0], board[row][1], board[row][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() { //Método que revisa si hay un ganador en las columnas
        for (int col = 0; col < 3; col++) {
            if (checkRowCol(board[0][col], board[1][col], board[2][col])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() { //Método que revisa si hay un ganador en las diagonales
        return checkRowCol(board[0][0], board[1][1], board[2][2]) ||
                checkRowCol(board[0][2], board[1][1], board[2][0]);
    }

    private boolean checkRowCol(char c1, char c2, char c3) { //Método que revisa si hay un ganador en una fila o columna
        return c1 != '-' && c1 == c2 && c2 == c3;
    }

    public boolean checkForDraw() { //Método que revisa si hay un empate
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false; // There's an empty cell, so the game is not a draw yet
                }
            }
        }
        return true; // All cells are filled, so it's a draw
    }

    public char getCurrentPlayer() { //Método que devuelve el jugador actual
        return currentPlayer;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.printBoard();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Player " + game.getCurrentPlayer() + ", enter row (0-2):");
            int row = scanner.nextInt();

            System.out.println("Player " + game.getCurrentPlayer() + ", enter column (0-2):");
            int col = scanner.nextInt();

            if (game.makeMove(row, col)) {
                game.printBoard();

                if (game.checkForWin()) {
                    System.out.println("Player " + (game.getCurrentPlayer() == 'X' ? 'O' : 'X') + " wins!");
                    break;
                } else if (game.checkForDraw()) {
                    System.out.println("It's a draw!");
                    break;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}
