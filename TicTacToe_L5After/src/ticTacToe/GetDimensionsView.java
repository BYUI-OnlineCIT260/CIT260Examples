/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.awt.Dimension;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jacksonrkj
 */
public class GetDimensionsView {
    
        
     public Dimension getInput(Game game) {

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
                new TicTacToeError().displayError(
                        "You must enter two numbers, the number rows and columns, "
                        + "or a \"Q\" to quit. Try again.");
                continue;
            } else if (valuesEntered.length == 1) {
                if (valuesEntered[0].toUpperCase().equals("Q")) { // Quit?
                    return null;
                }  // wrong number of values entered.
     
                // wrong number of values entered.
                new TicTacToeError().displayError(
                       "You must enter two numbers, the number rows and columns, "
                       + "or a \"Q\" to quit. Try again.");
                continue;
            }
            
            // user java regular expression to check for valid integer number 
            // for both numbers
            String regExpressionPattern = ".*\\d.*";
            if (!valuesEntered[0].matches(regExpressionPattern) ||
                !valuesEntered[1].matches(regExpressionPattern)) {
                new TicTacToeError().displayError(
                        "You must enter two numbers, the number rows and columns, "
                        + "or a \"Q\" to quit. Try again.");
                continue;
            }
            
            int rowsEntered = Integer.parseInt(valuesEntered[0]);
            int columnsEntered = Integer.parseInt(valuesEntered[1]);
            
            if (rowsEntered < 3 || rowsEntered > 10) {
            new TicTacToeError().displayError(
                    "The number of rows must be between 3 -10 and the "
                    + "number of columns must be between 3 -10 ");
            continue;
            }

            if (columnsEntered < 3 || columnsEntered > 10) {
                new TicTacToeError().displayError(
                        "The number of rows must be between 3 -10 and the "
                        + "number of columns must be between 3 -10 ");
                continue;
            }

            
            
            dimension = new Dimension(rowsEntered, columnsEntered);

        } while (dimension == null);
        
        
        
        return dimension;
    }
    
}
