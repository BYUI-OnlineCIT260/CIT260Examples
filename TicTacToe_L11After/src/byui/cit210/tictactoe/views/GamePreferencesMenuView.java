/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.views;

import byui.cit210.tictactoe.controls.GamePreferencesMenuControl;
import byui.cit210.tictactoe.controls.TicTacToe;
import byui.cit210.tictactoe.enums.ErrorType;
import byui.cit210.tictactoe.enums.StatusType;
import byui.cit210.tictactoe.exceptions.GameException;
import byui.cit210.tictactoe.exceptions.TicTacToeException;
import byui.cit210.tictactoe.intefaces.EnterInfo;
import byui.cit210.tictactoe.models.Game;
import byui.cit210.tictactoe.models.Player;
import java.awt.Dimension;
import java.util.Scanner;

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
    public StatusType getInput(Object object) throws TicTacToeException {       
        this.game = (Game) object;
        this.gamePreferenceCommands.setGame(game);
        
        StatusType gameStatus = StatusType.PLAYING;
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
                    return StatusType.QUIT;
            }
        } while (gameStatus != StatusType.QUIT);

        return gameStatus;
    }
    
    
    public void getMarker(Player player)  {
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
            try {
                this.gamePreferenceCommands.saveMarker(player, marker);
                valid = true;
            } catch (TicTacToeException ex) {
                continue;
            }
        } while (!valid);

        
   
    }

     public void getDimensions() {
        try {
            if (game.getStatus() == StatusType.PLAYING) {
                throw new GameException("\n\t" + ErrorType.ERROR101.getMessage());
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
                    System.out.println("\n\t" + ErrorType.ERROR102.getMessage());
                    continue;
                } 

                else if (valuesEntered.length == 1) {
                    if (valuesEntered[0].toUpperCase().equals("Q")) { // Quit?
                        return;
                    } 
                     // wrong number of values entered.
                    System.out.println("\n\t" + ErrorType.ERROR102.getMessage());
                    continue;
                }
                try {
                    int rowsEntered = Integer.parseInt(valuesEntered[0]);
                    int columnsEntered = Integer.parseInt(valuesEntered[1]);
                    dimension = new Dimension(rowsEntered, columnsEntered);
                } catch (NumberFormatException nfex) {
                    System.out.println("\n\t" + ErrorType.ERROR207.getMessage());
                    continue;
                }

            } while (dimension == null);


            this.gamePreferenceCommands.saveDimensions(dimension);
        } catch (GameException gex) {
            System.out.println("\n\t" + gex.getMessage());
        }
        
    }
   
}
