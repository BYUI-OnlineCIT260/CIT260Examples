/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;


import java.util.Scanner;

/**
 *
 * @author jacksonrkj
 */




public class GamePreferencesMenuView  {
    
    private Game game;
    private GamePreferencesMenuControl gamePreferenceControl;

    private final static String[][] menuItems = {
        {"1", "Change Marker of the first Player"},
        {"2", "Change Marker of the second Player"},
        {"D", "Change the dimensions of the board"},
        {"Q", "Return to game menu"}
    };

    public GamePreferencesMenuView(Game game) {
        this.game = game;
        this.gamePreferenceControl = new GamePreferencesMenuControl(this.game);
    }
    
    public final void display() {
        System.out.println("\n\t===============================================================");
        System.out.println("\tEnter the letter associated with one of the following commands:");

        for (int i = 0; i < GamePreferencesMenuView.menuItems.length; i++) {
            System.out.println("\t   " + menuItems[i][0] + "\t" + menuItems[i][1]);
        }
        System.out.println("\t===============================================================\n");
    }

    private boolean validCommand(String command) {
        String[][] items = GamePreferencesMenuView.menuItems;

        for (String[] item : GamePreferencesMenuView.menuItems) {
            if (item[0].equals(command)) {
                return true;
            }
        }
        return false;
    }

    private final String getCommand() {

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
    
    
    public String getInput() { 
       this.game = game;
       String command = "";
        
        do {
            this.display();

            // get commaned entered
           command = this.getCommand();
            
            switch (command) {
                case "1":
                    this.gamePreferenceControl.getMarker(this.game.getPlayerA());
                    break;
                case "2":
                    this.gamePreferenceControl.getMarker(this.game.getPlayerB());
                    break;
                case "D":
                    gamePreferenceControl.getDimensions();
                    break;
                case "Q":
                    return "QUIT";
            }
        } while (!command.equals("Q"));

        return "PLAYING";
    }
    
    
}
