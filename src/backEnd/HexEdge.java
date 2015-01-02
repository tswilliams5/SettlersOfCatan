package backEnd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Tom on 14/12/2014.
 */
public class HexEdge {

    public Hex rightHex;
    public Hex leftHex;
    public Hex northHex;
    public Hex southHex;
    public boolean hasRoad = false;
    public Player owner = null;
    public HexIntersect northEnd;
    public HexIntersect southEnd;
    private List<HexEdge> adjacentEdges;

    public static HashSet<HexEdge> allHexEdges;

    public HexEdge(Hex rightHex, Hex leftHex) {
        this.rightHex = rightHex;
        this.leftHex = leftHex;
        if (!allHexEdges.contains(this)) {
            allHexEdges.add(this);
        } else {
            System.out.println("This hex edge already exists"); // to do: needs revising, make it so that when a board is created, all of the hex edges and intersections are created
        }
        this.setAdjacentEdges(calculateAdjacentEdges());
    }

    public List<HexEdge> calculateAdjacentEdges() {

        List<HexEdge> adjacentEdges = new ArrayList<>();
        for (Hex adjacentHex : this.getNorthEnd().getAdjacentHexs()) {
            if (this.getLeftHex() != adjacentHex && this.getRightHex() != adjacentHex) {
                Hex northHex = adjacentHex;
                HexEdge northWestEdge = new HexEdge(northHex, this.getLeftHex());
                HexEdge northEastEdge = new HexEdge(northHex, this.getRightHex());
                adjacentEdges.add(northWestEdge);
                adjacentEdges.add(northEastEdge);
            }
        }
        for (Hex adjacentHex : this.getSouthEnd().getAdjacentHexs()) {
            if (this.getLeftHex() != adjacentHex && this.getRightHex() != adjacentHex) {
                Hex northHex = adjacentHex;
                HexEdge southWestEdge = new HexEdge(northHex, this.getLeftHex());
                HexEdge southEastEdge = new HexEdge(northHex, this.getRightHex());
                adjacentEdges.add(southWestEdge);
                adjacentEdges.add(southEastEdge);
            }
        }
        return adjacentEdges;
    }

    public static HexEdge findOne(Hex rightHex, Hex leftHex) {
        HexEdge theHexEdge = null;
        for (HexEdge hexEdge : allHexEdges) {
            if (hexEdge.getRightHex() == rightHex && hexEdge.getLeftHex() == leftHex) {
                theHexEdge = hexEdge;
            }
        }
        if (theHexEdge == null) {
            //import logger and say so
        }
        return theHexEdge;
    }

    public Hex getSouthHex() {
        return southHex;
    }

    public void setSouthHex(Hex southHex) {
        this.southHex = southHex;
    }

    public Hex getRightHex() {
        return rightHex;
    }

    public void setRightHex(Hex rightHex) {
        this.rightHex = rightHex;
    }

    public Hex getLeftHex() {
        return leftHex;
    }

    public void setLeftHex(Hex leftHex) {
        this.leftHex = leftHex;
    }

    public boolean isHasRoad() {
        return hasRoad;
    }

    public void setHasRoad(boolean hasRoad) {
        this.hasRoad = hasRoad;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Hex getNorthHex() {
        return northHex;
    }

    public void setNorthHex(Hex northHex) {
        this.northHex = northHex;
    }

    public HexIntersect getNorthEnd() {
        return northEnd;
    }

    public void setNorthEnd(HexIntersect northEnd) {
        this.northEnd = northEnd;
    }

    public HexIntersect getSouthEnd() {
        return southEnd;
    }

    public void setSouthEnd(HexIntersect southEnd) {
        this.southEnd = southEnd;
    }

    public List<HexEdge> getAdjacentEdges() {
        return adjacentEdges;
    }

    public void setAdjacentEdges(List<HexEdge> adjacentEdges) {
        this.adjacentEdges = adjacentEdges;
    }

    public static HashSet<HexEdge> getAllHexEdges() {
        return allHexEdges;
    }
}
