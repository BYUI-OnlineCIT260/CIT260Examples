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
public class Board {

    public int rowCount = 3;
    public int columnCount = 3;
   
    public Location[][] boardLocations;

    public Board() {
    }

    public Board(int noRows, int noColumns) {
  
        this.createBoardLocations(noRows, noColumns);
    }


    public void createBoardLocations(int noRows, int noColumns) {
        this.rowCount= noRows;
        this.columnCount= noColumns;
        
        // add locations to the board
        this.boardLocations = new Location[noRows][noColumns];
        for (int row = 0; row < noRows; row++) { // for every row
            // for every column in the row
            for (int column = 0; column < noColumns; column++) { 
                this.boardLocations[row][column] = new Location();           
            }            
        }   
    }
    
    
    public void clearTheBoard() {
        // add locations to the board
        for (int row = 0; row < this.rowCount; row++) {
            for (int column = 0; column < this.columnCount; column++) {
                Location location = this.boardLocations[row][column];
                location.player = null;           
            }            
        }    
    }

    public void occupyLocation(Player player, int row, int column) {
        Location location = this.boardLocations[row][column];
        
        if ( location.player != null) { // location already occupied
            new TicTacToeError().displayError("This location is already occupied. "
                    + "Try a different location.");
        }
        
        location.player = player;
    }


}
