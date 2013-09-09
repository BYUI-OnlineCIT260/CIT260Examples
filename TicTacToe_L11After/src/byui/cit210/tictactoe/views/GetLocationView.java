/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.views;

import byui.cit210.tictactoe.controls.TicTacToe;
import byui.cit210.tictactoe.enums.ErrorType;
import byui.cit210.tictactoe.exceptions.GameException;
import byui.cit210.tictactoe.exceptions.TicTacToeException;
import byui.cit210.tictactoe.intefaces.EnterInfo;
import byui.cit210.tictactoe.models.Game;
import java.awt.Point;
import java.util.Scanner;

    
/**
 *
 * @author jacksonrkj
 */
public class GetLocationView implements EnterInfo {
    private Game game;
    
    @Override
    public Object getInput(Object object) throws TicTacToeException {
        this.game = (Game) object;
        Scanner inFile = TicTacToe.getInputFile(); // get input file 

        // prompt for the row and column numbers
        System.out.println("\n\n\t" + game.getCurrentPlayer().getName() + " it is your turn."
                + " Enter a row and column number (For example: 1 3)");

        // read the row and column coordinates
        String[] coordinates;
        Point location = null;
        
        boolean valid = false;

        try {
            do {
                String strRowColumn = inFile.nextLine(); // read in row and column
                strRowColumn = strRowColumn.trim(); // trim all blanks from front and end 
                strRowColumn = strRowColumn.replace(',', ' '); // replace commas with a blank
                coordinates = strRowColumn.split("\\s"); // tokenize the string

                if (coordinates.length < 1) { // no coordinates specified
                    throw new GameException(ErrorType.ERROR109.getMessage());
                }    
                
                else if (coordinates.length == 1) { // only one coordinate
                    if (coordinates[0].toUpperCase().equals("Q")) { // Quit?
                        return null;
                    } else { // wrong number of values entered.
                        throw new GameException(ErrorType.ERROR109.getMessage());
                    }
                }
                try {
                    int row = Integer.parseInt(coordinates[0]);
                    int column = Integer.parseInt(coordinates[1]);
                    
                    location = new Point(row-1, column-1);
                    return location;
                } catch (NumberFormatException nfex) {
                    throw new GameException(ErrorType.ERROR109.getMessage());
                }
            } while (!valid);
            
        } catch (GameException pex) {
            System.out.println(pex.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TicTacToeException(e.getCause().toString() + ": " + e.getMessage());
        }
    }

}
