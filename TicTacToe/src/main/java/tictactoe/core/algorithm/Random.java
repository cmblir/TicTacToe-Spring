package tictactoe.core.algorithm;

import tictactoe.core.board.Board;

import java.util.Iterator;

class Random {
    private Random() {
    }

    static void run(Board board) {
        int[] moves = new int[board.getAvailableMoves().size()];
        int index = 0;

        Integer item;
        for(Iterator var3 = board.getAvailableMoves().iterator(); var3.hasNext(); moves[index++] = item) {
            item = (Integer)var3.next();
        }

        int randomMove = moves[(new java.util.Random()).nextInt(moves.length)];
        board.move(randomMove);
    }
}
