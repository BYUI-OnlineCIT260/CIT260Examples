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


public class TicTacToe {
     
    
    // Instance variables
    private static Player[] playerList;
    
    private final static String WELCOME = 
              "\n\t***********************************************************************"
            + "\n\t* Welcome to the game of Tic-Tac-Toe!                                 *"                            
            + "\n\t* You will be playing against a partner. The object of the game       *"
            + "\n\t* is to mark three adjacent squares either horizontally, vertically   *"
            + "\n\t* with your marker before your opponent does.                         *" 
            + "\n\t*                                                                     *"
            + "\n\t* Good Luck!!!                                                        *"
            + "\n\t***********************************************************************"
            + "\n";

    
    public TicTacToe() {
        
    }


    public static Player[] getPlayerList() {
        return playerList;
    }

    public static void setPlayerList(Player[] playerList) {
        TicTacToe.playerList = playerList;
    }
    
        
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.display();
        
        
        
        MainMenuView mainMenu = new MainMenuView();
        mainMenu.getInput();       
    }
    
    private void display() {
        System.out.println(TicTacToe.WELCOME);
    }
    
   

}
