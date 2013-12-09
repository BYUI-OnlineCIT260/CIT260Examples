/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit210.tictactoe.views;

import byui.cit210.tictactoe.controls.GameMenuControl;
import byui.cit210.tictactoe.controls.TicTacToe;
import byui.cit210.tictactoe.enums.ErrorType;
import byui.cit210.tictactoe.enums.GameType;
import byui.cit210.tictactoe.enums.PlayerType;
import byui.cit210.tictactoe.enums.StatusType;
import byui.cit210.tictactoe.exceptions.GameException;
import byui.cit210.tictactoe.exceptions.TicTacToeException;
import byui.cit210.tictactoe.intefaces.EnterInfo;
import byui.cit210.tictactoe.models.Game;
import byui.cit210.tictactoe.models.Player;
import java.awt.Point;

/**
 *
 * @author jacksonrkj
 */
public class GameMenuView extends Menu  implements EnterInfo {
    
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



    @Override
    public Object getInput(Object object)  {
        this.game = (Game) object;
            
        StatusType gameStatus = StatusType.CONTINUE;
        do {
            try {
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
                
            } catch (GameException | TicTacToeException tex) {
                System.out.println("\n\t" + tex.getMessage());
                continue;
            }
            
        } while (gameStatus != StatusType.QUIT);


        
        return StatusType.PLAYING;
    }
    
    
    
   private void takeTurn() throws GameException, TicTacToeException {
        String playersMarker;
        Point selectedLocation;
        
        if (this.game.getStatus() != StatusType.NEW_GAME && 
            this.game.getStatus() != StatusType.PLAYING) {
            throw new TicTacToeException(ErrorType.ERROR206.getMessage());
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

        if (this.game.getGameType() == GameType.ONE_PLAYER) {
            // computers turn
            locationMarkerPlaced = this.gameCommands.playerTakesTurn(otherPlayer, null);

            if (this.gameOver()) { // game won or tied?
                return;
            }
        }


        // display board and prompt for next player's turn
        this.displayBoard.display(game.getBoard());
        String promptNextPlayer = getNextPlayerMessage(otherPlayer);
        System.out.println("\n\t" + promptNextPlayer);

    }

    private boolean gameOver() {
        boolean done = false;
        if (this.game.getStatus() == StatusType.TIE) { // a tie?
            System.out.println("\n\n\t" + this.game.getTiedMessage());
            done = true;
        } else if (this.game.getStatus() == StatusType.WINNER) { // a win?
            System.out.println("\n\n\t" + this.game.getWinningMessage());
            done = true;
        }
        
        if (done) {
            this.displayBoard.display(this.game.getBoard());
        }
        

        return done;
    }
    
        
    private String getNextPlayerMessage(Player player) {
        if (this.game.getGameType() == GameType.ONE_PLAYER) {
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
