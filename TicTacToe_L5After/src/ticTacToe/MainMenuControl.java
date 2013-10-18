/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.util.Scanner;

/**
 *
 * @author jacksonrkj
 */
public class MainMenuControl {
    
    
    
    
    
    public void startGame(long noPlayers) {
                
        if (noPlayers != 1  &&  noPlayers != 2) {
            new TicTacToeError().displayError("startGame - invalid number of players specified.");
            return;
        }
        
        Game game;
        if (noPlayers == 1) {
            game = this.createGame("ONE_PLAYER");
        }
        else {
            game = this.createGame("TWO_PLAYER");
        }

        GameMenuView gameMenu = new GameMenuView(game);
        gameMenu.getInput(game);
    }

    
    
    private Game createGame(String gameType) {
        Game game = null;
        Player playerA = null;
        Player playerB = null;
        
        if (gameType == null) {
            new TicTacToeError().displayError("MainCommands - create: gameType is null");
            return null;
        }
        
        if (gameType.equals(Game.ONE_PLAYER)) {
            game = new Game(Game.ONE_PLAYER);
            playerA = new Player(Player.REGULAR_PLAYER, game.PLAYER_A_DEFAULT_MARKER);
            playerA.setName("Player 1");
            playerB = new Player(Player.COMPUTER_PLAYER, game.PLAYER_B_DEFAULT_MARKER);
            playerB.setName("Computer");
        }
        else if (gameType.equals(Game.TWO_PLAYER)) {
            game = new Game(Game.TWO_PLAYER);
            playerA = new Player(Player.REGULAR_PLAYER, game.PLAYER_A_DEFAULT_MARKER);
            playerA.setName("Player 1");
            playerB = new Player(Player.REGULAR_PLAYER, game.PLAYER_B_DEFAULT_MARKER);
            playerB.setName("Player 2");

        }

        game.setPlayerA(playerA);
        game.setPlayerB(playerB);
        
        return game;
    } 
    
    
    private String quitGame() {
        System.out.println("\n\tAre you sure you want to quit? (Y or N)");
        Scanner inFile = new Scanner(System.in);
        String answer = inFile.next().trim().toUpperCase();
        if (answer.equals("Y")) {
            return Game.EXIT;
        }

        return Game.PLAYING;
    }
    
    
    /*
     * Display help menu action
     */
    public void displayHelpMenu() {
        HelpMenuView helpMenuView = new HelpMenuView();
        helpMenuView.getInput();
    }
    
}
