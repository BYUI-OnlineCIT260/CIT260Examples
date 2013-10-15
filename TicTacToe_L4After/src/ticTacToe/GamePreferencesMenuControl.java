/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.awt.Dimension;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author jacksonrkj
 */
public class GamePreferencesMenuControl {
    
    private Game game;

    public GamePreferencesMenuControl() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
       
    
    public void getMarker(Player player) {
        String marker = null;
                
        boolean valid = false;
        do {
            System.out.println("\n\t" + player.getName() 
                + ", enter a single character that will be used to mark your squares in the game.");
            Scanner in = TicTacToe.getInputFile();
            marker = in.nextLine();
            if (marker == null  || marker.length() < 1) {
                continue;
            }
            
            marker = marker.trim().substring(0, 1);
            marker = marker.toUpperCase();
            valid = this.saveMarker(player, marker);
        } while (!valid);

        this.saveMarker(player, marker);
   
    }
    
     public boolean getDimensions() {

        if (game.getStatus().equals(Game.PLAYING)) {
            new TicTacToeError().displayError("You can not change the dimensions "
              + "of the board once the game has been started. "
              + "\n\tStart a new game and then change the dimensions "
              + "of the board. ");
            return false;
        }

        // prompt for the row and column numbers
        System.out.println("\n\tEnter the number of rows and columns in the board (For example: 3 3)");

        Scanner inFile = TicTacToe.getInputFile(); // get input file 
        
        // read the row and column coordinates
        String[] valuesEntered;
        Dimension dimension = null;
        do {
            String strNoRowsColumns = inFile.nextLine(); // read in row and column
            strNoRowsColumns = strNoRowsColumns.trim(); // trim all blanks from front and end 
            strNoRowsColumns = strNoRowsColumns.replace(',', ' '); // replace commas with a blank
            valuesEntered = strNoRowsColumns.split("\\s"); // tokenize the string

            if (valuesEntered.length < 1) {
                new TicTacToeError().displayError(
                        "You must enter two numbers, the number rows and columns, "
                        + "or a \"Q\" to quit. Try again.");
                continue;
            } else if (valuesEntered.length == 1) {
                if (valuesEntered[0].toUpperCase().equals("Q")) { // Quit?
                    return false;
                }  // wrong number of values entered.
     
                // wrong number of values entered.
                new TicTacToeError().displayError(
                       "You must enter two numbers, the number rows and columns, "
                       + "or a \"Q\" to quit. Try again.");
                continue;
            }
            
            // user java regular expression to check for valid integer number?
            Pattern digitPattern = Pattern.compile(".*\\D.*");
            if (digitPattern.matcher(valuesEntered[0]).matches()  || 
                digitPattern.matcher(valuesEntered[1]).matches()
               ) {
                new TicTacToeError().displayError(
                        "You must enter two numbers, the number rows and columns, "
                        + "or a \"Q\" to quit. Try again.");
                continue;
            }
            
            int rowsEntered = Integer.parseInt(valuesEntered[0]);
            int columnsEntered = Integer.parseInt(valuesEntered[1]);
            dimension = new Dimension(rowsEntered, columnsEntered);

        } while (dimension == null);
        
        this.saveDimensions(dimension);
        
        return true;
    }
   
    
    
    
    private boolean saveMarker(Player player, String marker) { 

        if (player == null  ||  marker == null) {
            new TicTacToeError().displayError("saveMarker - player or marker is invalid");
            return false;
        }

   
        if (game.getPlayerA().getMarker().equals(game.getPlayerB().getMarker())) {
            new TicTacToeError().displayError("Both players can not use the same character for a marker.");
            return false;
        }
        
        // update the players markers
        player.setMarker(marker);
        
        return true;
    }
        
    
    private boolean saveDimensions(Dimension dimension)  {
        // validate inputs
        if (this.game.getStatus().equals(Game.PLAYING)) {
            new TicTacToeError().displayError("You can not change the dimensions "
              + "of the board once the game has been started. "
              + "\nStart a new game and then change the dimensions "
              + "of the board. ");
            return false;
        }
        
        
        if (dimension == null) {
            new TicTacToeError().displayError(
                    "The number of rows must be between 3 -10 and the "
                    + "number of columns must be between 3 -10 ");
            return false;
        }
        
        int boardRowCount = dimension.width;
        int boardColumnCount= dimension.height;
        
        if (boardRowCount < 3 || boardRowCount > 10) {
            new TicTacToeError().displayError(
                    "The number of rows must be between 3 -10 and the "
                    + "number of columns must be between 3 -10 ");
            return false;
        }

        if (boardColumnCount < 3 || boardColumnCount > 10) {
            new TicTacToeError().displayError(
                    "The number of rows must be between 3 -10 and the "
                    + "number of columns must be between 3 -10 ");
            return false;
        }

        // no change in the board size so return
        if (boardRowCount == this.game.getBoard().getRowCount() &&
            boardColumnCount == this.game.getBoard().getColumnCount()) {
            return true;
        }
        
        // change the size board
        Player[][] boardLocations = new Player[boardRowCount][boardColumnCount];
        this.game.getBoard().getBoardDimensions().setLocation(boardRowCount, boardRowCount);
        this.game.getBoard().setBoardLocations(boardLocations);
        
        Dimension boardDimensions = new Dimension(boardRowCount, boardColumnCount);
        
        return true;
    }
    
    
    
}
