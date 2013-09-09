/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.controls;

import byui.cit210.tictactoe.enums.ErrorType;
import byui.cit210.tictactoe.views.GamePreferencesFrame;
import byui.cit210.tictactoe.views.MainFrame;
import byui.cit210.tictactoe.models.Player;

/**
 *
 * @author jacksonrkj
 */


public class TicTacToe {
    public static MainFrame mainFrame = null;
    private GamePreferencesFrame gamePreferencesFrame = null;
    /**
     *
     */
    
    private Player[] players = new Player[10];

    public TicTacToe() {
        
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

        
    public static void main(String[] args) {
        TicTacToe ticTacToe = null;
        try {  
            ticTacToe = new TicTacToe();
            
              /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    TicTacToe.mainFrame = new MainFrame();
                    TicTacToe.mainFrame.setVisible(true);
                }
            });
        } 

        catch (Throwable ex) {     
            ErrorType.displayErorrMsg("Unexpected error: " + ex.getMessage());
            ErrorType.displayErorrMsg(ex.getStackTrace().toString());           
        } 
        finally {
            if (TicTacToe.mainFrame != null) {
                TicTacToe.mainFrame.dispose();
            }
        }
        
      
    }


}
