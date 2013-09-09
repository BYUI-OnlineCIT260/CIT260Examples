/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.controls;

import byui.cit210.tictactoe.enums.ErrorType;
import byui.cit210.tictactoe.enums.StatusType;
import byui.cit210.tictactoe.exceptions.GameException;
import byui.cit210.tictactoe.models.Game;
import byui.cit210.tictactoe.models.Player;
import java.awt.Dimension;

/**
 *
 * @author jacksonrkj
 */
public class GamePreferencesMenuControl {
    
    Game game;

    public GamePreferencesMenuControl(Game game) {
        this.game = game;
    }
    
    
    public Dimension savePreferences(String playerAMarker, 
                                String playerBMarker, 
                                String rowCount, 
                                String columnCount) throws GameException {
        // validate inputs
        if (this.game.getStatus() == StatusType.PLAYING) {
            throw new GameException(ErrorType.ERROR101.getMessage());
        }
        
        if (playerAMarker == null  ||  playerAMarker.length() < 1) {         
            throw new GameException(ErrorType.ERROR204.getMessage());
        }
        playerAMarker = playerAMarker.substring(0, 1).toUpperCase();
        
        if (playerBMarker == null ||  playerBMarker.length() < 1) {            
            throw new GameException(ErrorType.ERROR204.getMessage());
        }
        playerBMarker = playerBMarker.substring(0, 1).toUpperCase();
        
        if (playerAMarker.equals(playerBMarker)) {
            throw new GameException(ErrorType.ERROR205.getMessage());
        }
        
        // update the players markers
        this.game.getPlayerA().setMarker(playerAMarker);
        this.game.getPlayerB().setMarker(playerBMarker);
        
        
        
        if (rowCount == null  || columnCount == null) {
            if (rowCount == null) {
                throw new GameException(ErrorType.ERROR207.getMessage());
            }
            else {
                throw new GameException(ErrorType.ERROR208.getMessage());
            }
        }
        
        int boardRowCount;
        int boardColumnCount;
        try {
            boardRowCount = Integer.parseInt(rowCount);
            if (boardRowCount < 3 || boardRowCount > 10) {
                throw new GameException(ErrorType.ERROR207.getMessage());
            }
        } catch (NumberFormatException ne) {
            throw new GameException(ErrorType.ERROR207.getMessage());
        }

        try {
            boardColumnCount = Integer.parseInt(columnCount);
            if (boardColumnCount < 3 || boardColumnCount > 10) {
                throw new GameException(ErrorType.ERROR208.getMessage());
            }
        } catch (NumberFormatException ne) {
            throw new GameException(ErrorType.ERROR208.getMessage());
        }
        
        // no change in the board size so return
        if (boardRowCount == this.game.getBoard().getRowCount() &&
            boardColumnCount == this.game.getBoard().getColumnCount()) {
            return null;
        }
        
        // change the size board
        Player[][] boardLocations = new Player[boardRowCount][boardColumnCount];
        this.game.getBoard().getBoardDimensions().setLocation(boardRowCount, boardRowCount);
        this.game.getBoard().setBoardLocations(boardLocations);
        
        Dimension boardDimensions = new Dimension(boardRowCount, boardColumnCount);
        
        return boardDimensions;
    }
    
    
    
}
