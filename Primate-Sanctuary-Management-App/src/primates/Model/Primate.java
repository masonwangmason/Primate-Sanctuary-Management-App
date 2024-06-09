package primates.Model;

// the Primate class would create new Primate objects
// it will help us assign the necessary info for each primate and return them
// it would also help us check whether a primate has been isolated/medicated before
public class Primate {
    // declare all the necessary enums
    public enum Species {DRILL, GUEREZA, HOWLER, MANGABEY, SAKI, SPIDER, SQUIRREL, TAMARIN}
    public enum Sex {MALE, FEMALE}
    public enum Food {EGGS, FRUITS, INSECTS, LEAVES, NUTS, SEEDS, TREE_SAP}

    // declare all the variables needed
    String name;
    Species species;
    Sex sex;
    int size;
    int weight;
    int age;
    Food food;
    boolean isolated;
    boolean medicated;

    // constructor
    public Primate(String name, Species species, Sex sex, int size, int weight, int age, Food food){
        this.name = name;
        this.species = species;
        this.sex = sex;
        this.size = size;
        this.weight = weight;
        this.age = age;
        this.food = food;
        this.isolated = false;
        this.medicated = false;
    }

    // "get" methods to return most info regarding a primate
    public String getName(){return name;}

    public Species getSpecies(){return species;}

    public Sex getSex(){return sex;}

    public int getSize(){return size;}

    public int getWeight(){return weight;}

    public int getAge(){return age;}

    public Food getFood(){return food;}

    // helper function to check the primate's isolation record
    public boolean isolatedBefore(){return isolated;}

    // helper function to check the primate's medical care record
    public boolean medicatedBefore(){return medicated;}



    // helper function to update a primate's isolation record
    public void setIsolated() {
        this.isolated = true;
    }

    // helper function to update a primate's medical record
    public void medicate(){
        this.medicated = true;
    }

    // helper function to return the Primate's detail
    public String getDetails() {
        return String.format("%s - %d - %s - %s - %s - %s",
                name, age, species, sex, food, medicated ? "Medicated" : "Not Medicated");
    }
}
