package backEnd;

import java.util.List;

/**
 * Created by Tom on 14/12/2014.
 */
public class HexIntersect {

    private List<Hex> adjacentHexs;
    private boolean hasSettlement = false;
    private boolean hasCity = false;
    private Player owner;
    private List<HexIntersect> nearestHexIntersects;

    public HexIntersect() {
        nearestHexIntersects =
    }

    public List<Hex> getAdjacentHexs() {
        return adjacentHexs;
    }

    public void setAdjacentHexs(List<Hex> adjacentHexs) {
        this.adjacentHexs = adjacentHexs;
    }

    public boolean isHasSettlement() {
        return hasSettlement;
    }

    public void setHasSettlement(boolean hasSettlement) {
        this.hasSettlement = hasSettlement;
    }

    public boolean isHasCity() {
        return hasCity;
    }

    public void setHasCity(boolean hasCity) {
        this.hasCity = hasCity;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
