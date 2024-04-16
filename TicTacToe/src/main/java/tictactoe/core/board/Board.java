package tictactoe.core.board;

import java.util.HashSet;

public class Board {
    public static final int size = 3;
    private State[][] board = new State[3][3];
    private State playerTurn;
    private State winner;
    private HashSet<Integer> movesAvailable = new HashSet();
    private int moveCount;
    private boolean gameOver;

    public Board() {
        this.reset();
    }

    private void initialize() {
        int i;
        for(i = 0; i < 3; ++i) {
            for(int col = 0; col < 3; ++col) {
                this.board[i][col] = Board.State.Blank;
            }
        }

        this.movesAvailable.clear();

        for(i = 0; i < 9; ++i) {
            this.movesAvailable.add(i);
        }

    }

    public void reset() {
        this.moveCount = 0;
        this.gameOver = false;
        this.playerTurn = Board.State.X;
        this.winner = Board.State.Blank;
        this.initialize();
    }

    public boolean move(int index) {
        return this.move(index % 3, index / 3);
    }

    public boolean move(int x, int y) {
        if (this.gameOver) {
            throw new IllegalStateException("틱택토는 끝났습니다. 움직이지 않으면 플레이할 수 있습니다.");
        } else if (this.board[y][x] == Board.State.Blank) {
            this.board[y][x] = this.playerTurn;
            ++this.moveCount;
            this.movesAvailable.remove(y * 3 + x);
            if (this.moveCount == 9) {
                this.winner = Board.State.Blank;
                this.gameOver = true;
            }

            this.checkRow(y);
            this.checkCol(x);
            this.checkDiagonalFromTopLeft(x, y);
            this.checkDIagonalFromTopRight(x, y);
            this.playerTurn = this.playerTurn == Board.State.X ? Board.State.O : Board.State.X;
            return true;
        } else {
            return false;
        }
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public State[][] toArray() {
        return (State[][])this.board.clone();
    }

    public State getTurn() {
        return this.playerTurn;
    }

    public State getWinner() {
        if (this.gameOver) {
            return this.winner;
        } else {
            throw new IllegalStateException("틱택토가 아직 끝나지 않았습니다.");
        }
    }

    public HashSet<Integer> getAvailableMoves() {
        return this.movesAvailable;
    }

    private void checkRow(int row) {
        for(int i = 1; i < 3 && this.board[row][i] == this.board[row][i - 1]; ++i) {
            if (i == 2) {
                this.winner = this.playerTurn;
                this.gameOver = true;
            }
        }

    }

    public void checkCol(int col) {
        for(int i = 1; i < 3 && this.board[i][col] == this.board[i - 1][col]; ++i) {
            if (i == 2) {
                this.winner = this.playerTurn;
                this.gameOver = true;
            }
        }

    }

    private void checkDiagonalFromTopLeft(int x, int y) {
        if (x == y) {
            for(int i = 1; i < 3 && this.board[i][i] == this.board[i - 1][i - 1]; ++i) {
                if (i == 2) {
                    this.winner = this.playerTurn;
                    this.gameOver = true;
                }
            }
        }

    }

    private void checkDIagonalFromTopRight(int x, int y) {
        if (2 - x == y) {
            for(int i = 1; i < 3 && this.board[2 - i][i] == this.board[3 - i][i - 1]; ++i) {
                if (i == 2) {
                    this.winner = this.playerTurn;
                    this.gameOver = true;
                }
            }
        }

    }

    public Board getDeepCopy() {
        Board board = new Board();
        int boardSize = board.board.length;

        for(int i = 0; i < boardSize; ++i) {
            board.board[i] = (State[])this.board[i].clone();
        }

        board.playerTurn = this.playerTurn;
        board.winner = this.winner;
        board.moveCount = this.moveCount;
        board.movesAvailable = new HashSet();
        board.movesAvailable.addAll(this.movesAvailable);
        board.gameOver = this.gameOver;
        return board;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int y = 0; y < 3; ++y) {
            for(int x = 0; x < 3; ++x) {
                if (this.board[y][x] == Board.State.Blank) {
                    sb.append("-");
                } else {
                    sb.append(this.board[y][x].name());
                }

                sb.append(" ");
            }

            if (y != 2) {
                sb.append("\n");
            }
        }

        return new String(sb);
    }

    public static enum State {
        Blank,
        X,
        O;

        private State() {
        }
    }
}