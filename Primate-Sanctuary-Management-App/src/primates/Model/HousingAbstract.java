package primates.Model;

import java.util.List;

// the HousingAbstract class would function as the blueprints both types of housing options in the Sanctuary
// it will define that for every housing option in the Sanctuary, it must have a String ID, an int capacity, and a list that contains all its primate objects
// it will also define that each of the housing option should have a method to add primates, a method to remove primates, and a method to get the primates list
public abstract class HousingAbstract {
    int capacity;
    String ID;
    List<Primate> primates;

    // abstract class constructor
    public HousingAbstract(int capacity, String ID){
        this.capacity = capacity;
        this.ID = ID;
    }

    // abstract methods
    // add primate to the current housing object
    public abstract boolean addPrimate(Primate primate);
    // remove primate from the current housing object
    public abstract boolean removePrimate(Primate primate);
    // get the list which contains all the primates in the current housing option
    public abstract List<Primate> getPrimates();
}
