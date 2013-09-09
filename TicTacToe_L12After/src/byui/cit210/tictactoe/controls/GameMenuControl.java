/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.controls;

import byui.cit210.tictactoe.enums.StatusType;
import byui.cit210.tictactoe.exceptions.GameException;
import byui.cit210.tictactoe.exceptions.TicTacToeException;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import byui.cit210.tictactoe.enums.ErrorType;
import byui.cit210.tictactoe.enums.GameType;
import byui.cit210.tictactoe.enums.PlayerType;
import byui.cit210.tictactoe.models.Board;
import byui.cit210.tictactoe.models.Game;
import byui.cit210.tictactoe.models.Player;

/**
 *
 * @author jacksonrkj
 */
public class GameMenuControl {
    
    Game game;
    Board board;

    public GameMenuControl(Game game) {
        this.game = game;
        this.board = game.getBoard();
    }
    
    public Point playerTakesTurn(Player player, Point selectedLocation) throws TicTacToeException, GameException {
        Point locationMarkerPlaced = null;

        if (player ==  null) {
            throw new TicTacToeException(ErrorType.ERROR106.getMessage());
        }

        if (player.getPlayerType() != PlayerType.REGULAR_PLAYER && 
            player.getPlayerType() != PlayerType.COMPUTER_PLAYER) {
            throw new TicTacToeException(ErrorType.ERROR209.getMessage());
        }
        
                
        if (this.game.getStatus() != StatusType.PLAYING ) {
            throw new GameException(ErrorType.ERROR206.getMessage());
        }

        PlayerType playerType = player.getPlayerType();

        if (playerType == PlayerType.REGULAR_PLAYER) {
            this.regularTurn(player, selectedLocation);
            locationMarkerPlaced = selectedLocation;
        }
        else if (playerType == PlayerType.COMPUTER_PLAYER) {
            locationMarkerPlaced = this.coumputerTakesTurn(player);
        }

        this.alternatePlayers();

        return locationMarkerPlaced;
    }
    
