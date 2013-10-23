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
public class GetMarkerView {
    
    private Game game; 
    
    /*
     * default constructor
     */
    public GetMarkerView(Game game) {
        this.game = game;
        
    } 
    
    public String getInput(Player player) {

        String newMarker = null;
        Scanner in = new Scanner(System.in);
                
        boolean valid = false; // flag to indicate if valid character entered
        while (!valid) {
            // prompt for input
            System.out.println("\n\t" + player.getName() 
                    + ", enter a single character that will be used to mark "
                    + "your squares in the game.");
            
            // get input from user           
            newMarker = in.nextLine();
           
            // no marker entered?
            if (newMarker == null  || newMarker.length() < 1) {
                continue;
            }
            
            // grab only the first character and convert it to upper case
            newMarker = newMarker.substring(0, 1).toUpperCase();
            
            if (newMarker.equals("Q")) { // Quit?
                return null;
            }
            
            // Check to see if the marker is already in use
            if (this.game.getPlayerA().getMarker().equals(newMarker) ||
                this.game.getPlayerB().getMarker().equals(newMarker) ) {
               new TicTacToeError().displayError(
                        "This marker is currently in use. " +
                        "Select a different character");
               continue; 
            }
            
            valid = true; // signal that a valid marker was entered
        }
        
        return newMarker;
    }
    
}
