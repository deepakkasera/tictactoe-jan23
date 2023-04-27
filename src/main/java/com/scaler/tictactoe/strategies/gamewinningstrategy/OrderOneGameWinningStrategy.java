package com.scaler.tictactoe.strategies.gamewinningstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.Game;
import com.scaler.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy {
    // 3 * 3 Board -> 3 maps for rows, 3 maps for cols & 2 maps for diagonals.
    List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();

    List<HashMap<Character, Integer>> colSymbolCounts = new ArrayList<>();

    HashMap<Character, Integer> topRightSymbolCounts = new HashMap<>();

    HashMap<Character, Integer> topLeftSymbolCounts = new HashMap<>();

    public OrderOneGameWinningStrategy(int dimension) {
        for (int i = 0; i < dimension; i++) {
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }
    }

    boolean isCellOnTopLeftDiagonal(int row, int col) {
        return row == col;
    }

    boolean isCellOnTopRightDiagonal(int row, int col, int dimension) {
        return row + col == dimension - 1;
    }

    @Override
    public boolean checkWinner(Board board, Player lastMovedPlayer, Cell cell) {
        char symbol = cell.getPlayer().getSymbol();
        int row = cell.getRow();
        int col = cell.getCol();
        int dimension = board.getBoard().size();

        if (!rowSymbolCounts.get(row).containsKey(symbol)) {
            rowSymbolCounts.get(row).put(symbol, 0);
        }
        rowSymbolCounts.get(row).put(symbol, rowSymbolCounts.get(row).
                                                    get(symbol) + 1);

        if (!colSymbolCounts.get(col).containsKey(symbol)) {
            colSymbolCounts.get(col).put(symbol, 0);
        }
        colSymbolCounts.get(col).put(symbol, colSymbolCounts.get(col).
                get(symbol) + 1);

        if (isCellOnTopLeftDiagonal(row, col)) {
            if (!topLeftSymbolCounts.containsKey(symbol)) {
                topLeftSymbolCounts.put(symbol, 0);
            }
            topLeftSymbolCounts.put(symbol, topLeftSymbolCounts.get(symbol) + 1);
        }

        if (isCellOnTopRightDiagonal(row, col, dimension)) {
            if (!topRightSymbolCounts.containsKey(symbol)) {
                topRightSymbolCounts.put(symbol, 0);
            }
            topRightSymbolCounts.put(symbol, topRightSymbolCounts.get(symbol) + 1);
        }

        //If we are winning the game in the row or col
        if (rowSymbolCounts.get(row).get(symbol) == dimension ||
                colSymbolCounts.get(col).get(symbol) == dimension) {
            return true;
        }

        if (isCellOnTopLeftDiagonal(row, col) &&
                topLeftSymbolCounts.get(symbol) == dimension) {
            return true;
        }

        if (isCellOnTopRightDiagonal(row, col, dimension) &&
                topRightSymbolCounts.get(symbol) == dimension) {
            return true;
        }

        return false;
    }
}
