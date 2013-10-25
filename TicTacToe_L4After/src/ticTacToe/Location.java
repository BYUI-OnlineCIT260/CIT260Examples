/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

/**
 *
 * @author jacksonrkj
 */
public class Location {
    
    public int row;
    public int column;
    public Player player;

    Location() {
    }
        
    public void occupyLocation(Player player) {
        this.player = player;
    }
    
}
