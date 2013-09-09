/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;


/**
 *
 * @author jacksonrkj
 */
public class Player {
    
    String name;
    double age;
    String playerType;
    long wins = 0;
    long losses = 0;
    long ties = 0;
    String marker;
    
    

    public Player() {
    }

    
    public void displayName() {
        System.out.println("\t\tGreetings, my name is "  + this.name); 
    }

}
