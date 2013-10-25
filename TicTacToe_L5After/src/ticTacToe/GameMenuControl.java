/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author jacksonrkj
 */
public class GameMenuControl {
    
    private Game game;
    private Board board;
    private GetLocationView getLocationView;
    private BoardView boardView = new BoardView();

    
    public GameMenuControl(Game game) {
        this.game = game;
        this.board = game.board;
    }
    
     
    /* 
     * Take a turn action
     */
    public void takeTurn() {
        
        int returnValue = 1;
        
        if (!this.game.status.equals(Game.NEW_GAME)  && 
            !this.game.status.equals(Game.PLAYING)) {
            new TicTacToeError().displayError("You must start a new game first.");
            return;
        }
        
        if (this.game.gameType.equals(Game.TWO_PLAYER)) { //two player game 
            // regular player takes turn
            returnValue = this.regularPlayerTurn(this.game.currentPlayer);            
            if (returnValue < 0  || this.gameOver(this.game.currentPlayer)) {
                return;
            }
            this.displayBoard();
            this.alternatePlayers(); // alternate players             
            
            // other player takes turn 
            returnValue = this.regularPlayerTurn(this.game.currentPlayer);            
            if (returnValue < 0  || this.gameOver(this.game.currentPlayer)) {
                return;
            }
            this.displayBoard();
            this.alternatePlayers(); // alternate players
        }
        
        else { // one player game
            // regular player takes turn
            this.regularPlayerTurn(this.game.currentPlayer);
            if (returnValue < 0  || this.gameOver(this.game.currentPlayer)) {
                return;
            }
        
            // computer takes turn         
            this.coumputerTakesTurn(this.game.otherPlayer);
            System.out.println("\n\tThe computer also took it's turn");
            this.displayBoard();            
            if (returnValue < 0  || this.gameOver(this.game.otherPlayer)) {
                return;
            }
        }
    
    }   
    
    
    /*
     * Display the board acton
     */
    public void displayBoard() {
        boardView.displayBoard(this.board);
    }
    
