package backEnd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 26/12/2014.
 */
public class Road extends Object {

    private HexEdge home;

    public Road() {
        int[] cost = {1,0,0,1,0};
        this.setCost(cost);
        this.setName("Road");
    }

    public Road(Player player) {
        int[] cost = {1,0,0,1,0};
        this.setCost(cost);
        this.setName("Road");
        this.setOwner(player);
    }
}
