package backEnd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 14/12/2014.
 */
public class HexEdge {

    private Hex onlyHex = null;
    private int coastalNumber;

    private Hex rightHex = null;
    private Hex leftHex = null;
    private Hex northHex;
    private Hex southHex;
    private boolean hasRoad = false;
    private Player owner = null;
    private HexIntersect northEnd;
    private HexIntersect southEnd;
    private List<HexEdge> connectedEdges;

    /**
     * this constructor is for hex's on the coast
     * @param onlyHex land hex which the edge lies on
     * @param coastalNumber how many edges of the onlyHex along clockwise the hes edge is
     */
    public HexEdge(Hex onlyHex, int coastalNumber) {
        this.onlyHex = onlyHex;
        this.coastalNumber = coastalNumber;
    }

    public HexEdge(Hex rightHex, Hex leftHex) {
        this.rightHex = rightHex;
        this.leftHex = leftHex;
        this.setConnectedEdges(calculateAdjacentEdges());
    }

    public List<HexEdge> calculateAdjacentEdges() {

        //needs reviewing
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
        for (HexEdge hexEdge : alledges) {
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

    public List<HexEdge> getConnectedEdges() {
        return connectedEdges;
    }

    public void setConnectedEdges(List<HexEdge> connectedEdges) {
        this.connectedEdges = connectedEdges;
    }

    public Hex getOnlyHex() {
        return onlyHex;
    }

    public void setOnlyHex(Hex onlyHex) {
        this.onlyHex = onlyHex;
    }

    public int getCoastalNumber() {
        return coastalNumber;
    }

    public void setCoastalNumber(int coastalNumber) {
        this.coastalNumber = coastalNumber;
    }
}
