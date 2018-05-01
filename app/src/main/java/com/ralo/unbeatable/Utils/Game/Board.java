package com.ralo.unbeatable.Utils.Game;

import com.ralo.unbeatable.Activities.MainActivity;
import com.ralo.unbeatable.R;
import com.ralo.unbeatable.UnbeatableApp;

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


    public Board() {
        board = new State[BOARD_SIZE][BOARD_SIZE];
        movesAvailable = new HashSet<>();
        reset();
    }


    private void initialize () {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = State.Blank;
            }
        }

        movesAvailable.clear();

        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            movesAvailable.add(i);
        }
    }


    public void reset () {
        moveCount = 0;
        gameOver = false;
        playersTurn = State.X;
        winner = State.Blank;
        initialize();
    }

    public boolean move (int index) {
        return move(index% BOARD_SIZE, index/ BOARD_SIZE);
    }


    private boolean move (int x, int y) {


        if (board[y][x] == State.Blank) {
            board[y][x] = playersTurn;
        } else {
            return false;
        }

        moveCount++;
        movesAvailable.remove(y * BOARD_SIZE + x);

        if (moveCount == BOARD_SIZE * BOARD_SIZE) {
            winner = State.Blank;
            gameOver = true;
        }

        checkRow(y);
        checkColumn(x);
        checkDiagonalFromTopLeft(x, y);
        checkDiagonalFromTopRight(x, y);

        playersTurn = (playersTurn == State.X) ? State.O : State.X;
        return true;
    }


    public boolean isGameOver () {
        return gameOver;
    }


    public State[][] toArray() {
        return board.clone();
    }


    public State getTurn () {
        return playersTurn;
    }



    public String getWinnerString(){
        if(getWinner().equals(State.Blank))
            return UnbeatableApp.getCurrentActivity().getString(R.string.drawMessage);
        else if(getWinner().equals(State.O))
            return UnbeatableApp.getCurrentActivity().getString(R.string.loseMessage);
        else if(getWinner().equals(State.X) && MainActivity.isUnbeatable() && MainActivity.isVersusComputer())
            return UnbeatableApp.getCurrentActivity().getString(R.string.winMessageUnbeatable);
        else if(getWinner().equals(State.X) && !MainActivity.getSwitchState())
            return UnbeatableApp.getCurrentActivity().getString(R.string.winMessageRandom);
        else
            return UnbeatableApp.getCurrentActivity().getString(R.string.error);
    }
    public State getWinner () {
        return winner;
    }

    public HashSet<Integer> getAvailableMoves () {
        return movesAvailable;
    }

    private void checkRow (int row) {
        for (int i = 1; i < BOARD_SIZE; i++) {
            if (board[row][i] != board[row][i-1]) {
                break;
            }
            if (i == BOARD_SIZE -1) {
                winner = playersTurn;
                gameOver = true;
            }
        }
    }


    private void checkColumn (int column) {
        for (int i = 1; i < BOARD_SIZE; i++) {
            if (board[i][column] != board[i-1][column]) {
                break;
            }
            if (i == BOARD_SIZE -1) {
                winner = playersTurn;
                gameOver = true;
            }
        }
    }


    private void checkDiagonalFromTopLeft (int x, int y) {
        if (x == y) {
            for (int i = 1; i < BOARD_SIZE; i++) {
                if (board[i][i] != board[i-1][i-1]) {
                    break;
                }
                if (i == BOARD_SIZE -1) {
                    winner = playersTurn;
                    gameOver = true;
                }
            }
        }
    }

    private void checkDiagonalFromTopRight (int x, int y) {
        if (BOARD_SIZE -1-x == y) {
            for (int i = 1; i < BOARD_SIZE; i++) {
                if (board[BOARD_SIZE -1-i][i] != board[BOARD_SIZE -i][i-1]) {
                    break;
                }
                if (i == BOARD_SIZE -1) {
                    winner = playersTurn;
                    gameOver = true;
                }
            }
        }
    }


    public Board getDeepCopy () {
        Board board = new Board();

        for (int i = 0; i < board.board.length; i++) {
            board.board[i] = this.board[i].clone();
        }

        board.playersTurn = this.playersTurn;
        board.winner = this.winner;
        board.movesAvailable = new HashSet<>();
        board.movesAvailable.addAll(this.movesAvailable);
        board.moveCount = this.moveCount;
        board.gameOver = this.gameOver;
        return board;
    }
}
