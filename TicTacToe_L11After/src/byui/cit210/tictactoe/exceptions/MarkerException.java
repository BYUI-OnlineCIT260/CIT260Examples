/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.exceptions;

/**
 *
 * @author jacksonrkj
 */
public class MarkerException extends Exception {

    public MarkerException() {
    }

    public MarkerException(String message) {
        super(message);
    }

    public MarkerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarkerException(Throwable cause) {
        super(cause);
    }

    public MarkerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
