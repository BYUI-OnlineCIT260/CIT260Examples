/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.tictactoe.views;

import byui.cit260.tictactoe.models.Game;
import byui.cit260.tictactoe.interfaces.EnterInfo;
import java.util.Scanner;
import byui.cit260.tictactoe.controls.MainMenuControl;
import byui.cit260.tictactoe.controls.TicTacToe;
import byui.cit260.tictactoe.controls.TicTacToeError;

/**
 *
 * @author jacksonrkj
 */




public class MainMenuView extends Menu  implements EnterInfo {
    
    private static final String[][] menuItems = {
        {"1", "One player game"},
        {"2", "Two player game"},
        {"H", "Help"},
        {"X", "Exit Tic-Tac-Toe"}
    }; 
  
    MainMenuControl mainCommands = new MainMenuControl();
    
    public MainMenuView() {
        super(MainMenuView.menuItems);
    }
    
    @Override
    public String getInput(Object object) {       
        
        String gameStatus = "PLAYING";
        do {
            this.display();

            // get commaned entered
            String command = this.getCommand();
            switch (command) {
                case "1":
                    this.startGame(1);
                    break;
                case "2":
                    this.startGame(2);
                    break;
                case "H":
                    HelpMenuView helpMenu = TicTacToe.getHelpMenu();
                    helpMenu.getInput(null);
                    break;
                case "X":
                    return Game.EXIT;
            }
        } while (!gameStatus.equals("EXIT"));

        return Game.EXIT;
    }

    private void startGame(long noPlayers) {
                
        if (noPlayers != 1  &&  noPlayers != 2) {
            new TicTacToeError().display("startGame - invalid number of players specified.");
            return;
        }
        
        Game game;
        if (noPlayers == 1) {
            game = this.mainCommands.create(Game.ONE_PLAYER);
        }
        else {
            game = this.mainCommands.create(Game.TWO_PLAYER);
        }
        
        SelectPlayersView sekectPlayersView = new SelectPlayersView(game);
        String status = (String) sekectPlayersView.selectPlayers(TicTacToe.getNameList());
        if (status.equals(Game.QUIT)) {
            return;
        }

        GameMenuView gameMenu = new GameMenuView(game);
        gameMenu.getInput(game);
    }

    private String quitGame() {
        System.out.println("\n\tAre you sure you want to quit? (Y or N)");
        Scanner inFile = new Scanner(System.in);
        String answer = inFile.next().trim().toUpperCase();
        if (answer.equals("Y")) {
            return "EXIT";
        }

        return "PLAYING";
    }




    
}
