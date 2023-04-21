package com.scaler.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimesion;
    private List<List<Cell>> board;

    Board(int dimension) {
        this.board = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            //List<List<Cell>>
            this.board.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++) {
                this.board.get(i).add(new Cell(i,j));
            }
        }
    }

    public void display() {
        //Print the board.
        for(int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                if (board.get(i).get(j).getCellState().equals(CellState.FILLED)) {
                    System.out.print("| " + board.get(i).get(j).getPlayer().getSymbol() + " |");
                } else {
                    System.out.print("|    |");
                }
            }
            System.out.println();
        }
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
