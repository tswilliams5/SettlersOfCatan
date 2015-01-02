package backEnd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 14/12/2014.
 */
public class Turn {

    private Player player;
    private List<Player> players;

    public Turn(Player player) {
        this.player = player;
    }

    public Turn(List<Player> players) {
        this.players = players;
    }

    public void haveTurn() {
        player.setIsTurn(true);
        if (!player.isInInitialSetUp()) {
            //normal turn
        } else {
            player.placeSettlement();
            //initial set up turn
        }
        Game.updateAll(players);
        player.vPUpdater();
        player.setIsTurn(false);
    }

    public void haveTurns() {
        for (int i = 0; i < players.size(); i++) {
            players = new ArrayList<Player>();
            //start with player 1,
            //make then have a turn
            players.get(i).haveTurn();
            if (i == players.size()) {
                i = 0;
            }
        }
    }
}
