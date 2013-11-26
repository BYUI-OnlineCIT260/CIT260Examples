/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.tictactoe.views;

import byui.cit260.tictactoe.enums.ErrorType;
import byui.cit260.tictactoe.models.Board;
import byui.cit260.tictactoe.models.Game;
import byui.cit260.tictactoe.interfaces.EnterInfo;
import java.awt.Point;
import java.util.Scanner;
import java.util.regex.Pattern;
import byui.cit260.tictactoe.controls.TicTacToe;
import byui.cit260.tictactoe.controls.TicTacToeError;

    
/**
 *
 * @author jacksonrkj
 */
public class GetLocationView implements EnterInfo {
    private Game game;
    
    @Override
    public Object getInput(Object object) {
        this.game = (Game) object;
        Scanner inFile = TicTacToe.getInputFile(); // get input file 

        // prompt for the row and column numbers
        System.out.println("\n\n\t" + game.getCurrentPlayer().getName() + " it is your turn."
                + " Enter a row and column number (For example: 1 3)");

        // read the row and column coordinates
        String[] coordinates;
        Point location = null;
        
        boolean valid = false;

        do {
            String strRowColumn = inFile.nextLine(); // read in row and column
            strRowColumn = strRowColumn.trim(); // trim all blanks from front and end 
                
            strRowColumn = strRowColumn.replace(',', ' '); // replace commas with a blank
            coordinates = strRowColumn.split("\\s"); // tokenize the string

            if (coordinates.length < 1) { // no coordinates specified
                new TicTacToeError(ErrorType.ERROR102).display();
                continue;
            }    

            else if (coordinates.length == 1) { // only one coordinate
                if (coordinates[0].toUpperCase().equals("Q")) { // Quit?
                    return null;
                } else { // wrong number of values entered.
                    new TicTacToeError(ErrorType.ERROR102).display();
                    continue;
                }
            }

            // user java regular expression to check for valid integer number?
            Pattern digitPattern = Pattern.compile(".*\\D.*");
            if (digitPattern.matcher(coordinates[0]).matches()  || 
                digitPattern.matcher(coordinates[1]).matches()
               ) {
                new TicTacToeError(ErrorType.ERROR102).display();
                continue;
            }
            
            int row = Integer.parseInt(coordinates[0]);
            int column = Integer.parseInt(coordinates[1]);
            
            Board board = game.getBoard();
            if (row < 1   ||  row > board.getRowCount() ||
                column < 1  ||  column > board.getColumnCount()) {
                new TicTacToeError(ErrorType.ERROR207).display();
                continue;
            }

            location = new Point(row-1, column-1);
            valid = true;

        } while (!valid);
        
        return location;
            
    }

}
