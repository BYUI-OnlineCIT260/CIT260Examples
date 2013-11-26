/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.tictactoe.views;


import byui.cit260.tictactoe.models.Player;
import byui.cit260.tictactoe.models.Game;
import byui.cit260.tictactoe.interfaces.EnterInfo;
import java.awt.Dimension;
import java.util.Scanner;
import java.util.regex.Pattern;
import byui.cit260.tictactoe.controls.GamePreferencesMenuControl;
import byui.cit260.tictactoe.controls.TicTacToe;
import byui.cit260.tictactoe.controls.TicTacToeError;

/**
 *
 * @author jacksonrkj
 */




public class GamePreferencesMenuView extends Menu  implements EnterInfo {
    
    private Game game;
    private GamePreferencesMenuControl gamePreferenceCommands;

    private final static String[][] menuItems = {
        {"1", "Change Marker of the first Player"},
        {"2", "Change Marker of the second Player"},
        {"D", "Change the dimensions of the board"},
        {"Q", "Return to game menu"}
    };

    public GamePreferencesMenuView() {
        super(GamePreferencesMenuView.menuItems);
        gamePreferenceCommands = new GamePreferencesMenuControl();
    }
    
    @Override
    public String getInput(Object object) {       
        this.game = (Game) object;
        this.gamePreferenceCommands.setGame(game);
        
        String gameStatus = Game.PLAYING;
        do {
            this.display();

            // get commaned entered
            String command = this.getCommand();
            
            switch (command) {
                case "1":
                    getMarker(this.game.getPlayerA());
                    break;
                case "2":
                    getMarker(this.game.getPlayerB());
                    break;
                case "D":
                    this.getDimensions();
                    break;
                case "Q":
                    return Game.QUIT;
            }
        } while (!gameStatus.equals(Game.QUIT));

        return gameStatus;
    }
    
    
    public void getMarker(Player player) {
        String marker = null;
                
        boolean valid = false;
        do {
            System.out.println("\n\t" + player.getName() 
                + ", enter a single character that will be used to mark your squares in the game.");
            Scanner in = TicTacToe.getInputFile();
            marker = in.nextLine();
            if (marker == null  || marker.length() < 1) {
                continue;
            }
            
            marker = marker.trim().substring(0, 1);
            marker = marker.toUpperCase();
            valid = this.gamePreferenceCommands.saveMarker(player, marker);
        } while (!valid);

        this.gamePreferenceCommands.saveMarker(player, marker);
   
    }
    
     public boolean getDimensions() {

        if (game.getStatus().equals(Game.PLAYING)) {
            new TicTacToeError().display("You can not change the dimensions "
              + "of the board once the game has been started. "
              + "\n\tStart a new game and then change the dimensions "
              + "of the board. ");
            return false;
        }

        // prompt for the row and column numbers
        System.out.println("\n\tEnter the number of rows and columns in the board (For example: 3 3)");

        Scanner inFile = TicTacToe.getInputFile(); // get input file 
        
        // read the row and column coordinates
        String[] valuesEntered;
        Dimension dimension = null;
        do {
            String strNoRowsColumns = inFile.nextLine(); // read in row and column
            strNoRowsColumns = strNoRowsColumns.trim(); // trim all blanks from front and end 
            strNoRowsColumns = strNoRowsColumns.replace(',', ' '); // replace commas with a blank
            valuesEntered = strNoRowsColumns.split("\\s"); // tokenize the string

            if (valuesEntered.length < 1) {
                new TicTacToeError().display(
                        "You must enter two numbers, the number rows and columns, "
                        + "or a \"Q\" to quit. Try again.");
                continue;
            } else if (valuesEntered.length == 1) {
                if (valuesEntered[0].toUpperCase().equals("Q")) { // Quit?
                    return false;
                }  // wrong number of values entered.
     
                // wrong number of values entered.
                new TicTacToeError().display(
                       "You must enter two numbers, the number rows and columns, "
                       + "or a \"Q\" to quit. Try again.");
                continue;
            }
            
            // user java regular expression to check for valid integer number?
            Pattern digitPattern = Pattern.compile(".*\\D.*");
            if (digitPattern.matcher(valuesEntered[0]).matches()  || 
                digitPattern.matcher(valuesEntered[1]).matches()
               ) {
                new TicTacToeError().display(
                        "You must enter two numbers, the number rows and columns, "
                        + "or a \"Q\" to quit. Try again.");
                continue;
            }
            
            int rowsEntered = Integer.parseInt(valuesEntered[0]);
            int columnsEntered = Integer.parseInt(valuesEntered[1]);
            dimension = new Dimension(rowsEntered, columnsEntered);

        } while (dimension == null);
        
        this.gamePreferenceCommands.saveDimensions(dimension);
        
        return true;
    }
   
}
