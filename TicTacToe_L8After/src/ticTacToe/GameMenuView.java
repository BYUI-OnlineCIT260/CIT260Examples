/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.awt.Point;
import java.util.Scanner;

/**
 *
 * @author jacksonrkj
 */
public class GameMenuView {
    
    private Game game;
    private GameMenuControl gameCommands ; 
    private GetLocationView getLocation = new GetLocationView();
    private BoardView displayBoard = new BoardView();

    private final static String[][] menuItems = {
        {"T", "Take your turn"},
        {"D", "Display the board"},
        {"N", "New Game"},
        {"R", "Report stastics"},
        {"P", "Change game preferences"},
        {"H", "Help"},
        {"Q", Game.QUIT}
    };

    public GameMenuView(Game game) {
        this.gameCommands = new GameMenuControl(game);
        
    }

    public BoardView getDisplayBoard() {
        return displayBoard;
    }

    public void setDisplayBoard(BoardView displayBoard) {
        this.displayBoard = displayBoard;
    }

    public final void display() {
        System.out.println("\n\t===============================================================");
        System.out.println("\tEnter the letter associated with one of the following commands:");

        for (int i = 0; i < GameMenuView.menuItems.length; i++) {
            System.out.println("\t   " + menuItems[i][0] + "\t" + menuItems[i][1]);
        }
        System.out.println("\t===============================================================\n");
    }

    private boolean validCommand(String command) {
        String[][] items = GameMenuView.menuItems;

        for (String[] item : GameMenuView.menuItems) {
            if (item[0].equals(command)) {
                return true;
            }
        }
        return false;
    }

    protected final String getCommand() {

        Scanner inFile = TicTacToe.getInputFile();
        String command;
        boolean valid = false;
        do {
            command = inFile.nextLine();
            command = command.trim().toUpperCase();
            valid = validCommand(command);
            if (!validCommand(command)) {
                new TicTacToeError().displayError("Invalid command. Please enter a valid command.");
                continue;
            }
                
        } while (!valid);
        
        return command;
    }


    
    public Object getInput(Object object) {
        this.game = (Game) object;

        this.game.setStatus(Game.CONTINUE);
        
        String gameStatus = this.game.getStatus();
        do {
     
            this.display();
            
            // get commaned entered
            String command = this.getCommand();
            switch (command) {
                case "T":
                    this.takeTurn();
                    break;
                case "D":
                    this.displayBoard.displayBoard(game.getBoard());
                    break;
                case "N":
                    gameCommands.startNewGame(game);
                    this.displayBoard.displayBoard(game.getBoard());
                    break;
                case "R":
                    this.displayStatistics();
                    break;
                case "P":
                    GamePreferencesMenuView gamePreferencesMenu = TicTacToe.getGamePreferencesMenu();
                    gamePreferencesMenu.getInput(this.game);
                    break;
                case "H":
                    HelpMenuView helpMenu = TicTacToe.getHelpMenu();
                    helpMenu.getInput(null);
                    break;
                case "Q":
                    gameStatus = Game.QUIT;
                    break;
            }
        } while (!gameStatus.equals(Game.QUIT));

        return Game.PLAYING;
    }
    
    
   private void takeTurn() {
        String playersMarker;
        Point selectedLocation;

        if (!this.game.getStatus().equals(Game.NEW_GAME) && 
            !this.game.getStatus().equals(Game.PLAYING)) {
            new TicTacToeError().displayError(
                    "There is no active game. You must start a new game before "
                    + "you can take a turn");
            return;
        }
        Player currentPlayer = this.game.getCurrentPlayer();
        Player otherPlayer = this.game.getOtherPlayer();

        // get location for first player
        selectedLocation = (Point) getLocation.getLocation(this.game);
        if (selectedLocation == null) {
            return;
        }

        // regular players turn
        Point locationMarkerPlaced = 
                this.gameCommands.playerTakesTurn(currentPlayer, selectedLocation);

        if (this.gameOver()) { // game won or tied?  
            return;
        }

        if (this.game.getGameType().equals(Game.ONE_PLAYER)) {
            // computers turn
            locationMarkerPlaced = this.gameCommands.playerTakesTurn(otherPlayer, null);

            if (this.gameOver()) { // game won or tied?
                return;
            }
        }


        // displayError board and prompt for next player's turn
        this.displayBoard.displayBoard(game.getBoard());
        String promptNextPlayer = getNextPlayerMessage(otherPlayer);
        System.out.println("\n\n\t" + promptNextPlayer);


    }

    private boolean gameOver() {
        boolean done = false;
        if (this.game.getStatus().equals(Game.TIE)) { // a tie?
            System.out.println("\n\n\t" + this.game.getTiedMessage());
            done = true;
        } else if (this.game.getStatus().equals(Game.WINNER)) { // a win?
            System.out.println("\n\n\t" + this.game.getWinningMessage());
            done = true;
        }
        
        if (done) {
            this.displayBoard.displayBoard(this.game.getBoard());
        }
        

        return done;
    }
    
        
    private String getNextPlayerMessage(Player player) {
        if (this.game.getGameType().equals(Game.ONE_PLAYER)) {
            return "The computer took it's turn. It is now your turn. "
                    + player.getName();
        } else {
            return "It is now your turn "
                    + player.getName();
        }
    }
    
    
    private void displayStatistics() {
        String playerAStatistics = this.game.getPlayerA().getPlayerStastics();
        String playerBStatistics = this.game.getPlayerB().getPlayerStastics();
        System.out.println("\n\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("\t " + playerAStatistics);
        System.out.println("\n\t " + playerBStatistics);
        System.out.println("\t+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }


   
}
