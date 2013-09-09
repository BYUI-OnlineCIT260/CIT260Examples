/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.controls;

import byui.cit210.tictactoe.models.Game;
import byui.cit210.tictactoe.models.Player;

/**
 *
 * @author jacksonrkj
 */
public class PlayerNameMenuControl {
    
    public static void savePlayersNames(Game game, String playerAName, String playerBName) {
       if (game == null 
               || playerAName == null
               || playerBName == null) {
           throw new IllegalArgumentException("savePlayersNames - parameter value is null");
       }
       Player playerA = game.getPlayerA();
       Player playerB = game.getPlayerB();
       playerA.setName(playerAName);
       playerB.setName(playerBName);
    }
    
}
