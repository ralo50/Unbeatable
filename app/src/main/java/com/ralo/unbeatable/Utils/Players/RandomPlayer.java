package com.ralo.unbeatable.Utils.Players;

import com.ralo.unbeatable.Utils.Game.Board;

import java.util.Random;

public class RandomPlayer {
    public static void run(Board board) {
        int[] moves = new int[board.getAvailableMoves().size()];
        int index = 0;

        for(Integer item : board.getAvailableMoves()){
            moves[index++] = item;
        }

        int randomMove = moves[new Random().nextInt(moves.length)];
        board.move(randomMove);
    }
}
