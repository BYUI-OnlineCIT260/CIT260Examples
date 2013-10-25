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

    int rowCount = 3;
    int columnCount = 3;
    private String name;
    private Point boardDimensions = new Point();
    private Player[][] boardLocations;

    public Board() {
    }

    public Board(int noRows, int noColumns) {
        this.boardDimensions.setLocation(noRows, noRows);
        this.boardLocations = new Player[noRows][noColumns];
    }


    public void clearTheBoard() {
        // TODO
    }

    public void occupyLocation(Player player, int row, int column) {
        Player playerAtLocation = this.boardLocations[row][column];

        if (playerAtLocation != null) { // location already occupied
            new TicTacToeError().displayError("This location is already occupied. "
                    + "Try a different location.");
        }
        this.boardLocations[row][column] = player;
    }

    public class Location {

        public int row;
        public int column;
        public Player player;

        Location() {
        }
    }
}
