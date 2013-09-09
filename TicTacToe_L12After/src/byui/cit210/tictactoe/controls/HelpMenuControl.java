/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.controls;

import byui.cit210.tictactoe.enums.ErrorType;
import byui.cit210.tictactoe.enums.HelpType;
import static byui.cit210.tictactoe.enums.HelpType.INSTRUCTIONS;
import byui.cit210.tictactoe.exceptions.TicTacToeException;



/**
 *
 * @author jacksonrkj
 */
public class HelpMenuControl {
    
    public String getHelpText(HelpType command) throws TicTacToeException {
        String helpText = "";
        switch (command) {
            case INSTRUCTIONS:
            case ONE_PERSON_GAME:
            case TWO_PERSON_GAME:
            case PLAYER:
            case BOARD:
            case MARKER:
                helpText = command.getHelpText();
                break;
            default:
                throw new TicTacToeException(ErrorType.ERROR105.getMessage());
        }

        return helpText;
    }
    
}
