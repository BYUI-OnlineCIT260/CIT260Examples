/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;


/**
 *
 * @author jacksonrkj
 */
public class Player {

    public static final String REGULAR_PLAYER = "REGULAR";
    public static final String COMPUTER_PLAYER = "COMPUTER"; 
    
    public String name;
    public double age;
    public String playerType;
    public long wins = 0;
    public long losses = 0;
    public long ties = 0;
    public String marker;
    

    public Player() {
    }

    public Player(String playerType, String marker) {
        this.playerType = playerType;
        this.marker = marker;
    } 

    public String getPlayerStastics() {
        String playerStatistics = 
                this.name + " has won "
                + this.getWinningPercentage(this.wins, this.losses, this.ties) + "% of the games."
                + "\n\t" + this.wins + " wins, "
                + this.losses + " losses and "
                + this.ties + " ties.";
        
        return playerStatistics;
    }

    
    public double getWinningPercentage(long wins, long losses, long ties) {
     
        if (wins < 0 ) {
            System.out.println("\n\tThe number of wins must be "
                    + "greater than or equal to zero.");
            return -999;
        }
        
        if (losses < 0 ) {
            System.out.println("\n\tThe number of losses must be "
                    + "greater than or equal to zero.");
            return -999;
        }
        
        if (ties < 0 ) {
            System.out.println("\n\tThe number of ties must be "
                    + "greater than or equal to zero.");
            return -999;
        }
        
        double totalScore = wins + losses + ties;
        
        if (totalScore ==  0) {
            return 0;
        }
        
        double winLossRatio = wins / totalScore;
        return winLossRatio * 100;
    }

    
}
