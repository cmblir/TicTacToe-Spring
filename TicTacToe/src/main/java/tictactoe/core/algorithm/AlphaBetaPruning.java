package tictactoe.core.algorithm;

import tictactoe.core.board.Board;
import tictactoe.core.board.State;

import java.util.Iterator;

public class AlphaBetaPruning {
    private static double maxPly;

    private AlphaBetaPruning() {
    }

    public static void run(State player, Board board, double maxPly) {
        if (maxPly < 1.0) {
            throw new IllegalArgumentException("최대 깊이는 무조건 0보다 커야합니다.");
        } else {
            AlphaBetaPruning.maxPly = maxPly;
            alphaBetaPruning(player, board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
        }
    }

    private static int alphaBetaPruning(State player, Board board, double alpha, double beta, int currentPly) {
        if ((double)(currentPly++) != maxPly && !board.isGameOver()) {
            return board.getTurn() == player ? getMax(player, board, alpha, beta, currentPly) : getMin(player, board, alpha, beta, currentPly);
        } else {
            return score(player, board);
        }
    }

    private static int getMax(State player, Board board, double alpha, double beta, int currentPly) {
        int indexOfBestMove = -1;
        Iterator var8 = board.getAvailableMoves().iterator();

        while(var8.hasNext()) {
            Integer theMove = (Integer)var8.next();
            Board modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove);
            int score = alphaBetaPruning(player, modifiedBoard, alpha, beta, currentPly);
            if ((double)score > alpha) {
                alpha = (double)score;
                indexOfBestMove = theMove;
            }

            if (alpha >= beta) {
                break;
            }
        }

        if (indexOfBestMove != -1) {
            board.move(indexOfBestMove);
        }

        return (int)alpha;
    }

    private static int getMin(State player, Board board, double alpha, double beta, int currentPly) {
        int indexOfBestMove = -1;
        Iterator var8 = board.getAvailableMoves().iterator();

        while(var8.hasNext()) {
            Integer theMove = (Integer)var8.next();
            Board modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove);
            int score = alphaBetaPruning(player, modifiedBoard, alpha, beta, currentPly);
            if ((double)score < beta) {
                beta = (double)score;
                indexOfBestMove = theMove;
            }

            if (alpha >= beta) {
                break;
            }
        }

        if (indexOfBestMove != -1) {
            board.move(indexOfBestMove);
        }

        return (int)beta;
    }

    public static int score(State player, Board board) {
        if (player == State.Blank) {
            throw new IllegalArgumentException("플레이어는 무조건 X 또는 O입니다.");
        } else {
            State opponent = player == State.X ? State.O : State.X;
            if (board.isGameOver() && board.getWinner() == player) {
                return 10;
            } else {
                return board.isGameOver() && board.getWinner() == opponent ? -10 : 0;
            }
        }
    }
}

