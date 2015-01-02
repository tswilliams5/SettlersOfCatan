package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 14/12/2014.
 */
public class Player {

    public Player() {}

    public String name;
    public int[] resources = new int[5]; //ordering: [wood,pasture,corn,brick,ore]
    public int[] unrevealedDevelopmentCards = new int[5]; //ordering: [knight card,victory point card,road building card,monopoly,year of plenty]
    public int[] revealedDevelopmentCards = new int[5]; //ordering: [knight card,victory point card,road building card,monopoly,year of plenty]
    public int settlementQuantity; //5 max
    public int cityQuantity; //4 max
    public int roadQuantity; //15 max
    public int longestRoad;
    public int largestArmy;
    public int victoryPoints;
    public int startingPosition;
    public boolean inInitialSetUp;
    public boolean isTurn;

    public void vPUpdater() {

        victoryPoints = settlementQuantity + 2*cityQuantity + revealedDevelopmentCards[1] + unrevealedDevelopmentCards[1];
        int theLongestRoad = 0;
        int theLargestArmy = 0;
        int LRTally = 0;
        int LATally = 0;
        for(int i=0; i<Launch.currentGame.players.size(); i++){
            if(Launch.currentGame.players.get(i).longestRoad > theLongestRoad){
                theLongestRoad = Launch.currentGame.players.get(i).longestRoad;
                LRTally = 1;
            }else if(Launch.currentGame.players.get(i).longestRoad == theLongestRoad){
                LRTally++;
            }
            if(Launch.currentGame.players.get(i).largestArmy > theLargestArmy){
                theLargestArmy = Launch.currentGame.players.get(i).largestArmy;
                LATally = 1;
            }else if(Launch.currentGame.players.get(i).largestArmy == theLargestArmy){
                LATally++;
            }
        }
        if(longestRoad == theLongestRoad && LRTally == 1 && longestRoad > 4){
            victoryPoints += 2;
        }
        if(largestArmy == theLargestArmy && LATally == 1 && largestArmy > 2){
            victoryPoints += 2;
        }
    }
    public static void tradeUpdater(Player a,Player b,int[] c,int[] d) {
        //c is the resource(s) that player a is giving, d is the resource(s) what player b is giving
        //look into an inbuilt method of adding and subtracting arrays with one swift move?
        for(int i=0; i<5; i++) {
            a.resources[i] = a.resources[i] - c[i] + d[i];
            b.resources[i] = b.resources[i] - d[i] + c[i];
        }
    }
    public void haveTurn() {
        Turn turn = new Turn(this);
        turn.haveTurn();
    }


    public boolean validateRoadPlacement(HexEdge hexEdge) {

        Map<Boolean, String> conditionsToErrors = new HashMap<>();
        boolean hasLocalTerritory = false;
        boolean hasRoads = false;
        boolean canAfford = true;

        if (((hexEdge.getNorthEnd().isHasSettlement() || hexEdge.getNorthEnd().isHasCity())
                && (hexEdge.getNorthEnd().getOwner() == this))
            || ((hexEdge.getSouthEnd().isHasSettlement() || hexEdge.getSouthEnd().isHasCity())
                && (hexEdge.getSouthEnd().getOwner() == this))) {
            hasLocalTerritory = true;
        }
        if (!hasLocalTerritory) {
            List<HexEdge> adjacentEdges = hexEdge.getAdjacentEdges();
            for (HexEdge viableRoad : adjacentEdges) {
                if (viableRoad.getOwner() == this) {
                    hasLocalTerritory = true;
                }
            }
        }

        if (this.getRoadQuantity() < 15) {
            hasRoads = true;
        }

        if (!this.inInitialSetUp) {
            int[] cost = {1, 0, 0, 1, 0};
            canAfford = this.checkFunds(cost);

        } else {
            canAfford = true;
        }
        conditionsToErrors.put(hasLocalTerritory, this.getName() + " does not have surrounding territory");
        conditionsToErrors.put(hasRoads, this.getName() + " has ran out of roads");
        conditionsToErrors.put(canAfford, this.getName() + " does not have enough resources");

        return checkBooleans(conditionsToErrors);
    }

