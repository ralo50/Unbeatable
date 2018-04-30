package com.ralo.unbeatable.Utils.Game;


import com.ralo.unbeatable.Activities.MainActivity;
import com.ralo.unbeatable.R;
import com.ralo.unbeatable.Utils.Players.Algorithms;
import static com.ralo.unbeatable.Utils.Game.Board.BOARD_SIZE;


public class GameFlow {
    public static void playMove(int move) {
        if (!MainActivity.board.isGameOver() && move != -1) {
            boolean validMove = MainActivity.board.move(move);
            if (validMove && !MainActivity.board.isGameOver() && MainActivity.isUnbeatable()) {
                Algorithms.alphaBetaPruning(MainActivity.board);
            }
            else if (validMove && !MainActivity.board.isGameOver() && !MainActivity.isUnbeatable()) {
                Algorithms.randomPlayer(MainActivity.board);
            }
        }
        updateBoard();
    }

    public static void updateBoard(){
        Board.State[][] boardArray = MainActivity.board.toArray();
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (boardArray[x][y] == Board.State.X) {
                    int movePosition = x * BOARD_SIZE + y;
                    MainActivity.buttons.get(movePosition).setBackgroundResource(R.drawable.x);
                } else if (boardArray[x][y] == Board.State.O) {
                    int movePosition = x * BOARD_SIZE + y;
                    MainActivity.buttons.get(movePosition).setBackgroundResource(R.drawable.o);
                }
                else{
                    int movePosition = x * BOARD_SIZE + y;
                    MainActivity.buttons.get(movePosition).setBackgroundResource(0);
                }
            }
        }
    }
}