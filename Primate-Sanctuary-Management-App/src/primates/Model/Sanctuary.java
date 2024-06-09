package primates.Model;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

// the Sanctuary class would stimulate how the sanctuary operates
// it will help create the necessary isolation/enclosure spaces
// it will also help us to generally add/remove a primate from different types of housing options and provide medical treatments
// eventually, it will help us to get a list of all the Primates in the current Sanctuary and a list of details regrading different enclosure spaces
public class Sanctuary {
    // set up an empty array for isolations and a hashmap for sanctuary
    private Isolation[] isolations;
    private Map<String, Enclosure> enclosures;


    // Sanctuary constructor
    public Sanctuary() {
        // Initialize the isolations array before using it
        isolations = new Isolation[20];

        // Set up 20 isolation cages
        for (int i = 0; i < isolations.length; i++) {
            isolations[i] = new Isolation(Integer.toString(i));
        }

        // Initialize the enclosures map before using it
        enclosures = new HashMap<>();

        // Set up 8 enclosure spaces for each primate species
        String[] species = {"DRILL", "GUEREZA", "HOWLER", "MANGABEY", "SAKI", "SPIDER", "SQUIRREL", "TAMARIN"};
        for (String specie : species) {
            Primate.Species primateSpecies = Primate.Species.valueOf(specie);
            enclosures.put(specie, new Enclosure(specie, Integer.MAX_VALUE, primateSpecies));
        }
    }

    // method to add a new primate to the sanctuary by first putting it in isolation
    public Primate addPrimateToSanctuary(String name, Primate.Species species, Primate.Sex sex, int size, int weight, int age, Primate.Food food) {
        // Validate the primate's name
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Primate name cannot be empty.");
        }

        // Validate the primate's species
        if (species == null) {
            throw new IllegalArgumentException("Primate species is not valid.");
        }

        // Validate the primate's sex
        if (sex == null) {
            throw new IllegalArgumentException("Primate sex is not valid.");
        }

        // Validate the primate's size, weight, and age
        if (size <= 0 || weight <= 0 || age <= 0) {
            throw new IllegalArgumentException("Primate size, weight, and age must be greater than zero.");
        }

        // Validate the primate's food preference
        if (food == null) {
            throw new IllegalArgumentException("Primate food preference is not valid.");
        }

