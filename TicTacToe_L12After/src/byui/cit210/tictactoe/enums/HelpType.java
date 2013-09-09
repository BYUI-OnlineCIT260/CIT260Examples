/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.enums;

/**
 *
 * @author jacksonrkj
 */
public enum HelpType {
        INSTRUCTIONS ("The objective of the game is to be the first player to mark three "
                + "squares vertically, horizontally or diagonally. \n\nEach player takes "
                + "turns placing their marker in one of the locations on the "
                + "board. \n\nThe first player to get \"three-in-a-row\" is the winner."),
        
        BOARD ("The game board for Tic-Tac-Toe. It consist of a grid of "
                + "locations. \n\nPlayers place there marker on the different locations "
                + "on the board in an effort to win the game. \n\nThe default board is "
                + "3 rows by 3 columns."),
          
        ONE_PERSON_GAME ("In a one person game, you will play against the computer."),

        TWO_PERSON_GAME ("In a two person game, you will play against another real player."), 

        PLAYER ("A real player manually takes their turn by placing their mark "
                + "in an unused location on the board."),

        
        MARKER ("A symbol that \"marks\" the locations in the board that are occupied "
                + "by a player. "
                + "\n\nThe default markers are \"X\" and \"O\".");
        
        String helpText;

    private HelpType(String helpText) {
        this.helpText = helpText;
    }

    public String getHelpText() {
        return helpText;
    }
    
    

}
