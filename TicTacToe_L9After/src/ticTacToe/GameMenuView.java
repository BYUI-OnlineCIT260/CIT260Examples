/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.awt.Point;

/**
 *
 * @author jacksonrkj
 */
public class GameMenuView extends Menu {
    
    private Game game;
    private GameMenuControl gameCommands; 
    private GetLocationView getLocation = new GetLocationView();
    private BoardView displayBoard = new BoardView();

    private final static String[][] menuItems = {
        {"T", "Take your turn"},
        {"D", "Display the board"},
        {"N", "New Game"},
        {"R", "Report stastics"},
        {"P", "Change game preferences"},
        {"H", "Help"},
        {"Q", "Quit"}
    };

    public GameMenuView(Game game) {
        super(GameMenuView.menuItems);
        this.gameCommands = new GameMenuControl(game);
    }

    public BoardView getDisplayBoard() {
        return displayBoard;
    }

    public void setDisplayBoard(BoardView displayBoard) {
        this.displayBoard = displayBoard;
    }

    


    public Object getMenuItem(Object object) {
        this.game = (Game) object;

        String gameStatus = Game.CONTINUE;
        do {
     
            this.display();
            
            // get commaned entered
            String command = this.getCommand();
            switch (command) {
                case "T":
                    this.takeTurn();
                    break;
                case "D":
                    this.displayBoard.display(game.getBoard());
                    break;
                case "N":
                    gameCommands.startNewGame(game);
                    this.displayBoard.display(game.getBoard());
                    break;
                case "R":
                    this.displayStatistics();
                    break;
                case "P":
                    GamePreferencesMenuView gamePreferencesMenu = TicTacToe.getGamePreferencesMenu();
                    gamePreferencesMenu.display();
                    gamePreferencesMenu.getMenuItem(this.game);
                    break;
                case "H":
                    HelpMenuView helpMenu = TicTacToe.getHelpMenu();
                    helpMenu.getHelpMenuItem(null);
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

        if (this.game.getGameType() == Game.ONE_PLAYER) {
            // computers turn
            locationMarkerPlaced = this.gameCommands.playerTakesTurn(otherPlayer, null);

            if (this.gameOver()) { // game won or tied?
                return;
            }
        }


        // display board and prompt for next player's turn
        this.displayBoard.display(game.getBoard());
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
            this.displayBoard.display(this.game.getBoard());
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
