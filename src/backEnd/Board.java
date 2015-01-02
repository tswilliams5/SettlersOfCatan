package backEnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tom on 14/12/2014.
 */
public class Board {

    public List<Hex> hexs;
    public List<Integer> numbers;
    public List<Integer[]> coordinates;

    public Board() {

        this.numbers = new ArrayList();
        this.hexs = new ArrayList();
        this.coordinates = new ArrayList();

        numbers = makeShuffledNumbers();
        coordinates = makeShuffledCoordinates();
        insertRandomHexs(hexs);
    }

    public ArrayList makeShuffledNumbers() {

        ArrayList numbers = new ArrayList();
        numbers.add(2);
        numbers.add(12);
        for (int i = 3; i<12; i++){
            numbers.add(i);
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    public ArrayList makeShuffledCoordinates() {
        ArrayList coordinates = new ArrayList<int[]>();
        for (int i = 0; i<3; i++) {
            int[] integer1 = new int[2];
            integer1[0] = 0;
            integer1[1] = i;
            coordinates.add(integer1);
            int[] integer2 = new int[2];
            integer2[0] = 4;
            integer2[1] = i;
            coordinates.add(integer2);
        }
        for (int i = 0; i<4; i++) {
            int[] integer1 = new int[2];
            integer1[0] = 1;
            integer1[1] = i;
            coordinates.add(integer1);
            int[] integer2 = new int[2];
            integer2[0] = 3;
            integer2[1] = i;
            coordinates.add(integer2);
        }
        for (int i = 0; i<5; i++) {
            int[] integer1 = new int[2];
            integer1[0] = 2;
            integer1[1] = i;
            coordinates.add(integer1);
        }
        Collections.shuffle(coordinates);
        return coordinates;
    }

    public void insertRandomHexs(List hexs) {
        // mountains, pastures, hills, fields, forests and desert are the different possible
        for (int i = 0; i < 4; i++) {
            Hex hex = new Hex("forests", coordinates.get(i), numbers.get(i));
            hexs.add(hex);
        }
        for (int i = 4; i < 8; i++) {
            Hex hex = new Hex("pastures", coordinates.get(i), numbers.get(i));
            hexs.add(hex);
        }
        for (int i = 8; i < 12; i++) {
            Hex hex = new Hex("fields", coordinates.get(i), numbers.get(i));
            hexs.add(hex);
        }
        for (int i = 12; i < 15; i++) {
            Hex hex = new Hex("hills", coordinates.get(i), numbers.get(i));
            hexs.add(hex);
        }
        for (int i = 15; i < 18; i++) {
            Hex hex = new Hex("mountains", coordinates.get(i), numbers.get(i));
            hexs.add(hex);
        }
        Hex hex = new Hex("desert", coordinates.get(18), 18);
        hexs.add(hex);
        Collections.shuffle(hexs);
    }
}
