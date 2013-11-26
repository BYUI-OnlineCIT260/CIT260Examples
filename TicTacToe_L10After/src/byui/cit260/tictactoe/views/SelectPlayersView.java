/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.tictactoe.views;

import byui.cit260.tictactoe.enums.ErrorType;
import byui.cit260.tictactoe.enums.GameType;
import byui.cit260.tictactoe.enums.StatusType;
import byui.cit260.tictactoe.models.Game;
import java.util.Scanner;
import byui.cit260.tictactoe.controls.TicTacToe;
import byui.cit260.tictactoe.controls.TicTacToeError;

/**
 *
 * @author jacksonrkj
 */
public class SelectPlayersView {
    
    private Game game;
    private String[] playerNames;

    public SelectPlayersView(Game game) {
        this.game = game;
        playerNames = TicTacToe.getNameList();
    }

    
    public StatusType selectPlayers(String[] nameList) {
        String playersName;
        
        this.displayNameList(); // display the list of names
        
        // if one player game
        if (GameType.ONE_PLAYER.equals(this.game.getGameType())) {
           System.out.println("\tPlease enter the number of the player.");

            // get the players name
            playersName = this.getName(TicTacToe.getNameList());

            if (playersName ==  null) {
                return StatusType.QUIT;
            }
            this.game.getPlayerA().setName(playersName);
            this.game.getPlayerB().setName("Computer");
        }
        
        // else two player game
        else { 
            System.out.println("\tPlease enter the number of the first player.");
            // get first players name
            playersName = this.getName(TicTacToe.getNameList());
            if (playersName ==  null) {
                return StatusType.QUIT;
            }
            this.game.getPlayerA().setName(playersName); 

            // get the second players name
            System.out.println("\tPlease enter the number of the second player.");
            playersName = this.getName(TicTacToe.getNameList());
            if (playersName ==  null) {
                return StatusType.QUIT;
            }
            this.game.getPlayerB().setName(playersName);
        }
        
        return StatusType.CONTINUE;
        
    }
    

    public String getName(String[] nameList) {

        Scanner inFile = TicTacToe.getInputFile();
        String name = null;
        boolean valid = false;
        do {
            String strNumber = inFile.nextLine();
            
            if (strNumber.length() < 1) { // was a value entered ?
                new TicTacToeError(ErrorType.ERROR107).display();
                continue;
            }
            
            strNumber = strNumber.trim(); // trim off all blanks from front and back    
            strNumber = strNumber.substring(0, 1); // get only the first character
            
            if (strNumber.toUpperCase().equals("Q")) { // quit?
                return null;
            }
            
            
            if (!strNumber.matches("[0-9]+")) { // is the value entered a number?
                new TicTacToeError(ErrorType.ERROR210).display();
                continue;
            }
            
            int numberSelected = Integer.parseInt(strNumber); // convert string to integer
            
            // is the number outside the range of the list of names
            if (numberSelected < 1  ||  numberSelected > nameList.length) {
                new TicTacToeError(ErrorType.ERROR210).display();
                continue;
            }
            
            name = nameList[numberSelected-1]; // get the name from the list
            valid = true;
      
        } while (!valid);
        
        return name;
    }
    
    
    public void displayNameList() {
        System.out.println("\n\t===============================================================");
        System.out.println("\tSelect the player/s who will be playing the game.");
        System.out.println("\tEnter the number of a player below:");

        for (int i = 0; i < this.playerNames.length; i++) {
            int namePosition = i+1;
            System.out.println("\t   " + namePosition + "\t" + playerNames[i]);
        }
        System.out.println("\t===============================================================\n");
    }
    
}