    private boolean checkFunds(int[] cost) {
        for (int i = 0; i < cost.length; i++) {
            if (this.getResources()[i] < cost[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBooleans(Map<Boolean, String> conditionsToErrors) {
        boolean allTrue = true;
        for (Boolean b : conditionsToErrors.keySet()) {
            if (!b) {
                allTrue = false;
                System.out.println(conditionsToErrors.get(b));
            }
        }
        return allTrue;
    }


    public void placeRoad(HexEdge hexEdge) {

        if (validateRoadPlacement(hexEdge)) {
            this.subtractResources(new int[]{1, 0, 0, 1, 0});
            hexEdge.setHasRoad(true);
            hexEdge.setOwner(this);
            this.setRoadQuantity(this.getRoadQuantity() - 1);
        }
        else {
            System.out.println(this.getName() + " is not allowed to build a road here.");
        }
    }

    private boolean validateSettlement(HexIntersect hexIntersect) {

        boolean correctSpacing = false;
        boolean hasLocalTerritory = false;
        if (!this.inInitialSetUp) {
            // need to verify that at least one of the surrounding edges has a road belonging to them
            outerLoop:
            for (int i = 0; i < 3; i++) {
                for (int j = i + 1; j < 3; i++) {
                    HexEdge departingEdge = HexEdge.findOne(hexIntersect.getAdjacentHexs().get(i), hexIntersect.getAdjacentHexs().get(j));
                    if (departingEdge.hasRoad && departingEdge.getOwner() == this) {
                        hasLocalTerritory = true;
                        break outerLoop;
                    }
                }
            }
        }
        else {
            hasLocalTerritory = true;
        }
        //location bit


    }

    public void placeSettlement(HexIntersect hexIntersect) {
        if (validateSettlement(hexIntersect)) {

        }
    }

    public void validateCity(Hex hexIntersect) {}

    public void placeCity() {}

    public static int[] subtract(int[] resources, int[] subtraction) {
        int[] newResources = new int[5];
        for (int i = 0; i < 5; i++) {
            newResources[i] = resources[i] - subtraction[i];
        }
        return newResources;
    }

    public void subtractResources(int[] subtraction) {
        this.setResources(subtract(this.getResources(), subtraction));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getResources() {
        return resources;
    }

    public void setResources(int[] resources) {
        this.resources = resources;
    }

    public int[] getUnrevealedDevelopmentCards() {
        return unrevealedDevelopmentCards;
    }

    public void setUnrevealedDevelopmentCards(int[] unrevealedDevelopmentCards) {
        this.unrevealedDevelopmentCards = unrevealedDevelopmentCards;
    }

    public int[] getRevealedDevelopmentCards() {
        return revealedDevelopmentCards;
    }

    public void setRevealedDevelopmentCards(int[] revealedDevelopmentCards) {
        this.revealedDevelopmentCards = revealedDevelopmentCards;
    }

    public int getSettlementQuantity() {
        return settlementQuantity;
    }

    public void setSettlementQuantity(int settlementQuantity) {
        this.settlementQuantity = settlementQuantity;
    }

    public int getCityQuantity() {
        return cityQuantity;
    }

    public void setCityQuantity(int cityQuantity) {
        this.cityQuantity = cityQuantity;
    }

    public int getRoadQuantity() {
        return roadQuantity;
    }

    public void setRoadQuantity(int roadQuantity) {
        this.roadQuantity = roadQuantity;
    }

    public int getLongestRoad() {
        return longestRoad;
    }

    public void setLongestRoad(int longestRoad) {
        this.longestRoad = longestRoad;
    }

    public int getLargestArmy() {
        return largestArmy;
    }

    public void setLargestArmy(int largestArmy) {
        this.largestArmy = largestArmy;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(int startingPosition) {
        this.startingPosition = startingPosition;
    }

    public boolean isInInitialSetUp() {
        return inInitialSetUp;
    }

    public void setInInitialSetUp(boolean inInitialSetUp) {
        this.inInitialSetUp = inInitialSetUp;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }
}
