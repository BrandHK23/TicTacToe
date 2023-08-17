package game.test;

import org.game.TicTacToe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    //Prueba oara "makeMove" con movimiento valido
    //Verifica que makeMove permita realizar un movimiento válido en el tablero, movimientos validos deben devolver true
    @Test
    public void testValidMove() {
        TicTacToe game = new TicTacToe();
        assertTrue(game.makeMove(0, 0));
    }

    //Prueba para "makeMove" con movimiento invalido(Casilla ocupada)
    //Verifica que makeMove no permita realizar un movimiento en una casilla ocupada, movimientos invalidos deben devolver false
    @Test
    public void testInvalidMoveOccupiedCell() {
        TicTacToe game = new TicTacToe();
        game.makeMove(0, 0);
        assertFalse(game.makeMove(0, 0));
    }

    //Prueba para "makeMove" con movimiento invalido(Fila fuera de rango)
    //Verifica que makeMove no permita realizar un movimiento fuera de rango, movimientos invalidos deben devolver false
    //Los valores de fila y columna deben estar entre 0 y 2 (inclusive) para ser válidos.
    @Test
    public void testInvalidMoveOutOfBounds() {
        TicTacToe game = new TicTacToe();
        assertFalse(game.makeMove(-1, 1));
        assertFalse(game.makeMove(2,3));
    }

    //Prueba para "checkForWin" con ganador en fila
    //Verifica que checkForWin detecte un ganador en una fila
    @Test
    public void testCheckForWinRow() {
        TicTacToe game = new TicTacToe();
        game.makeMove(0, 0);
        game.makeMove(1, 0);
        game.makeMove(0, 1);
        game.makeMove(1, 1);
        game.makeMove(0, 2);
        assertTrue(game.checkForWin());
    }

    //Prueba de fallo
    @Test
    public void testCheckForWinRow_Failure() {
        TicTacToe game = new TicTacToe();
        game.makeMove(0, 0);
        game.makeMove(1, 0);
        game.makeMove(0, 1);
        game.makeMove(1, 1);// Cambiamos esta posición
        game.makeMove(0,2); //Elimiar esta linea para generar el error
        assertTrue(game.checkForWin());
    }

    //Prueba para "checkForWin" con empate
    //Verifica que checkForWin detecte un empate, cuando no hay ganador y todas las casillas estan ocupadas
    @Test
    public void testCheckForDraw(){
        TicTacToe game = new TicTacToe();
        game.makeMove(0, 0);
        game.makeMove(0, 1);
        game.makeMove(0, 2);
        game.makeMove(1, 0);
        game.makeMove(1, 2);
        game.makeMove(1, 1);
        game.makeMove(2, 0);
        game.makeMove(2, 2);
        game.makeMove(2, 1);
        assertTrue(game.checkForDraw());
    }

    //Prueba para "getCurrentPlayer"
    // Verifica que getCurrentPlayer devuelva el jugador actual
    @Test
    public void testGetCurrentPlayer(){
        TicTacToe game = new TicTacToe();
        assertEquals('X', game.getCurrentPlayer());
    }
}
