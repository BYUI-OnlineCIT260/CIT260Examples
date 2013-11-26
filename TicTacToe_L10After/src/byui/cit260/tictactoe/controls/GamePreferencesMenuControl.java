/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.tictactoe.controls;

import byui.cit260.tictactoe.models.Player;
import byui.cit260.tictactoe.models.Game;
import java.awt.Dimension;

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
    
    
    
    public boolean saveMarker(Player player, String marker) { 

        if (player == null  ||  marker == null) {
            new TicTacToeError().display("saveMarker - player or marker is invalid");
            return false;
        }

   
        if (game.getPlayerA().getMarker().equals(game.getPlayerB().getMarker())) {
            new TicTacToeError().display("Both players can not use the same character for a marker.");
            return false;
        }
        
        // update the players markers
        player.setMarker(marker);
        
        return true;
    }
        
    
    public boolean saveDimensions(Dimension dimension)  {
        // validate inputs
        if (this.game.getStatus().equals(Game.PLAYING)) {
            new TicTacToeError().display("You can not change the dimensions "
              + "of the board once the game has been started. "
              + "\nStart a new game and then change the dimensions "
              + "of the board. ");
            return false;
        }
        
        
        if (dimension == null) {
            new TicTacToeError().display(
                    "The number of rows must be between 3 -10 and the "
                    + "number of columns must be between 3 -10 ");
            return false;
        }
        
        int boardRowCount = dimension.width;
        int boardColumnCount= dimension.height;
        
        if (boardRowCount < 3 || boardRowCount > 10) {
            new TicTacToeError().display(
                    "The number of rows must be between 3 -10 and the "
                    + "number of columns must be between 3 -10 ");
            return false;
        }

        if (boardColumnCount < 3 || boardColumnCount > 10) {
            new TicTacToeError().display(
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
