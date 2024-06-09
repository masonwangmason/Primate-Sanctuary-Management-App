package primates.Model;

import java.util.ArrayList;
import java.util.List;

// the enclosure class would extend the HousingAbstract class and initiate a new enclosure object
// each enclosure would be given a String ID, a int capacity, and it's associated Primate specie
// it also inherits and overwrites the add/remove primates methods from its abstract class
public class Enclosure extends HousingAbstract{
    private Primate.Species primateType;
    public Enclosure(String ID, int capacity, Primate.Species primateType){
        super(capacity,ID);
        this.primateType = primateType;
        this.primates = new ArrayList<>();
    }

    // overwrite the addPrimate method
    // add a new primate if: the current Primate is in the right type and the current size of the primates array is smaller than its capacity
    @Override
    public boolean addPrimate(Primate primate){
        if (this.primates.size() < capacity && primate.getSpecies() == primateType){
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

    // helper function to get the type of Primate for this enclosure
    public Primate.Species getPrimateType(){
        return this.primateType;
    }

    // helper function to get the capacity of this enclosure
    public int getCapacity(){
        return this.capacity;
    }
}
