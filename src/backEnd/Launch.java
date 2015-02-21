package backEnd;

/**
 * Created by Tom on 14/12/2014.
 */
public class Launch {

    /*
    to do list:
    robber
    rolling/ resource awarding/ have turn method
    remove arrays?#
    ports

    5/1
    finish making all of the hexs, edges and intersects, then find out what relationships are actually needed before dealing with rest
     */

    public static Game currentGame;

    /**
     * Launch a new game here, input the number of players
     * @param args
     */
    public static void main(String[] args) {
        currentGame = new Game(2);
    }

}
