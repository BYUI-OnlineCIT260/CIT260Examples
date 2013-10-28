/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.awt.Dimension;

/**
 *
 * @author jacksonrkj
 */
public class GamePreferencesMenuControl {
    
    private Game game;

    public GamePreferencesMenuControl(Game game) {
        this.game = game;
    }
    
    
    public void getMarker(Player player) { 
        
        GetMarkerView getMarkerView = new GetMarkerView(this.game);
        String marker = getMarkerView.getInput(player);
        
        if (marker == null) { // user changed there mind and quit??
            return;
        }
      
        player.marker = marker; // update the players markers
        
        return;
    }
        
    
    public void getDimensions()  {
        
        // Check to see if a game is already in progress
        if (this.game.status.equals(Game.PLAYING)) {
            new TicTacToeError().displayError("You can not change the dimensions "
              + "of the board once the game has been started. "
              + "\nStart a new game and then change the dimensions "
              + "of the board. ");
            return;
        }
        
        GetDimensionsView getDimensionsView = new GetDimensionsView(this.game);
        Dimension dimension = getDimensionsView.getInput();
        
        if (dimension == null) {    
            return;
        }
        
        int boardRowCount = dimension.width;
        int boardColumnCount= dimension.height;
         
        // no change in the board size so return
        if (boardRowCount == this.game.board.getRowCount() &&
            boardColumnCount == this.game.board.getColumnCount()) {
            return;
        }
        
        // change the size board
        Player[][] boardLocations = new Player[boardRowCount][boardColumnCount];
        this.game.board.getBoardDimensions().setLocation(boardRowCount, boardRowCount);
        this.game.board.setBoardLocations(boardLocations);
               
        return;
    }
    
    
    
}
