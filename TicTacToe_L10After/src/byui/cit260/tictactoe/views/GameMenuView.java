/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.tictactoe.views;

import byui.cit260.tictactoe.enums.GameType;
import byui.cit260.tictactoe.enums.StatusType;
import byui.cit260.tictactoe.models.Player;
import byui.cit260.tictactoe.models.Game;
import byui.cit260.tictactoe.interfaces.EnterInfo;
import java.awt.Point;
import byui.cit260.tictactoe.controls.GameMenuControl;
import byui.cit260.tictactoe.controls.TicTacToe;

/**
 *
 * @author jacksonrkj
 */
public class GameMenuView extends Menu  implements EnterInfo {
    
    private Game game;
    private GameMenuControl gameCommands; 
    private GetLocationView getLocation = new GetLocationView();
    private BoardView displayBoard;

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
        this.game = game;
        this.gameCommands = new GameMenuControl(game);
         this.displayBoard = new BoardView(this.game.getBoard());
    }

    public BoardView getDisplayBoard() {
        return displayBoard;
    }

    public void setDisplayBoard(BoardView displayBoard) {
        this.displayBoard = displayBoard;
    }

    


    @Override
    public Object getInput(Object object) {
        this.game = (Game) object;

        StatusType gameStatus = StatusType.CONTINUE;
        do {
     
            this.display();
            
            // get commaned entered
            String command = this.getCommand();
            switch (command) {
                case "T":
                    this.takeTurn();
                    break;
                case "D":
                    this.displayBoard.display();
                    break;
                case "N":
                    gameCommands.startNewGame(game);
                    this.displayBoard.display();
                    break;
                case "R":
                    this.displayStatistics();
                    break;
                case "P":
                    GamePreferencesMenuView gamePreferencesMenu = TicTacToe.getGamePreferencesMenu();
                    gamePreferencesMenu.display();
                    gamePreferencesMenu.getInput(this.game);
                    break;
                case "H":
                    HelpMenuView helpMenu = TicTacToe.getHelpMenu();
                    helpMenu.getInput(null);
                    break;
                case "Q":
                    gameStatus = StatusType.QUIT;
                    break;
            }
        } while (!gameStatus.equals(StatusType.QUIT));

        return StatusType.PLAYING;
    }
    
    
   private void takeTurn() {
        String playersMarker;
        Point selectedLocation;

        if (!this.game.getStatus().equals(StatusType.NEW_GAME) && 
            !this.game.getStatus().equals(StatusType.PLAYING)) {
            this.displayBoard = new BoardView(this.game.getBoard());
            return;
        }
        Player currentPlayer = this.game.getCurrentPlayer();
        Player otherPlayer = this.game.getOtherPlayer();

        // get location for first player
        selectedLocation = (Point) getLocation.getInput(this.game);
        if (selectedLocation == null) {
            return;
        }

        // regular players turn
        Point locationMarkerPlaced = 
                this.gameCommands.playerTakesTurn(currentPlayer, selectedLocation);

        if (this.gameOver()) { // game won or tied?  
            return;
        }

        if (this.game.getGameType().equals(GameType.ONE_PLAYER)) {
            // computers turn
            locationMarkerPlaced = this.gameCommands.playerTakesTurn(otherPlayer, null);

            if (this.gameOver()) { // game won or tied?
                return;
            }
        }


        // display board and prompt for next player's turn
        this.displayBoard.display();
        String promptNextPlayer = getNextPlayerMessage(otherPlayer);
        System.out.println("\n\n\t" + promptNextPlayer);


    }

    private boolean gameOver() {
        boolean done = false;
        if (this.game.getStatus().equals(StatusType.TIE)) { // a tie?
            System.out.println("\n\n\t" + this.game.getTiedMessage());
            done = true;
        } else if (this.game.getStatus().equals(StatusType.WINNER)) { // a win?
            System.out.println("\n\n\t" + this.game.getWinningMessage());
            done = true;
        }
        
        if (done) {
            this.displayBoard.display();
        }
        

        return done;
    }
    
        
    private String getNextPlayerMessage(Player player) {
        if (this.game.getGameType().equals(GameType.ONE_PLAYER)) {
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
