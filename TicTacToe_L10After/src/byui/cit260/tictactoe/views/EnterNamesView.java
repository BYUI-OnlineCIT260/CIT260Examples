/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.tictactoe.views;

import byui.cit260.tictactoe.models.Game;
import byui.cit260.tictactoe.interfaces.EnterInfo;
import java.util.Scanner;
import byui.cit260.tictactoe.controls.TicTacToe;
import byui.cit260.tictactoe.controls.TicTacToeError;

/**
 *
 * @author jacksonrkj
 */
public class EnterNamesView implements EnterInfo {
    
    private Game game;

    public EnterNamesView(Game game) {
        this.game = game;
    }

    
    
    
    @Override
    public Object getInput(Object object) {
        String playersName;

        // if one player game
        if (Game.ONE_PLAYER.equals(this.game.getGameType())) {
            System.out.println("\n\tFirst player:");
            playersName = (String) this.getName();

            if (playersName ==  null) {
                return Game.QUIT;
            }
            this.game.getPlayerA().setName(playersName);
            this.game.getPlayerB().setName("Computer");
        }
        // else two player game
        else { 
            // get first players name
            System.out.println("\n\tFirst player:");
            playersName = (String) this.getName();
            if (playersName ==  null) {
                return Game.QUIT;
            }
            this.game.getPlayerA().setName(playersName); 

            // get the second players name
            System.out.println("\n\tSecond player:");
            playersName = (String) this.getName();
            if (playersName ==  null) {
                return Game.QUIT;
            }
            this.game.getPlayerB().setName(playersName);
        }
        
        return Game.CONTINUE;
    }
 

    public Object getName() {
        String strName = null;
        Scanner inFile = TicTacToe.getInputFile();

        
        boolean valid = false;
        do {
            System.out.println("\tPlease enter the name of the player.");
            strName = inFile.nextLine();
            strName = strName.trim().toUpperCase();
            if (strName.length() < 1) {
                new TicTacToeError().display("You must enter a name or enter a \"Q\" to quit. Try again.");
                return null;
            }
            strName = strName.trim();
            if (strName.equals("Q")) {
                return null;
            }
            
            valid = true;
        } while (!valid);


        return strName;
    }
 
    
}
