/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.tictactoe.controls;

import byui.cit260.tictactoe.enums.ErrorType;
import byui.cit260.tictactoe.enums.StatusType;
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
            new TicTacToeError(ErrorType.ERROR108).display();
            return false;
        }

   
        if (game.getPlayerA().getMarker().equals(game.getPlayerB().getMarker())) {
            new TicTacToeError(ErrorType.ERROR205).display();
            return false;
        }
        
        // update the players markers
        player.setMarker(marker);
        
        return true;
    }
        
    
    public boolean saveDimensions(Dimension dimension)  {
        // validate inputs
        if (this.game.getStatus().equals(StatusType.PLAYING)) {
            new TicTacToeError(ErrorType.ERROR101).display();
            return false;
        }
        
        
        if (dimension == null) {
            new TicTacToeError(ErrorType.ERROR207).display();
            return false;
        }
        
        int boardRowCount = dimension.width;
        int boardColumnCount= dimension.height;
        
        if (boardRowCount < 3 || boardRowCount > 10) {
            new TicTacToeError(ErrorType.ERROR207).display();
            return false;
        }

        if (boardColumnCount < 3 || boardColumnCount > 10) {
            new TicTacToeError(ErrorType.ERROR207).display();
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
