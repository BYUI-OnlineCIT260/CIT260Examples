/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.exceptions;

/**
 *
 * @author jacksonrkj
 */
public class TicTacToeException extends Exception {

    public TicTacToeException() {
    }

    public TicTacToeException(String message) {
        super(message);
    }

    public TicTacToeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicTacToeException(Throwable cause) {
        super(cause);
    }
    
}
