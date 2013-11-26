/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.tictactoe.enums;

/**
 *
 * @author jacksonrkj
 */


public enum StatusType {
    CONTINUE ("CONTINUE"),
    NEW_GAME ("NEW_GAME"),
    PLAYING ("PLAYING"), 
    WINNER ("WINNER"), 
    TIE ("TIE"), 
    QUIT ("QUIT"), 
    ERROR ("ERROR"),
    EXIT ("EXIT");
    
    String value;
    
    StatusType(String value) {
        this.value = value;   
    }
    
    public String getValue() {
        return value;
    }
}
