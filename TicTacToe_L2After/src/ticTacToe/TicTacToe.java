
package ticTacToe;



/**
 *
 * @author jacksonrkj
 */


public class TicTacToe {
    
    String welcomeMsg = 
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
    
    
        
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.display();
        
        Board board = new Board();
        board.displaySize();
        
        Game game = new Game();
        game.displayPlayers();
        
        
    }
    
    private void display() {
        System.out.println(this.welcomeMsg);
    }
    

}