    public void takeTurn(Point selectedLocation) throws TicTacToeException, GameException {
        Player currentPlayer = this.game.getCurrentPlayer();
        Player otherPlayer = this.game.getOtherPlayer();
        
        PlayerType playerType = currentPlayer.getPlayerType(); 

        if (this.game.getGameType() == GameType.ONE_PLAYER) {
            if (currentPlayer.getPlayerType() == PlayerType.REGULAR_PLAYER) {
                this.playerTakesTurn(currentPlayer, selectedLocation);
                if (this.game.getStatus() != StatusType.PLAYING) { // game over ?
                    return;
                }
                
                this.playerTakesTurn(otherPlayer, selectedLocation);
                String message = "The computer also took it's turn. "
                        + " it is your turn again " + currentPlayer.getName();
            }
            if (currentPlayer.getPlayerType() == PlayerType.COMPUTER_PLAYER) {
                this.playerTakesTurn(currentPlayer, selectedLocation);
                this.alternatePlayers();                
            } 
        } 
        else if (this.game.getGameType() == GameType.TWO_PLAYER) {
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
    
    public void regularTurn(Player player, Point location) throws TicTacToeException, GameException {
        if (location == null) {
            throw new IllegalArgumentException("GameCommands - regularTurn: location is null");
        }
        
        if (game.getStatus() != StatusType.PLAYING && 
            game.getStatus() != StatusType.NEW_GAME) {
            throw new TicTacToeException(ErrorType.ERROR206.getMessage());
        }

        game.setStatus(StatusType.PLAYING);
        this.markLocation(player, location);
    }
    
    public Point coumputerTakesTurn(Player player) throws TicTacToeException, GameException {
        // computer takes turn
        game.setStatus(StatusType.PLAYING); 
        Point location = this.getComputersSelection();
        this.markLocation(player, location);
        return location;
    }
    

    
    
    private void markLocation(Player player, Point location) throws GameException {


        
        this.game.getBoard().occupyLocation(player, location.x, location.y);
        if (this.isTie()) { // game tied already
            this.game.recordTie();
            this.game.setStatus(StatusType.TIE);
            return;
        }

        boolean aWinner = this.isWinner();
        if (aWinner) { // game won already
            this.game.recordWinner();
            this.game.setStatus(StatusType.WINNER);
            return;
        }
        
        this.game.setStatus(StatusType.PLAYING);
    }
    
    
    
    private Point getComputersSelection()  throws TicTacToeException {
        Point coordinate;
        try {
            coordinate = this.findWinningLocation(game.getCurrentPlayer());
            if (coordinate != null) { // winning location found for computer
                return coordinate;
            }
            
            // find winning location for other player
            coordinate = this.findWinningLocation(game.getOtherPlayer());
            if (coordinate == null) { // no winning location found for other player
                coordinate = this.chooseRandomLocation();

                if (coordinate == null) {
                    throw new GameException(ErrorType.ERROR201.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TicTacToeException(e.getMessage());
        }
        
        return coordinate;
    }

    
    public void startNewGame(Game game) {
        game.start();
        this.clearTheBoard();
    }
  
    
    
    public void clearTheBoard() {
        Player[][] locations = this.game.getBoard().getBoardLocations();
        
        for (int i = 0; i < this.board.getBoardLocations().length; i++) {
            Player[] rowlocations = locations[i];
            for (int j = 0; j < rowlocations.length; j++) {
                rowlocations[j] = null;
            }
        }
    }


    private boolean isTie() {
        Player[][] locations = this.board.getBoardLocations();

        for (int row = 0; row < locations.length; row++) {
            Player[] rowLocations = locations[row];
            for (int col = 0; col < rowLocations.length; col++) {
                Player location = rowLocations[col];
                if (locations[row][col] == null) { // square not taken yet
                    return false;
                }
            }
        }

        return true;
    }

    
    private boolean isWinner() {

        Player[][] locations = this.board.getBoardLocations();

        for (int row = 0; row < locations.length; row++) {
            Player[] rowLocations = locations[row];
            for (int col = 0; col < rowLocations.length; col++) {
                if (threeInARow(row, col, locations)) {
                    return true;
                }
            }
        }

        return false;
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
        Point coordinate = new Point();
        Player[][] locations = this.board.getBoardLocations();
        for (int row = 0; row < locations.length; row++) {
            Player[] rowLocations = locations[row];
            for (int col = 0; col < rowLocations.length; col++) {
                Player location = rowLocations[col];
                coordinate.setLocation(row, col);

                if (rowLocations[col] != null) { // location is occupied
                    continue;
                }

                // search for three adjacent horizontally
                if ((row < locations.length
                        && col < rowLocations.length - 2)
                        && (locations[row][col + 1] == player
                        && locations[row][col + 2] == player)) {
                    return coordinate;
                } else if ((row < locations.length
                        && col > 0 && col < rowLocations.length - 1)
                        && (locations[row][col - 1] == player
                        && locations[row][col + 1] == player)) {
                    return coordinate;
                } else if ((row < locations.length && col > 1)
                        && (locations[row][col - 1] == player
                        && locations[row][col - 2] == player)) {
                    return coordinate;
                } // search for three adjacent vertically
                else if ((row < locations.length - 2
                        && col < rowLocations.length)
                        && (locations[row + 1][col] == player
                        && locations[row + 2][col] == player)) {
                    return coordinate;
                } else if ((row > 0 && row < locations.length - 1
                        && col < rowLocations.length)
                        && (locations[row - 1][col] == player
                        && locations[row + 1][col] == player)) {
                    return coordinate;
                } else if ((row > 1 && col < rowLocations.length)
                        && (locations[row - 1][col] == player
                        && locations[row - 2][col] == player)) {
                    return coordinate;
                } // search for three adjacent diagonally leaning backward
                else if ((row < locations.length - 2
                        && col < rowLocations.length - 2)
                        && (locations[row + 1][col + 1] == player
                        && locations[row + 2][col + 2] == player)) {
                    return coordinate;
                } else if ((row > 0 && row < locations.length - 1
                        && col > 0 && col < rowLocations.length - 1)
                        && (locations[row - 1][col + 1] == player
                        && locations[row + 1][col - 1] == player)) {
                    return coordinate;
                } else if ((row > 1 && col > 1)
                        && (locations[row - 1][col - 1] == player
                        && locations[row - 2][col - 2] == player)) {
                    return coordinate;
                } // search for three adjacent diagonally learning forward
                else if ((row < locations.length - 2 && col > 1)
                        && (locations[row + 1][col - 1] == player
                        && locations[row + 2][col - 2] == player)) {
                    return coordinate;
                } else if ((row > 0 && row < locations.length - 1
                        && col > 0 && col < rowLocations.length - 1)
                        && (locations[row - 1][col + 1] == player
                        && locations[row + 1][col - 1] == player)) {
                    return coordinate;
                } else if ((row > 1 && col < rowLocations.length - 2)
                        && (locations[row - 1][col + 1] == player
                        && locations[row - 2][col + 2] == player)) {
                    return coordinate;
                }
            }
        }

        return null; // not found
    }

    private Point chooseRandomLocation() {
        Point randomLocation;

        ArrayList<Point> listOfEmptyLocations = new ArrayList<>();
        Player[][] locations = this.board.getBoardLocations();

        // create list of empty locations
        for (int row = 0; row < locations.length; row++) {
            Player[] rowLocations = locations[row];
            for (int col = 0; col < rowLocations.length; col++) {
                Player location = rowLocations[col];
                if (location == null) { // location not occupied?
                    listOfEmptyLocations.add(new Point(row, col));
                }
            }
        }

        int noOfEmptyLocations = listOfEmptyLocations.size();

        if (noOfEmptyLocations == 0) { // no empty locations?
            return null;
        } else if (listOfEmptyLocations.size() == 1) { // only one empty location?
            randomLocation = listOfEmptyLocations.get(0);
            return randomLocation;
        } else { // randomly choose one of the empty locations
            int randomNumber = new Random().nextInt(noOfEmptyLocations);
            randomLocation = listOfEmptyLocations.get(randomNumber);
            return randomLocation;
        }
    }
    
}
