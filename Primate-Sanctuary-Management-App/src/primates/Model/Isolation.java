package primates.Model;

import java.util.ArrayList;
import java.util.List;

// the isolation class would extend the HousingAbstract class and initiate a new isolation object
// each isolation would be given a String ID and a default capacity of 1
// it also inherits/overwrites the add/remove primates methods from its abstract class
public class Isolation extends HousingAbstract {
    // Isolation Cage Constructor
    // set each cage's capacity as 1
    public Isolation(String ID){
        super(1, ID);
        this.primates = new ArrayList<>(1);
    }

    // overwrite the addPrimate method
    // add a new primate if the current size of the primates array is smaller than its capacity
    @Override
    public boolean addPrimate(Primate primate){
        if (this.primates.size() < capacity){
            this.primates.add(primate);
            return true;
        }
        return false;
    }

    // overwrite the removePrimate method
    // remove a primate from the primates array if it is achievable
    @Override
    public boolean removePrimate(Primate primate){
        return this.primates.remove(primate);
    }

    // overwrite the getPrimates method
    // return a copy of the primate array list
    @Override
    public List<Primate> getPrimates(){
        return new ArrayList<>(this.primates);
    }

    // helper function to get an isolation's capacity
    public int getCapacity(){
        return capacity;
    }
}
