package com.scaler.tictactoe.models;

import javax.swing.*;
import java.util.Scanner;

public class Player {
    private char symbol;
    private String name;
    private PlayerType type;

    public Player(char symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public Move decideMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        System.out.println("Enter the row number for the move: ");
        row = scanner.nextInt();

        System.out.println("Enter the col number for the move: ");
        col = scanner.nextInt();

        return new Move(this, new Cell(row, col));
    }
}
