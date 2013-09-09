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
     
    private static final Scanner inFile = new Scanner(System.in);
    private static final HelpMenuView helpMenu = new HelpMenuView();
    private static final GamePreferencesMenuView GamePreferencesMenu = new GamePreferencesMenuView();
    
    
    // Instance variables
    private static String[] nameList;
    
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
        
    public static Scanner getInputFile() {
        return TicTacToe.inFile;
    }
    
    public static HelpMenuView getHelpMenu() {
        return TicTacToe.helpMenu;
    }


    public static GamePreferencesMenuView getGamePreferencesMenu() {
        return GamePreferencesMenu;
    }

    public static String[] getNameList() {
        return nameList;
    }

    public static void setNameList(String[] nameList) {
        TicTacToe.nameList = nameList;
    }
    
        
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.display();
        MainMenuView mainMenu = new MainMenuView();

        mainMenu.getInput(null);
        TicTacToe.inFile.close();
    }
    
    private void display() {
        System.out.println(TicTacToe.WELCOME);
    }
    

}
