/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.views;

import byui.cit210.tictactoe.controls.MainMenuControl;
import byui.cit210.tictactoe.controls.TicTacToe;
import byui.cit210.tictactoe.enums.GameType;
import byui.cit210.tictactoe.enums.StatusType;
import byui.cit210.tictactoe.exceptions.TicTacToeException;
import byui.cit210.tictactoe.intefaces.EnterInfo;
import byui.cit210.tictactoe.models.Game;
import java.util.Scanner;

/**
 *
 * @author jacksonrkj
 */




public class MainMenuView extends Menu implements EnterInfo {
    
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
    public StatusType getInput(Object object) throws TicTacToeException {       
        
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
                    return StatusType.EXIT;
            }
        } while (!gameStatus.equals("EXIT"));

        return StatusType.EXIT;
    }

    private void startGame(long noPlayers) throws TicTacToeException {
        
        if (noPlayers != 1  &&  noPlayers != 2) {
            throw new TicTacToeException("startGame - invalid number of players specified.");
        }
        
        Game game;
        if (noPlayers == 1) {
            game = this.mainCommands.create(GameType.ONE_PLAYER);
        }
        else {
            game = this.mainCommands.create(GameType.TWO_PLAYER);
        }
        
        SelectPlayersView sekectPlayersView = new SelectPlayersView(game);
        StatusType status = sekectPlayersView.selectPlayers(TicTacToe.getNameList());
        if (status ==  StatusType.QUIT) {
            return;
        }
        
        // determine current and other player
        

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
