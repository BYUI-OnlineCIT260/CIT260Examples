/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.views;


import byui.cit210.tictactoe.enums.StatusType;
import byui.cit210.tictactoe.exceptions.MenuException;
import byui.cit210.tictactoe.exceptions.TicTacToeException;
import byui.cit210.tictactoe.intefaces.EnterInfo;

/**
 *
 * @author jacksonrkj
 */
public class HelpMenuView extends Menu implements EnterInfo  {
   
    private final static String[][] menuItems = {
        {"B", "The board"},
        {"C", "A computer player"}, 
        {"G", "The Tic-Tac-Toe game"},
        {"L", "A location"},
        {"M", "A marker"},
        {"R", "A regular player"},        
        {"Q", "Quit Help"}        
    };
    
    public HelpMenuView() {
        super(HelpMenuView.menuItems);
    } 
    
    @Override
    public StatusType getInput(Object object) {       
            
            StatusType gameStatus = StatusType.PLAYING;
            do {
                try {
                    
                    this.display();

                    // get commaned entered
                    String command = this.getCommand();
                    switch (command) {
                        case "B":
                            this.displayHelp(Help.BOARD);
                            break;
                        case "C":
                            this.displayHelp(Help.COMPUTER_PLAYER);
                            break;
                        case "G":
                            this.displayHelp(Help.GAME);
                            break;                  
                        case "L":
                            this.displayHelp(Help.LOCATION);
                            break;
                        case "M":
                            this.displayHelp(Help.MARKER);
                            break;
                         case "R":
                            this.displayHelp(Help.REAL_PLAYER);
                            break; 
                        case "Q": 
                            return StatusType.QUIT;
                    }
                    
                }
                catch (TicTacToeException e) {
                    System.out.println("\n" + e.getMessage());
                }
                
            } while (gameStatus != StatusType.QUIT);  

         
         return gameStatus;
    }
    
    private void displayHelp(Help helpText) {
        StringBuilder dividerLine = new StringBuilder(80);
        for (int i = 0; i < 80; i++) {
            dividerLine.insert(i, '~');
        }
        System.out.println("\t" + dividerLine.toString());
        System.out.println(helpText.getHelpText());
        System.out.println("\t" + dividerLine.toString());
    }
    
    // nested class to define the text for each help item in the menu
    private enum Help {
        BOARD ("\tThe game board for Tic-Tac-Toe. It consist of a grid of "
                + "\n\tlocations. Players place there marker on the different locations "
                + "\n\ton the board in an effort to win the game. The default board is "
                + "\n\t3 rows by 3 columns."),
        
        GAME ("\tThe objective of the game is to be the first player to mark three "
                + "\n\tsquares vertically, horizontally or diagonally. Each player takes "
                + "\n\tturns placing their marker in one of the locations on the "
                + "\n\tboard. The first player to get \"three-in-a-row\" is the winner."),
        
        REAL_PLAYER ("\tA real player manually takes their turn by placing their mark "
                    + "\n\tin an unused location on the board."),
        
        COMPUTER_PLAYER ("\tA computer based player utomatically takes its turn "
                        + "\n\timmediatly after a real player in a single player game."),
        
        LOCATION ("\tA location on the board where a player can place their marker"),
        
        MARKER ("\tA symbol that \"marks\" the locations in the board that are occupied "
                + "by a player. "
                + "\n\tThe default markers are \"X\" and \"O\".");
        
        String helpText;
        
        Help(String helpText) {
            this.helpText = helpText;
        }

        public String getHelpText() {
            return helpText;
        }

        public void setHelpText(String helpInfo) {
            this.helpText = helpInfo;
        }
        
    }
  
}
