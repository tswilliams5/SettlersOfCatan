package backEnd;

/**
 * Created by Tom on 14/12/2014.
 */
public class Hex {

    private int uniqueID;
    public String landscape; //hills, mountains, forest, ...
    public Integer[] position; // {row,column} a board can be thought of as a row of 3,4,5,4,3.
    public int number; // between 2 and 12
    public boolean hasRobber;

    public Hex() {}

    public Hex(String landscape, Integer[] position, int number) {
        this.landscape = landscape;
        this.position = position;
        this.number = number;
    }
}
