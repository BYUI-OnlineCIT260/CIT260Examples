/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.awt.Dimension;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author jacksonrkj
 */
public class GamePreferencesMenuControl {
    
    private Game game;

    public GamePreferencesMenuControl(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
          
    
    public void getMarker(Player player) {
        System.out.println("\n\tgetMarker called");
   
    }
    
     public boolean getDimensions() {

       System.out.println("\n\tgetDimensions called");
       return true;
    }   
    
    
}
