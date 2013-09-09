/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.awt.Point;

/**
 *
 * @author jacksonrkj
 */
public class GameMenuControl {

    private Game game;
    private Board board;

    public GameMenuControl(Game game) {
        this.game = game;
        this.board = game.getBoard();
    }

    public Point playerTakesTurn(Player player, Point selectedLocation) {
        Point locationMarkerWasPlaced = new Point();
        // TODO

        return locationMarkerWasPlaced;
    }

    public void takeTurn(Point selectedLocation) {
        Player currentPlayer = this.game.getCurrentPlayer();
        Player otherPlayer = this.game.getOtherPlayer();

        String playerType = currentPlayer.getPlayerType();

        if (this.game.getGameType().equals(Game.ONE_PLAYER)) {
            if (currentPlayer.getPlayerType().equals(Player.REGULAR_PLAYER)) {
                this.playerTakesTurn(currentPlayer, selectedLocation);
                if (this.game.getStatus().equals(Game.PLAYING)) { // game over ?
                    return;
                }

                this.playerTakesTurn(otherPlayer, selectedLocation);
                String message = "The computer also took it's turn. "
                        + " it is your turn again " + currentPlayer.getName();
            }
            if (currentPlayer.getPlayerType().equals(Player.COMPUTER_PLAYER)) {
                this.playerTakesTurn(currentPlayer, selectedLocation);
                this.alternatePlayers();
            }
        } else if (this.game.getGameType().equals(Game.TWO_PLAYER)) {
            this.playerTakesTurn(currentPlayer, selectedLocation);
            this.alternatePlayers();
        }




    }

    public void alternatePlayers() {
        if (this.game.getCurrentPlayer() == this.game.getPlayerA()) {
            this.game.setCurrentPlayer(this.game.getPlayerB());
            this.game.setOtherPlayer(this.game.getPlayerA());
        } else {
            this.game.setCurrentPlayer(this.game.getPlayerA());
            this.game.setOtherPlayer(this.game.getPlayerB());
        }
    }

    public boolean regularTurn(Player player, Point location) {
        if (location == null) {
            new TicTacToeError().displayError("GameCommands - regularTurn: location is null");
            return false;
        }

        if (game.getStatus().equals(Game.PLAYING)
                && game.getStatus().equals(Game.NEW_GAME)) {
            new TicTacToeError().displayError("There is no active game. "
                    + "You must start a new game before you can take a turn");
            return false;
        }

        game.setStatus(Game.PLAYING);
        this.markLocation(player, location);

        return true;
    }

    public Point coumputerTakesTurn(Player player) {
        // computer takes turn
        game.setStatus(Game.PLAYING);
        Point location = this.getComputersSelection();
        this.markLocation(player, location);
        return location;
    }

    private void markLocation(Player player, Point location) {

        this.game.getBoard().occupyLocation(player, location.x, location.y);
        if (this.isTie()) { // game tied already
            this.game.recordTie();
            this.game.setStatus(Game.TIE);
            return;
        }

        boolean aWinner = this.isWinner();
        if (aWinner) { // game won already
            this.game.recordWinner();
            this.game.setStatus(Game.WINNER);
            return;
        }

        this.game.setStatus(Game.PLAYING);
    }

    private boolean isTie() {
        // TODO

        return false;
    }

    
    private boolean isWinner() {
        // TODO

        return false;
    }
    
    
    private Point getComputersSelection() {
        // TODO

        return null;
    }

    public void startNewGame(Game game) {
        game.start();
    }



    private boolean threeInARow(int row, int col, Player[][] boardLocations) {
        boolean winner = false;

        int columnLength = boardLocations[row].length;
        int rowLength = boardLocations.length;

        // square not taken yet
        if (boardLocations[row][col] == null) {
            return false;
        } // search for three adjacent horizontally
        else if (row < rowLength && col < columnLength - 2
                && boardLocations[row][col] == boardLocations[row][col + 1]
                && boardLocations[row][col] == boardLocations[row][col + 2]) {
            return true;
        } // search for three adjacent vertically
        else if (row < rowLength - 2 && col < columnLength
                && boardLocations[row][col] == boardLocations[row + 1][col]
                && boardLocations[row][col] == boardLocations[row + 2][col]) {
            return true;
        } // search for three adjacent diagonally leaning backward
        else if (row < rowLength - 2 && col < columnLength - 2
                && boardLocations[row][col] == boardLocations[row + 1][col + 1]
                && boardLocations[row][col] == boardLocations[row + 2][col + 2]) {
            return true;
        } // search for three adjacent diagonally learning forward
        else if (row < rowLength - 2 && col > 1
                && boardLocations[row][col] == boardLocations[row + 1][col - 1]
                && boardLocations[row][col] == boardLocations[row + 2][col - 2]) {
            return true;
        }

        return false;
    }

    private Point findWinningLocation(Player player) {
        // TODO 

        return null; 
    }

    private Point chooseRandomLocation() {
        // TODO
        
        return null;
    }
    
    
    
}
