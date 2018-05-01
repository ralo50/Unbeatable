package com.ralo.unbeatable.Utils.Players;

import com.ralo.unbeatable.Utils.Game.Board;

public class Algorithms {
    private Algorithms(){}
    public static void alphaBetaPruning(Board board){
        AlphaBetaPruning.run(board.getTurn(), board, Double.POSITIVE_INFINITY);
    }

    public static void randomPlayer(Board board){
        RandomPlayer.run(board);
    }

    public static void twoPlayers() {
    }
}
