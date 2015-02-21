package backEnd;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Tom on 14/12/2014.
 */
public class Game {

    private static List<Player> players;
    private int noOfPlayers;
    private boolean hasFinished;
    private Board board;

    public Game(int noOfPlayers) {

        if (noOfPlayers > 1 && noOfPlayers < 5) {
            this.hasFinished = false;
            this.noOfPlayers = noOfPlayers;
            players = new ArrayList<>();
            for (int i = 0; i < noOfPlayers; i++) {
                Player player = new Player();
                players.add(player);
            }
            //construction of the board
            board = new Board(this);
            //--do something with it
            InitialSetup.chooseNames();
            InitialSetup.randomiseStartingPositions(players);
            InitialSetup.setUpGame();

            Turn turn = new Turn(players);
            while (!hasFinished) {
                turn.haveTurns();
            }
        } else {
            System.out.println("Please enter a number between 2 and 4 for the number of players");
        }
    }

    public static void updateAll(List<Player> players) {
        for (Player player : players) {
            player.vPUpdater();
        }
    }

    public void victoryChecker() {
        for (int i=0; i<noOfPlayers; i++) {
            if (players.get(i).getVictoryPoints() > 9){
                hasFinished = true;
            }
        }
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(List<Player> players) {
        Game.players = players;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
