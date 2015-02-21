package backEnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tom on 14/12/2014.
 */
public class Board {

    private List<Hex> hexs;
    private List<Integer> numbers;
    private List<Integer[]> coordinates;
    private List<String> landscapes;
    private Game parent;
    private List<HexEdge> hexEdges;

    public Board(Game parent) {

        this.parent = parent;
        makeShuffledNumbers();
        makeOrderedCoordinates();
        makeShuffledLandscapes();
        makeHexs();
        makeHexEdges();
        //using coordinates, make UI for the board
    }

    private void makeShuffledNumbers() {

        numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(12);
        for (int i = 3; i<12; i++){
            numbers.add(i);
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    private void makeOrderedCoordinates() {
        coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Integer[] coordinateRow0 = new Integer[2];
            coordinateRow0[0] = 0;
            coordinateRow0[1] = i;
            coordinates.add(coordinateRow0);
            Integer[] coordinateRow4 = new Integer[2];
            coordinateRow4[0] = 4;
            coordinateRow4[1] = i;
            coordinates.add(coordinateRow4);
        }
        for (int i = 0; i < 4; i++) {
            Integer[] coordinateRow1 = new Integer[2];
            coordinateRow1[0] = 1;
            coordinateRow1[1] = i;
            coordinates.add(coordinateRow1);
            Integer[] coordinateRow3 = new Integer[2];
            coordinateRow3[0] = 3;
            coordinateRow3[1] = i;
            coordinates.add(coordinateRow3);
        }
        for (int i = 0; i < 5; i++) {
            Integer[] coordinateRow2 = new Integer[2];
            coordinateRow2[0] = 2;
            coordinateRow2[1] = i;
            coordinates.add(coordinateRow2);
        }
    }

    private void makeShuffledLandscapes() {
        landscapes = new ArrayList<>();
        addLandscapes(4, "Forests");
        addLandscapes(4, "Pastures");
        addLandscapes(4, "Fields");
        addLandscapes(3, "Hills");
        addLandscapes(3, "Mountains");
        landscapes.add("Desert");
        Collections.shuffle(landscapes);
    }

    private void addLandscapes(int j, String landscape) {
        for (int i = 0; i < j; i++) {
            landscapes.add(landscape);
        }
    }

    private void makeHexs() {
        for (int i = 0; i < 19; i++) {
            if (!landscapes.get(i).equals("Desert")) {
                Hex hex = new Hex(this, i, landscapes.get(i), coordinates.get(i), numbers.get(i), false);
                hexs.add(hex);
            } else {
                Hex hex = new Hex(this, i, "Desert", coordinates.get(i), 0, true);
                hexs.add(hex);
            }
        }
    }

    private void makeHexEdges() {
        List<HexEdge> coast = makeCoast();
        List<HexEdge> perpendicularCoast = makePerpendicularCoast();
        List<HexEdge> middlePerimeter = makeMiddlePerimeter();
        List<HexEdge> perpendicularPerimeter = makePerpendicularPerimeter();
        List<HexEdge> centrePerimeter = makeCentrePerimeter();

        setConnectedEdges(coast, perpendicularCoast, middlePerimeter, perpendicularPerimeter, centrePerimeter);

        hexEdges.addAll(coast);
        hexEdges.addAll(perpendicularCoast);
        hexEdges.addAll(middlePerimeter);
        hexEdges.addAll(perpendicularPerimeter);
        hexEdges.addAll(centrePerimeter);

    }

    private List<HexEdge> makePerpendicularCoast() {
        List<HexEdge> perpendicularCoast = new ArrayList<>();
        int[] spiralOfOuterHexs = {0,1,2,6,11,15,18,17,16,12,7,3};
        for (int i = 0; i < 12; i++) {
            HexEdge hexEdge = new HexEdge(hexs.get(spiralOfOuterHexs[i]),hexs.get(spiralOfOuterHexs[(i+1)%12]));
            perpendicularCoast.add(hexEdge);
        }
        return perpendicularCoast;
    }

    private void setConnectedEdges(List<HexEdge> coast, List<HexEdge> perpendicularCoast, List<HexEdge> middlePerimeter, List<HexEdge> perpendicularPerimeter, List<HexEdge> centrePerimeter) {
        for (int i = 0; i < coast.size(); i++) {
            List<HexEdge> connectedEdges = new ArrayList<>();
            connectedEdges.add(coast.get((i - 1) % coast.size()));
            connectedEdges.add(coast.get((i+1)%coast.size()));
            coast.get(i).setConnectedEdges(connectedEdges);
        }
        //contineu
    }

    private List<HexEdge> makeCoast() {
        List<HexEdge> theCoast = new ArrayList<>();
        int[] coastalCorners = {0,2,11,18,16,7};
        int[] coastalEdges = {1,6,15,17,12,3};
        for (int i = 0; i < 6; i++) {
            HexEdge edge1 = new HexEdge(hexs.get(coastalCorners[i]), 0);
            HexEdge edge2 = new HexEdge(hexs.get(coastalCorners[i]), 1);
            HexEdge edge3 = new HexEdge(hexs.get(coastalCorners[i]), 2);
            HexEdge edge4 = new HexEdge(hexs.get(coastalEdges[i]), 0);
            HexEdge edge5 = new HexEdge(hexs.get(coastalEdges[i]), 1);
            theCoast.add(edge1);
            theCoast.add(edge2);
            theCoast.add(edge3);
            theCoast.add(edge4);
            theCoast.add(edge5);
        }
        return theCoast;
    }

    public List<Hex> getHexs() {
        return hexs;
    }

    public void setHexs(List<Hex> hexs) {
        this.hexs = hexs;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Integer[]> coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getLandscapes() {
        return landscapes;
    }

    public void setLandscapes(List<String> landscapes) {
        this.landscapes = landscapes;
    }

    public Game getParent() {
        return parent;
    }

    public void setParent(Game parent) {
        this.parent = parent;
    }
}
