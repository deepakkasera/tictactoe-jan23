package com.scaler.tictactoe;

import com.scaler.tictactoe.Controller.GameController;
import com.scaler.tictactoe.Exceptions.InvalidGameException;
import com.scaler.tictactoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) throws InvalidGameException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Give the dimension of the board");
        int dimension = scanner.nextInt();

        System.out.println("Is there any bot player ? y/n");
        String isBotPlayer = scanner.next();

        List<Player> players = new ArrayList<>();
        int size = dimension - 1;

        if (isBotPlayer.equals("y")) {
            size = dimension - 2;
        }

        for (int i = 0; i < size; i++) {
            System.out.println("Enter the name for player number: " + i);
            String playerName = scanner.next();

            System.out.println("Enter the symbol for player number: " + i);
            String symbol = scanner.next();
            Player player = new Player(symbol.charAt(0), playerName);
            players.add(player);
        }

        if (isBotPlayer.equals("y")) {
            System.out.println("Enter the name for Bot: ");
            String playerName = scanner.next();

            System.out.println("Enter the symbol for Bot: ");
            String symbol = scanner.next();
            Bot bot = new Bot(symbol.charAt(0), playerName, BotDifficultyLevel.EASY);
        }

        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension, players);

        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("This is the current Board");

            gameController.displayBoard(game);

            System.out.println("Do you want to undo ? y/n");
            String input = scanner.next();

            if (input.equals("y")) {
                gameController.undo(game);
            } else {
                gameController.executeNextMove(game);
            }
        }

        System.out.println("Game has ended. Winner is: ");
        if (!gameController.getGameStatus(game).equals(GameStatus.DRAW)) {
            System.out.println(gameController.getWinner(game).getName());
        }
    }
}