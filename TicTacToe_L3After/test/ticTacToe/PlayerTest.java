/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jacksonrkj
 */
public class PlayerTest {
    
    public PlayerTest() {
    }

    /**
     * Test of displayName method, of class Player.
     */
    @Test
    public void testDisplayName() {
        System.out.println("displayName");
        Player instance = new Player();
        instance.displayName();        
    }

    /**
     * Test of getWinningPercentage method, of class Player.
     */
    @Test
    public void testGetWinningPercentage() {
        
        // test 1
        System.out.println("getWinningPercentage - Test 1");
        long wins = 3L;
        long losses = 4L;
        long ties = 8L;      
        double expResult = 20L;      
        Player instance = new Player();
        double result = instance.getWinningPercentage(wins, losses, ties);       
        assertEquals(expResult, result, 0.0);
        
        // test 2
        System.out.println("getWinningPercentage - Test 2");
        wins = -5L;
        losses = 4L;
        ties = 8L;     
        expResult = -999L;
        result = instance.getWinningPercentage(wins, losses, ties);
        assertEquals(expResult, result, 0.0);
        
        // test 3
        System.out.println("getWinningPercentage - Test 3");
        wins = 5L;
        losses = -4L;
        ties = 8L;     
        expResult = -999L;
        result = instance.getWinningPercentage(wins, losses, ties);
        assertEquals(expResult, result, 0.0);
                       
        // test 4
        System.out.println("\ngetWinningPercentage - Test 4");
        wins = 5L;
        losses = 4L;
        ties = -8L;     
        expResult = -999L;
        result = instance.getWinningPercentage(wins, losses, ties);
        assertEquals(expResult, result, 0.0);
                        
        // test 5
        System.out.println("getWinningPercentage - Test 5");
        wins = 0L;
        losses = 0L;
        ties = 0L;     
        expResult = 0L;
        result = instance.getWinningPercentage(wins, losses, ties);
        assertEquals(expResult, result, 0.0);
        
    }
}