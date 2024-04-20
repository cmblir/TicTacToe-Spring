package tictactoe.core.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    public Board board;

    public BoardTest() {
    }

    @BeforeEach
    public void setUp() {
        this.board = new Board();
    }

    @Test()
    public void testReset(){
        this.board.move(0, 0);
        this.board.reset();
        Assertions.assertEquals(State.X, this.board.getTurn());
        Assertions.assertFalse(this.board.isGameOver());
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.board.getWinner();
        });
        Assertions.assertEquals(9L, (long)this.board.getAvailableMoves().size());
    }

    @Test
    public void testMove_ValidMove() {
        Assertions.assertTrue(this.board.move(0, 0));
        Assertions.assertEquals(State.O, this.board.getTurn());
    }

    @Test
    public void testMove_InvalidMove() {
        this.board.move(0, 0);
        Assertions.assertFalse(this.board.move(0, 0));
        Assertions.assertEquals(State.O, this.board.getTurn());
    }

    @Test()
    public void testMove_GameOver(){
        this.board.move(0, 0);
        this.board.move(1, 0);
        this.board.move(0, 1);
        this.board.move(1, 1);
        this.board.move(0, 2);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.board.move(1, 2);
        });
    }

    @Test()
    public void testGetDeepCopy() throws IllegalStateException{
        Board copy = this.board.getDeepCopy();
        Assertions.assertNotSame(this.board, copy);
        Assertions.assertArrayEquals(this.board.toArray(), copy.toArray());
        Assertions.assertEquals(this.board.getTurn(), copy.getTurn());
        Assertions.assertEquals(this.board.isGameOver(), copy.isGameOver());
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.board.getWinner();
        });
        Assertions.assertThrows(IllegalStateException.class, () -> {
            copy.getWinner();
        });
        Assertions.assertEquals(this.board.getAvailableMoves(), copy.getAvailableMoves());
    }

    @Test
    public void testCheckRow_WinningRow() {
        this.board.move(0, 0);
        this.board.move(0, 1);
        this.board.move(1, 0);
        this.board.move(1, 1);
        this.board.move(2, 0);
        Assertions.assertTrue(this.board.isGameOver());
        Assertions.assertEquals(State.X, this.board.getWinner());
    }

    @Test()
    public void testCheckRow_NonWinningRow(){
        this.board.move(0, 0);
        this.board.move(0, 1);
        this.board.move(1, 0);
        this.board.move(1, 1);
        this.board.move(2, 1);
        Assertions.assertFalse(this.board.isGameOver());
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.board.getWinner();
        });
    }

    @Test
    public void testCheckCol_WinningCol() {
        this.board.move(0, 0);
        this.board.move(1, 0);
        this.board.move(0, 1);
        this.board.move(1, 1);
        this.board.move(0, 2);
        Assertions.assertTrue(this.board.isGameOver());
        Assertions.assertEquals(State.X, this.board.getWinner());
    }

    @Test()
    public void testCheckCol_NonWinningCol(){
        this.board.move(0, 0);
        this.board.move(1, 0);
        this.board.move(0, 1);
        this.board.move(1, 1);
        this.board.move(2, 1);
        Assertions.assertFalse(this.board.isGameOver());
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.board.getWinner();
        });
    }

    @Test
    public void testCheckDiagonalFromTopLeft_WinningDiagonal() {
        this.board.move(0, 0);
        this.board.move(0, 1);
        this.board.move(1, 1);
        this.board.move(0, 2);
        this.board.move(2, 2);
        Assertions.assertTrue(this.board.isGameOver());
        Assertions.assertEquals(State.X, this.board.getWinner());
    }

    @Test()
    public void testCheckDiagonalFromTopLeft_NonWinningDiagonal(){
        this.board.move(0, 0);
        this.board.move(0, 1);
        this.board.move(1, 1);
        this.board.move(0, 2);
        this.board.move(2, 0);
        Assertions.assertFalse(this.board.isGameOver());
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.board.getWinner();
        });
    }

    @Test
    public void testCheckDiagonalFromTopRight_WinningDiagonal() {
        this.board.move(0, 2);
        this.board.move(0, 1);
        this.board.move(1, 1);
        this.board.move(0, 0);
        this.board.move(2, 0);
        Assertions.assertTrue(this.board.isGameOver());
        Assertions.assertEquals(State.X, this.board.getWinner());
    }

    @Test()
    public void testCheckDiagonalFromTopRight_NonWinningDiagonal(){
        this.board.move(0, 2);
        this.board.move(0, 1);
        this.board.move(1, 1);
        this.board.move(0, 0);
        this.board.move(1, 0);
        Assertions.assertFalse(this.board.isGameOver());
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.board.getWinner();
        });
    }

    @Test
    public void testToString() {
        boolean moved1 = this.board.move(0, 0);
        System.out.println(this.board.toString());
    }
}

