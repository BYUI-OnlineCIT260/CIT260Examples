/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.controls;

import byui.cit210.tictactoe.enums.ErrorType;
import byui.cit210.tictactoe.enums.StatusType;
import byui.cit210.tictactoe.exceptions.GameException;
import byui.cit210.tictactoe.exceptions.TicTacToeException;
import byui.cit210.tictactoe.models.Game;
import byui.cit210.tictactoe.models.Player;
import java.awt.Dimension;

/**
 *
 * @author jacksonrkj
 */
public class GamePreferencesMenuControl {
    
    Game game;

    public GamePreferencesMenuControl() {
    }
    
    
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    
    public void saveMarker(Player player, String marker) throws TicTacToeException { 

        // validate inputs       
        if (marker == null  ||  marker.length() < 1) {         
            throw new TicTacToeException(ErrorType.ERROR204.getMessage());
        }
   
        if (game.getPlayerA().getMarker().equals(game.getPlayerB().getMarker())) {
            throw new TicTacToeException(ErrorType.ERROR205.getMessage());
        }
        
        // update the players markers
        player.setMarker(marker);
    }
        
    
    public void saveDimensions(Dimension dimension) throws GameException {
        // validate inputs
        if (this.game.getStatus() == StatusType.PLAYING) {
            throw new GameException(ErrorType.ERROR101.getMessage());
        }
        
        
        if (dimension == null) {
                throw new GameException(ErrorType.ERROR207.getMessage());
        }
        
        int boardRowCount = dimension.width;
        int boardColumnCount= dimension.height;
        
        if (boardRowCount < 3 || boardRowCount > 9) {
            throw new GameException(ErrorType.ERROR207.getMessage());
        }

        if (boardColumnCount < 3 || boardColumnCount > 9) {
            throw new GameException(ErrorType.ERROR207.getMessage());
        }

        // no change in the board size so return
        if (boardRowCount == this.game.getBoard().getRowCount() &&
            boardColumnCount == this.game.getBoard().getColumnCount()) {
            return;
        }
        
        // change the size board
        Player[][] boardLocations = new Player[boardRowCount][boardColumnCount];
        this.game.getBoard().getBoardDimensions().setLocation(boardRowCount, boardRowCount);
        this.game.getBoard().setBoardLocations(boardLocations);
        
        Dimension boardDimensions = new Dimension(boardRowCount, boardColumnCount);
    }
    
    
    
}
