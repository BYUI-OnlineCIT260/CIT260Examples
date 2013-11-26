/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.tictactoe.controls;

import byui.cit260.tictactoe.enums.ErrorType;
import byui.cit260.tictactoe.enums.GameType;
import byui.cit260.tictactoe.enums.PlayerType;
import byui.cit260.tictactoe.enums.StatusType;
import byui.cit260.tictactoe.models.Player;
import byui.cit260.tictactoe.models.Game;

/**
 *
 * @author jacksonrkj
 */
public class MainMenuControl {
    
    private static final String PLAYER_A_DEFAULT_MARKER = "X";
    private static final String PLAYER_B_DEFAULT_MARKER = "O";
    
    public Game create(GameType gameType) {
        Game game = null;
        Player playerA = null;
        Player playerB = null;
        
        if (gameType == null) {
            new TicTacToeError(ErrorType.ERROR105).display();
            return null;
        }
        
        if (gameType.equals(GameType.ONE_PLAYER)) {
            game = new Game(GameType.ONE_PLAYER);
            playerA = new Player(PlayerType.REGULAR_PLAYER, PLAYER_A_DEFAULT_MARKER);
            playerB = new Player(PlayerType.COMPUTER_PLAYER, PLAYER_B_DEFAULT_MARKER);
        }
        else if (gameType.equals(GameType.TWO_PLAYER)) {
            game = new Game(GameType.TWO_PLAYER);
            playerA = new Player(PlayerType.REGULAR_PLAYER, PLAYER_A_DEFAULT_MARKER);
            playerB = new Player(PlayerType.REGULAR_PLAYER, PLAYER_B_DEFAULT_MARKER);

        }
         
        game.setPlayerA(playerA);
        game.setPlayerB(playerB);
        
        game.setStatus(StatusType.CONTINUE);
        
        return game;
    } 
    
}
