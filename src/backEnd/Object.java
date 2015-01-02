package backEnd;

/**
 * Created by Tom on 26/12/2014.
 */
public class Object {

    private int[] cost;
    private String name;
    private Player owner;
    private Object home;

    public int[] getCost() {
        return cost;
    }

    public void setCost(int[] cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Object getHome() {
        return home;
    }

    public void setHome(Object home) {
        this.home = home;
    }
}
