package com.scaler.tictactoe.models;

import com.scaler.tictactoe.Exceptions.InvalidGameException;
import com.scaler.tictactoe.strategies.gamewinningstrategy.GameWinningStrategy;
import com.scaler.tictactoe.strategies.gamewinningstrategy.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;

    private Player winner;

    private GameWinningStrategy gameWinningStrategy;

    public Board getBoard() {
        return board;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public void displayBoard() {
        this.board.display();
    }

    public void makeNextMove() {
        Player playerToMove = players.get(nextPlayerIndex);

        System.out.println("Player " + playerToMove.getName() + " is making a move");

        Move move = playerToMove.decideMove(this.board);

        Cell cell = move.getCell();
        int row = cell.getRow();
        int col = cell.getCol();

        System.out.println("Move happened at row: " + row + " & col: " + col);

        //Validate this move.
        //Check is the cell is the empty or not.
        if (cell.getCellState().equals(CellState.EMPTY)) {
            board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
            board.getBoard().get(row).get(col).setPlayer(playerToMove);
            moves.add(move);
            cell.setPlayer(playerToMove);
        }

        if (gameWinningStrategy.checkWinner(board, playerToMove, cell)) {
            gameStatus = GameStatus.ENDED;
            winner = playerToMove;
        }

        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();
    }

    public Player getWinner() {
        return winner;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean isValid() throws InvalidGameException {
            if (dimension < 3) {
                throw new InvalidGameException("Dimension of the Game can't be less than 3");
            }

            if (players.size() != dimension - 1) {
                throw new InvalidGameException("Number of players should be equal to dimension - 1");
            }

            //More validations

            return true;
        }

        public Game build() throws InvalidGameException {
            try {
                isValid();
            } catch (Exception e) {
                throw new InvalidGameException("Invalid Game creation");
            }

            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimension));
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));
            return game;
        }
    }
}
