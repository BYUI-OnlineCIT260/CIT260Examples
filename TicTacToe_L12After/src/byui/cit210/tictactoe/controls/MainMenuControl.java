/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.controls;

import byui.cit210.tictactoe.enums.GameType;
import byui.cit210.tictactoe.enums.PlayerType;
import byui.cit210.tictactoe.models.Game;
import byui.cit210.tictactoe.models.Player;

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
            throw new IllegalArgumentException ("MainCommands - create: gameType is null");
        }
        
        if (gameType == GameType.ONE_PLAYER) {
            game = new Game(GameType.ONE_PLAYER);
            playerA = new Player(PlayerType.REGULAR_PLAYER, PLAYER_A_DEFAULT_MARKER);
            playerB = new Player(PlayerType.COMPUTER_PLAYER, PLAYER_B_DEFAULT_MARKER);
        }
        else if (gameType == GameType.TWO_PLAYER) {
            game = new Game(GameType.TWO_PLAYER);
            playerA = new Player(PlayerType.REGULAR_PLAYER, PLAYER_A_DEFAULT_MARKER);
            playerB = new Player(PlayerType.REGULAR_PLAYER, PLAYER_B_DEFAULT_MARKER);

        }
         
        game.setPlayerA(playerA);
        game.setPlayerB(playerB);
        
        return game;
    } 
    
}
