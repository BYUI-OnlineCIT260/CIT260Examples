/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.awt.Point;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author jacksonrkj
 */
public class Board  {
    
    int rowCount = 3;
    int columnCount = 3;
    
    Location[][] boardLocations;
    

    public Board() {
    }

    
    public void displaySize() {
        System.out.println("\n\tThe board is " + this.rowCount + " by " 
                           + this.columnCount + " in size.");
    }

  

    

}
