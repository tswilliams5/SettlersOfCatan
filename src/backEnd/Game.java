package backEnd;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Tom on 14/12/2014.
 */
public class Game {

    public static List<Player> players;
    public int noOfPlayers;
    public boolean hasFinished;

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

    public Game(int noOfPlayers) {

        if (noOfPlayers<2 || noOfPlayers>4) {
            System.out.println("Please enter a number between 2 and 4 for the number of players");
        } else {
            this.hasFinished = false;
            this.noOfPlayers = noOfPlayers;
            players = new ArrayList<Player>();
            for (int i=0; i<noOfPlayers; i++) {
                Player player = new Player();
                players.add(player);
            }
            //construction of the board
            Board board = new Board();
            //--do something wth it
            InitialSetup.chooseNames();
            InitialSetup.randomiseStartingPositions(players);
            InitialSetup.setUpGame();

            Turn turn = new Turn(players);
            while (!hasFinished) {
                turn.haveTurns();
            }
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
}
