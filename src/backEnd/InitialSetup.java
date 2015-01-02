package backEnd;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tom on 14/12/2014.
 */
public class InitialSetup {

    public static Game currentGame;
    public static int noOfPlayers;

    public InitialSetup() { }

    public static void chooseNames() {
        //once the UI is set up, establish a way of asking the user to write the name of the players, then
        currentGame.getPlayers().get(0).setName("Bill");
    }

    public static void setUpGame() {
        setInitialSetUps(true);
        for (int j = 0; j<2; j++) {
            for (int i = 0; i<noOfPlayers; i++) {
                currentGame.players.get(i).haveTurn();
            }
        }
        setInitialSetUps(false);
    }

    public static List<Player> randomiseStartingPositions(List<Player> players) { // it will be passed the players from the current game
        Collections.shuffle(players);
        for (int i = 0; i<noOfPlayers; i++) {
            players.get(i).setStartingPosition(i);
        }
        return players;
    }

    public static void setInitialSetUps(boolean isInitial) {
        for (int i = 0; i<currentGame.noOfPlayers; i++) {
            currentGame.players.get(i).setInInitialSetUp(isInitial);
        }
    }
}
