package backEnd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 14/12/2014.
 */
public class Hex {

    private int uniqueID;
    private String landscape; //hills, mountains, forest, ...
    private Integer[] coordinates; // {row,column} a board can be thought of as a row of 3,4,5,4,3.
    private int number; // between 2 and 12 and 0 for desert
    private boolean hasRobber;
    private Board parent;
    private List<Hex> adjacentHexs;

    private Hex() {}

    public Hex(Board parent, int uniqueID, String landscape, Integer[] coordinates, int number, boolean hasRobber) {
        this.parent = parent;
        this.uniqueID = uniqueID;
        this.landscape = landscape;
        this.coordinates = coordinates;
        this.number = number;
        this.hasRobber = hasRobber;
    }
    //not used
    private void calculateAdjacentHexs() {
        //Get Bill to optimise this
        adjacentHexs = new ArrayList<>();
        for (Hex sibling : parent.getHexs()) {
            if (Math.abs(sibling.getCoordinates()[0] - this.getCoordinates()[0] ) == 1
                    || Math.abs(sibling.getCoordinates()[1] - this.getCoordinates()[1] ) == 1) {
                adjacentHexs.add(sibling);
            }
            if (adjacentHexs.size() == 6) {
                return;
            }
        }
    }

    public Hex getHex(int uniqueID) {
        for (Hex siblings : parent.getHexs()) {
            if (siblings.getUniqueID() == uniqueID) {
                return siblings;
            }
        }
        System.out.println("No such Hex exists");
        return null;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    public Integer[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Integer[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isHasRobber() {
        return hasRobber;
    }

    public void setHasRobber(boolean hasRobber) {
        this.hasRobber = hasRobber;
    }

    public Board getParent() {
        return parent;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }

    public List<Hex> getAdjacentHexs() {
        return adjacentHexs;
    }

    public void setAdjacentHexs(List<Hex> adjacentHexs) {
        this.adjacentHexs = adjacentHexs;
    }
}