        Primate newPrimate = new Primate(name, species, sex, size, weight, age, food);
        addPrimateToISO(newPrimate);
        return newPrimate;
    }

    // provide medical care for a primate
    public void medicalCare(Primate primate){
        primate.medicate();
    }

    // add primates to isolation
    public void addPrimateToISO(Primate primate){
        // iterate the Isolations array to locate an empty isolation
        boolean added = false;
        for (Isolation isolation : isolations) {
            if (isolation.getPrimates().size() < isolation.getCapacity()) {
                isolation.addPrimate(primate);
                added = true;
                break;
            }
        }

        // if the add fail, raise an IllegalStateException
        if (!added) {
            throw new IllegalStateException("No available isolation space.");
        }

        // Update the primate's isolation status if it was not already isolated
        if (!primate.isolatedBefore()) {
            primate.setIsolated();
        }
    }


    // remove primate from isolation
    public void removePrimateFromISO(Primate primate){
        // Check whether the target primate is in the isolations array
        for (Isolation isolation : isolations) {
            // Check if the primate is in the current isolation unit
            if (isolation.getPrimates().contains(primate)) {
                if (primate.medicatedBefore()){
                    isolation.removePrimate(primate);
                    return;
                } else {
                    throw new IllegalArgumentException("This primate has not been medicated yet.");
                }
            }
        }

        // If the loop completes without finding and removing the primate, throw an exception
        throw new IllegalArgumentException("The target primate is not found in any isolation units.");
    }


    // add primates to enclosures
    public void addPrimateToENC(Primate primate){
        // if the primate has not been isolated/medicated, throw an IllegalArgumentException
        if (!primate.isolatedBefore()) throw new IllegalArgumentException("This primate has not been isolated yet.");
        if (!primate.medicatedBefore()) throw new IllegalArgumentException("This primate has not been medicated yet.");

        // get the correct enclosure based on the primate's species
        String speciesString = primate.getSpecies().toString();
        Enclosure targetEnclosure = enclosures.get(speciesString);
        // if the target enclosure does not exist, throw an IllegalArgumentException
        if (targetEnclosure == null) {
            throw new IllegalArgumentException("No enclosure found for species: " + speciesString);
        }
        // if the target enclosure is full, c
        if (targetEnclosure.getPrimates().size() == targetEnclosure.getCapacity()) {
            throw new IllegalArgumentException("The enclosure for this primate is currently full.");
        }

        // otherwise, add the primate to its enclosure
        targetEnclosure.addPrimate(primate);
    }

    // remove primate from enclosures
    public void removePrimateFromENC(Primate targetPrimate) {
        for (Enclosure enclosure : enclosures.values()) {
            if (enclosure.getPrimates().contains(targetPrimate)) {
                // Remove the primate from the enclosure
                enclosure.removePrimate(targetPrimate);
                return; // Exit the method after removing the primate
            }
        }
        // If the target primate can't be located, throw an exception
        throw new IllegalArgumentException("The target primate has not been found in the enclosures.");
    }

    // get the enclosure list
    public List<String> getEnclosureList() {
        List<String> enclosureList = new ArrayList<>();

        // Iterate through each enclosure
        for (Enclosure enclosure : enclosures.values()) {
                StringBuilder enclosureString = new StringBuilder();
                enclosureString.append("Enclosure for ").append(enclosure.getPrimateType()).append(":\n");

                // Iterate through each primate in the enclosure
                for (Primate primate : enclosure.getPrimates()) {
                    String primateInfo = String.format(
                            "Name: %s, Sex: %s, Favorite Food: %s",
                            primate.getName(),
                            primate.getSex(),
                            primate.getFood()
                    );
                    enclosureString.append(primateInfo).append("\n");
                }

                enclosureList.add(enclosureString.toString());
        }

        return enclosureList;
    }

    // get the all primate list
    public List<String> getAllNames() {
        // Set up an empty list to store information about all the primates
        List<String> allPrimateDetails = new ArrayList<>();

        // Store the isolated primates
        for (Isolation isolation : isolations) {
            allPrimateDetails.addAll(getPrimateDetails(isolation.getPrimates()));
        }

        // Store the primates in enclosures
        for (Enclosure enc : enclosures.values()) {
            allPrimateDetails.addAll(getPrimateDetails(enc.getPrimates()));
        }

        // Sort the details list alphabetically by primate name
        Collections.sort(allPrimateDetails);

        // Insert the header line at the beginning of the list
        allPrimateDetails.add(0, "All Primates Currently in the Sanctuary (in alphabetical order):\n");

        return allPrimateDetails;
    }

    // helper method to get detailed information of a list of primates
    private List<String> getPrimateDetails(List<Primate> primates) {
        List<String> details = new ArrayList<>();
        for (Primate pri : primates) {
            String detail = String.format("Name: %s, Age: %d, Sex: %s, Food: %s",
                    pri.getName(), pri.getAge(), pri.getSex(), pri.getFood());
            details.add(detail);
        }
        return details;
    }

    // Helper method to determine whether a primate is in isolation
    public boolean isPrimateInIsolation(Primate primate) {
        for (Isolation isolation : isolations) {
            if (isolation.getPrimates().contains(primate)) {
                return true;
            }
        }
        return false;
    }

    // helper method to get the enclosure for a specific species
    public Enclosure getEnclosureForSpecies(Primate.Species species) {
        return enclosures.get(species.toString());
    }

    // helper method to get all the isolated primate details
    public List<Primate> getIsolatedPrimates() {
        List<Primate> isolatedPrimates = new ArrayList<>();
        for (Isolation isolation : isolations) {
            isolatedPrimates.addAll(isolation.getPrimates());
        }
        return isolatedPrimates;
    }

    // helper method to find a primate based on its name
    public Primate findPrimateByName(String name) {
        for (Isolation isolation : isolations) {
            for (Primate primate : isolation.getPrimates()) {
                if (primate.getName().equals(name)) {
                    return primate;
                }
            }
        }
        return null;
    }
}
