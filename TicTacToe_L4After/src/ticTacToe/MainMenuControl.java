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
            game = this.create("ONE_PLAYER");
        }
        else {
            game = this.create("TWO_PLAYER");
        }

        GameMenuView gameMenu = new GameMenuView(game);
        gameMenu.getInput();
    }

    
    
    public Game create(String gameType) {
        Game game = null;
        Player player1 = null;
        Player player2 = null;
        
        if (gameType == null) {
            new TicTacToeError().displayError("MainCommands - create: gameType is null");
            return null;
        }
        
        if (gameType.equals(Game.ONE_PLAYER)) {
            game = new Game(Game.ONE_PLAYER);
            player1 = new Player(Player.REGULAR_PLAYER, game.PLAYER_A_DEFAULT_MARKER);
            player1.setName("Player 1");
            player2 = new Player(Player.COMPUTER_PLAYER, game.PLAYER_B_DEFAULT_MARKER);
            player2.setName("Computer");
        }
        else if (gameType.equals(Game.TWO_PLAYER)) {
            game = new Game(Game.TWO_PLAYER);
            player1 = new Player(Player.REGULAR_PLAYER, game.PLAYER_A_DEFAULT_MARKER);
            player1.setName("Player 1");
            player2 = new Player(Player.REGULAR_PLAYER, game.PLAYER_B_DEFAULT_MARKER);
            player2.setName("Player 2");

        }
      
        game.playerA = player1;
        game.playerB = player2;
        
        return game;
    } 
    
    public void displayHelpMenu() {
        HelpMenuView helpMenu = new HelpMenuView();
        helpMenu.getInput();
    }
    
}