    /*
     * Start a new game action
     */
    public void startNewGame() {
        this.game.start();
        this.displayBoard();
    }
  
    
  
    
    /*
     * Display statistics action
     */
     public void displayStatistics() {
        String playerAStatistics = this.game.playerA.getPlayerStastics();
        String playerBStatistics = this.game.playerB.getPlayerStastics();
        System.out.println("\n\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("\t " + playerAStatistics);
        System.out.println("\n\t " + playerBStatistics);
        System.out.println("\t+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
     
     /*
      * Display game preferences menu action
      */
    public void displayPreferencesMenu() {
        GamePreferencesMenuView gamePreferenceMenuView = new GamePreferencesMenuView(this.game);
        gamePreferenceMenuView.getInput();
    }
    
    
    /*
      * Display help menu action
      */
    public void displayHelpMenu() {
        HelpMenuView helpMenuView = new HelpMenuView();
        helpMenuView.getInput();
    }
    
    /* 
     * determine if the game is over
     * @return true - if the game is a win or a tie; otherwise, return false
     */    
    private boolean gameOver(Player player) {
        if (this.isWinner()) {
            this.game.status = Game.WINNER;
            this.displayGameOverMessage(player, "Congratulations! You won the game.");
            
            return true;
        }
        else if (this.isTie()) {
            this.game.status = Game.TIE;
            this.displayGameOverMessage(player, "Better luck next time. The game is a tie.");
            return true;
        } 
        
        return false;
    }
    
    private void displayGameOverMessage(Player player, String message) {
        System.out.println("\n\t************************************************");
        System.out.println("\t " + player.name + ": " + message);
        System.out.println("\t************************************************");
    }
    
    /*
     * A regular player takes a turn
     * @parameter player The player taking the turn
     */
    private int regularPlayerTurn(Player player) {
        
        if (!this.game.status.equals(Game.NEW_GAME)  &&
            !this.game.status.equals(Game.PLAYING)) {
            new TicTacToeError().displayError(
                    "There is no active game. You must start a new game before "
                    + "you can take a turn");
            return -1;
        } 
        
        this.game.status = Game.PLAYING;
        
        GetLocationView getLocationView = new GetLocationView(this.game);
        Point location = getLocationView.getInput();
        if (location == null) { // no location was entered?
            return -1;
        }
            
        this.game.board.occupyLocation(player, location.x, location.y);
        
        return 0;
    }
    
     /*
     * A regular player takes a turn
     * @parameter player The player who is the computer
     */
    private void coumputerTakesTurn(Player player) {
        // computer takes turn 
        Point location = this.getComputersSelection();
        this.game.board.occupyLocation(player, location.x, location.y);
        return;
    }
    


    /*
     * Alternate players
     */
    public void alternatePlayers() {
        if (this.game.currentPlayer == this.game.playerA) {
            this.game.currentPlayer = this.game.playerB;
            this.game.otherPlayer = this.game.playerA;
        } else {
            this.game.currentPlayer = this.game.playerA;
            this.game.otherPlayer = this.game.playerB;
        }
    }
    


    
    
    /* 
     * Get computers selection
     */
    private Point getComputersSelection() {
        Point coordinate;

        coordinate = this.findWinningLocation(game.currentPlayer);
        if (coordinate != null) { // winning location found for computer
            return coordinate;
        }

        // find winning location for other player
        coordinate = this.findWinningLocation(game.otherPlayer);
        if (coordinate == null) { // no winning location found for other player
            coordinate = this.chooseRandomLocation();

            if (coordinate == null) {
                new TicTacToeError().displayError("No empty locations found on the board");
                return null;
            }
        }
   
        return coordinate;
    }



    /* 
     * Is the game tied?
     */ 
    private boolean isTie() {
        
        Location[][] locations = this.board.boardLocations;
        
        // for every row in the table
        for (int row = 0; row < locations.length; row++) {
            
            Location[] rowLocations = locations[row];
            
            // for every column in the row
            for (int col = 0; col < rowLocations.length; col++) {
                Location location = rowLocations[col]; // get contents of cell
                if (locations[row][col].player == null) { // location not taken yet?
                    return false;
                }
            }
        }

        return true; // all locations are taken
    }

    /*
     * Is the game won
     */
    private boolean isWinner() {

        Location[][] locations = this.board.boardLocations;

        // for every row in the table
        for (int row = 0; row < locations.length; row++) {
            
            // get the list of locstaions (columns) in the row
            Location[] rowLocations = locations[row];
            
            // for every column in the row
            for (int col = 0; col < rowLocations.length; col++) {
                
                // three of the same players markers in a row?
                if (threeInARow(row, col, locations)) { 
                    return true; // three in a row found (a winner)
                }
            }
        }

        return false; // no one is a winner yet
    }

    /* 
     * Are there three of the same markers in a row
     */
    private boolean threeInARow(int row, int col, Location[][] boardLocations) {
        boolean winner = false;

        int columnLength = boardLocations[row].length;
        int rowLength = boardLocations.length;

        // square not taken yet
        if (boardLocations[row][col].player == null) {
            return false;
        } // search for three adjacent horizontally
        else if (row < rowLength && col < columnLength - 2
                && boardLocations[row][col].player == boardLocations[row][col + 1].player
                && boardLocations[row][col].player == boardLocations[row][col + 2].player) {
            return true;
        } // search for three adjacent vertically
        else if (row < rowLength - 2 && col < columnLength
                && boardLocations[row][col].player == boardLocations[row + 1][col].player
                && boardLocations[row][col].player == boardLocations[row + 2][col].player) {
            return true;
        } // search for three adjacent diagonally leaning backward
        else if (row < rowLength - 2 && col < columnLength - 2
                && boardLocations[row][col].player == boardLocations[row + 1][col + 1].player
                && boardLocations[row][col].player == boardLocations[row + 2][col + 2].player) {
            return true;
        } // search for three adjacent diagonally learning forward
        else if (row < rowLength - 2 && col > 1
                && boardLocations[row][col].player == boardLocations[row + 1][col - 1].player
                && boardLocations[row][col].player == boardLocations[row + 2][col - 2].player) {
            return true;
        }

        return false;
    }

    /* 
     * Find a winning location
     */
    private Point findWinningLocation(Player player) {
        Point coordinate = new Point();
        Location[][] locations = this.board.boardLocations;
        for (int row = 0; row < locations.length; row++) {
            Location[] rowLocations = locations[row];
            for (int col = 0; col < rowLocations.length; col++) {
                Location location = rowLocations[col];
                coordinate.setLocation(row, col);

                if (rowLocations[col].player != null) { // location is occupied
                    continue;
                }

                // search for three adjacent horizontally
                if ((row < locations.length
                        && col < rowLocations.length - 2)
                        && (locations[row][col + 1].player == player
                        && locations[row][col + 2].player == player)) {
                    return coordinate;
                } else if ((row < locations.length
                        && col > 0 && col < rowLocations.length - 1)
                        && (locations[row][col - 1].player == player
                        && locations[row][col + 1].player == player)) {
                    return coordinate;
                } else if ((row < locations.length && col > 1)
                        && (locations[row][col - 1].player == player
                        && locations[row][col - 2].player == player)) {
                    return coordinate;
                } // search for three adjacent vertically
                else if ((row < locations.length - 2
                        && col < rowLocations.length)
                        && (locations[row + 1][col].player == player
                        && locations[row + 2][col].player == player)) {
                    return coordinate;
                } else if ((row > 0 && row < locations.length - 1
                        && col < rowLocations.length)
                        && (locations[row - 1][col].player == player
                        && locations[row + 1][col].player == player)) {
                    return coordinate;
                } else if ((row > 1 && col < rowLocations.length)
                        && (locations[row - 1][col].player == player
                        && locations[row - 2][col].player == player)) {
                    return coordinate;
                } // search for three adjacent diagonally leaning backward
                else if ((row < locations.length - 2
                        && col < rowLocations.length - 2)
                        && (locations[row + 1][col + 1].player == player
                        && locations[row + 2][col + 2].player == player)) {
                    return coordinate;
                } else if ((row > 0 && row < locations.length - 1
                        && col > 0 && col < rowLocations.length - 1)
                        && (locations[row - 1][col + 1].player == player
                        && locations[row + 1][col - 1].player == player)) {
                    return coordinate;
                } else if ((row > 1 && col > 1)
                        && (locations[row - 1][col - 1].player == player
                        && locations[row - 2][col - 2].player == player)) {
                    return coordinate;
                } // search for three adjacent diagonally learning forward
                else if ((row < locations.length - 2 && col > 1)
                        && (locations[row + 1][col - 1].player == player
                        && locations[row + 2][col - 2].player == player)) {
                    return coordinate;
                } else if ((row > 0 && row < locations.length - 1
                        && col > 0 && col < rowLocations.length - 1)
                        && (locations[row - 1][col + 1].player == player
                        && locations[row + 1][col - 1].player == player)) {
                    return coordinate;
                } else if ((row > 1 && col < rowLocations.length - 2)
                        && (locations[row - 1][col + 1].player == player
                        && locations[row - 2][col + 2].player == player)) {
                    return coordinate;
                }
            }
        }

        return null; // not found
    }

    /* 
     * Choose a random location
     */
    private Point chooseRandomLocation() {
        Point randomLocation;

        ArrayList<Point> listOfEmptyLocations = new ArrayList<>();
        Location[][] locations = this.board.boardLocations;

        // create list of empty locations
        for (int row = 0; row < locations.length; row++) {
            Location[] rowLocations = locations[row];
            for (int col = 0; col < rowLocations.length; col++) {
                Location location = rowLocations[col];
                if (location.player == null) { // location not occupied?
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
    
      /* 
     * Clear the board action
     */
    public void clearTheBoard() {
        Location[][] locations = this.game.board.boardLocations;
        
        for (int i = 0; i < this.board.boardLocations.length; i++) {
            Location[] rowlocations = locations[i];
            for (int j = 0; j < rowlocations.length; j++) {
                rowlocations[j] = null;
            }
        }
    }
        
}
