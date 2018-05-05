package com.ralo.unbeatable.Utils.Players;

import com.ralo.unbeatable.Activities.MainActivity;
import com.ralo.unbeatable.R;
import com.ralo.unbeatable.UnbeatableApp;
import com.ralo.unbeatable.Utils.Game.Board;

public class AlphaBetaPruning {
    private static double maxDepth;
    private static final double ALPHA = Double.NEGATIVE_INFINITY;
    private static final double BETA = Double.POSITIVE_INFINITY;

    private AlphaBetaPruning() {}

    static void run (Board.State player, Board board, double maxDepth) {
        int currentDepth = 0;

        AlphaBetaPruning.maxDepth = maxDepth;
        alphaBetaPruning(player, board, ALPHA, BETA, currentDepth);
    }


    private static int alphaBetaPruning (Board.State player, Board board, double alpha, double beta, int currentDepth) {
        if (currentDepth++ == maxDepth || board.isGameOver()) {
            return score(player, board, currentDepth);
        }

        if (board.getTurn() == player) {
            return getMax(player, board, alpha, beta, currentDepth);
        } else {
            return getMin(player, board, alpha, beta, currentDepth);
        }
    }

    private static int getMax (Board.State player, Board board, double alpha, double beta, int currentDepth) {
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Board modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove);
            int score = alphaBetaPruning(player, modifiedBoard, alpha, beta, currentDepth);

            if (score > alpha) {
                alpha = score;
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

    private static int getMin (Board.State player, Board board, double alpha, double beta, int currentDepth) {
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Board modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove);

            int score = alphaBetaPruning(player, modifiedBoard, alpha, beta, currentDepth);

            if (score < beta) {
                beta = score;
                indexOfBestMove = theMove;
            }

            if (alpha >= beta) {
                break;
            }
        }

        if (indexOfBestMove != -1) {
            board.move(indexOfBestMove);
        }
        return (int) beta;
    }
    private static int score (Board.State player, Board board, int currentDepth) {
        String currentDepthString = UnbeatableApp.getCurrentActivity().getString(R.string.currentDepthString) + (currentDepth - 1);
        MainActivity.gameResult.setText(currentDepthString);
        Board.State opponent = (player == Board.State.X) ? Board.State.O : Board.State.X;

        if (board.isGameOver() && board.getWinner() == player) {
            return 10 - currentDepth;
        } else if (board.isGameOver() && board.getWinner() == opponent) {
            return -10 + currentDepth;
        } else {
            return 0;
        }
    }
}