/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

/**
 *
 * @author jacksonrkj
 */
public class BoardView {
    
    Board board;
    
    public void displayBoard(Board board) {
        this.board = board;
        
        this.printHeadRow(); // print the header row for the board
        this.printDividerRow(); // print the top divider row of the board
        
        // for all rows in the board
        for (int i = 0; i < this.board.getRowCount(); i++) {
            // get the list of list of columns locations in the row
            Player[] rowOfLocations = this.board.getBoardLocations()[i];
            
            // print the contents of each location in the row
            this.printRow(i+1, rowOfLocations); 
            
            this.printDividerRow();// print the ending divider for the row
        }
        
        System.out.println(); // print blank line after the board
         
        return;
    }

    private void printHeadRow() {

        // print the first column in the header row
        System.out.print("\n\t      1   ");
        
        // print remaining header cells in row between the first and last column
        int columnsInRow = this.board.getColumnCount();
        for (int i = 1; i < columnsInRow - 1; i++) {
            int col = i + 1;
            System.out.print("  " + col + "   ");
        }
        // print the header for the last column in the header
        System.out.print(" " + columnsInRow + "   ");
    }

    private void printDividerRow() {

        // print the divider for the first column in the row
        System.out.print("\n\t  |------");
        
        int columnsInRow = this.board.getColumnCount();
        // print remaining divider for each column between the first and last
        for (int i = 1; i < columnsInRow - 1; i++) {
            System.out.print("------");
        }
        // print the row divider for the last column in the row
        System.out.print("-----|");
    }

    private void printRow(int rowNumber, Player[] rowLocations) {
        
        // print contents of first column in the row
        String letter = " ";
        if (rowLocations[0] != null) {
            letter = rowLocations[0].getMarker();
        }
        System.out.print("\n\t" + rowNumber + " |  " + letter + "  |");

        // print the contents of the rest of the columns in the row 
        for (int i = 1; i < rowLocations.length; i++) {
            if (rowLocations[i] == null) {
                letter = " ";
            } else {
                letter = rowLocations[i].getMarker();
            }
            
            System.out.print("  " + letter + "  |");
        }
    }

}
