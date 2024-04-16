package tictactoe.core.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.core.board.Board;
import tictactoe.core.board.Board.State;

public class AlgorithmTest {
    private Board board;

    public AlgorithmTest() {
    }

    @BeforeEach
    public void setUp() {
        this.board = new Board();
    }

    @Test()
    public void testRun_InvalidMaxPly(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AlphaBetaPruning.run(State.X, this.board, 0.0);
        });
    }

    @Test
    public void testScore_PlayerXWins() {
        this.board.move(0, 0);
        this.board.move(1, 0);
        this.board.move(0, 1);
        this.board.move(1, 1);
        this.board.move(0, 2);
        Assertions.assertEquals(10L, (long)AlphaBetaPruning.score(State.X, this.board));
    }

    @Test
    public void testScore_PlayerOWins() {
        this.board.move(1, 0);
        this.board.move(0, 0);
        this.board.move(1, 1);
        this.board.move(0, 1);
        this.board.move(2, 0);
        this.board.move(0, 2);
        Assertions.assertEquals(-10L, (long)AlphaBetaPruning.score(State.X, this.board));
    }

    @Test
    public void testRun_PlayerXWins() {
        this.board.move(0, 0);
        this.board.move(1, 0);
        this.board.move(0, 1);
        this.board.move(1, 1);
        this.board.move(0, 2);
        AlphaBetaPruning.run(State.X, this.board, 9.0);
        Assertions.assertTrue(this.board.isGameOver());
        Assertions.assertEquals(State.X, this.board.getWinner());
    }

    @Test
    public void testRun_PlayerOWins() {
        this.board.move(1, 0);
        this.board.move(0, 0);
        this.board.move(1, 1);
        this.board.move(0, 1);
        this.board.move(2, 0);
        this.board.move(0, 2);
        AlphaBetaPruning.run(State.X, this.board, 9.0);
        Assertions.assertTrue(this.board.isGameOver());
        Assertions.assertEquals(State.O, this.board.getWinner());
    }
}
