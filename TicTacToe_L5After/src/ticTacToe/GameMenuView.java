/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.awt.Point;
import java.util.Scanner;

/**
 *
 * @author jacksonrkj
 */
public class GameMenuView {
    
    private Game game;
    private GameMenuControl gameMenuControl = null; 


    private final static String[][] menuItems = {
        {"T", "Take your turn"},
        {"D", "Display the board"},
        {"N", "New Game"},
        {"R", "Report stastics"},
        {"P", "Change game preferences"},
        {"H", "Help"},
        {"Q", "QUIT"}
    };

    public GameMenuView(Game game) {
        this.gameMenuControl = new GameMenuControl(game);
        
    }

    
    
    public Object getInput(Object object) {
        this.game = (Game) object;

        this.game.setStatus(Game.CONTINUE);
        
        String gameStatus = this.game.getStatus();
        do {
     
            this.display();
            
            // get commaned entered
            String command = this.getCommand();
            switch (command) {
                case "T":
                    this.gameMenuControl.takeTurn();
                    break;
                case "D":
                    this.gameMenuControl.displayBoard();
                    break;
                case "N":
                    this.gameMenuControl.startNewGame();
                    break;
                case "R":
                    this.gameMenuControl.displayStatistics();
                    break;
                case "P":
                    this.gameMenuControl.displayPreferencesMenu();
                    break;
                case "H":
                    this.gameMenuControl.displayHelpMenu();
                    break;
                case "Q":
                    gameStatus = "QUIT";
                    break;
            }
        } while (!gameStatus.equals("QUIT"));

        return Game.PLAYING;
    }
    


    public final void display() {
        System.out.println("\n\t===============================================================");
        System.out.println("\tEnter the letter associated with one of the following commands:");

        for (int i = 0; i < GameMenuView.menuItems.length; i++) {
            System.out.println("\t   " + menuItems[i][0] + "\t" + menuItems[i][1]);
        }
        System.out.println("\t===============================================================\n");
    }

    private boolean validCommand(String command) {
        String[][] items = GameMenuView.menuItems;

        for (String[] item : GameMenuView.menuItems) {
            if (item[0].equals(command)) {
                return true;
            }
        }
        return false;
    }

    protected final String getCommand() {

        Scanner inFile = TicTacToe.getInputFile();
        String command;
        boolean valid = false;
        do {
            command = inFile.nextLine();
            command = command.trim().toUpperCase();
            valid = validCommand(command);
            if (!validCommand(command)) {
                new TicTacToeError().displayError("Invalid command. Please enter a valid command.");
                continue;
            }
                
        } while (!valid);
        
        return command;
    }



   
}
