package com.ralo.unbeatable.Utils.Game;

import java.util.HashSet;

public class Board {
    static final int BOARD_SIZE = 3;

    public enum State {Blank, X, O}
    private State[][] board;
    private State playersTurn;
    private State winner;
    private HashSet<Integer> movesAvailable;
    private int moveCount;
    private boolean gameOver;

    Board (){
        board = new State[BOARD_SIZE][BOARD_SIZE];
        movesAvailable = new HashSet<>();
        reset();
    }

    private void initialize(){
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int column = 0; column < BOARD_SIZE; column++){
                board[row][column] = State.Blank;
            }
        }
        movesAvailable.clear();
        for(int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++){
            movesAvailable.add(i);
        }
    }
    private void reset(){
        moveCount = 0;
        gameOver = false;
        playersTurn = State.X;
        winner = State.Blank;
        initialize();
    }
}
