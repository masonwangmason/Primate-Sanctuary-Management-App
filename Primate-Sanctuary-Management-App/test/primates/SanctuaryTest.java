package primates;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import primates.Model.Enclosure;
import primates.Model.Primate;
import primates.Model.Sanctuary;

import java.util.List;

import static org.junit.Assert.*;

public class SanctuaryTest {

    private Sanctuary sanctuary;

    // initializes the thrown rule with no expected exception
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // set up a sample sanctuary for the upcoming tests
    @Before
    public void setUp() {
        sanctuary = new Sanctuary();
    }

    // check whether a primate object can be created successfully
    @Test
    public void testCreateMonkeySuccessfully() {
        Primate primate = sanctuary.addPrimateToSanctuary("Leo", Primate.Species.DRILL, Primate.Sex.MALE, 80, 25, 5, Primate.Food.FRUITS);
        assertNotNull("Primate should be created successfully", primate);
    }

    // check whether an error would be thrown if a user tries to create a Primate object illegally
    @Test
    public void testExceptionThrownIfCreatingInvalidMonkey() {
        thrown.expect(IllegalArgumentException.class);
        sanctuary.addPrimateToSanctuary("", null, null, -1, -1, -1, null);
    }

    // check whether the program would automatically put new primates in isolations
    @Test
    public void testMonkeyCanBeAddedToIsolationSuccessfully() {
        Primate primate = sanctuary.addPrimateToSanctuary("Ella", Primate.Species.SAKI, Primate.Sex.FEMALE, 60, 20, 7, Primate.Food.LEAVES);
        assertTrue("Primate should be added to isolation successfully", primate.isolatedBefore());
    }

    // check whether an error would be thrown if the user attempts to transfer a primate when the isolations are full
    @Test
    public void testMonkeyCannotBeAddedToIsolationIfFull() {
        thrown.expect(IllegalStateException.class);
        // Fill up the isolation units
        for (int i = 0; i < 20; i++) {
            sanctuary.addPrimateToSanctuary("Monkey" + i, Primate.Species.HOWLER, Primate.Sex.MALE, 65, 22, 6, Primate.Food.FRUITS);
        }
        // Attempt to add one more primate should throw IllegalStateException
        sanctuary.addPrimateToSanctuary("ExtraMonkey", Primate.Species.HOWLER, Primate.Sex.MALE, 65, 22, 6, Primate.Food.FRUITS);
    }

    // check whether an error would be raised if a new primate had not been treated
    @Test
    public void testMonkeyCannotBeAddedToEnclosureIfNotHealthy() {
        thrown.expect(IllegalArgumentException.class);
        Primate sickPrimate = sanctuary.addPrimateToSanctuary("SickMonkey", Primate.Species.MANGABEY, Primate.Sex.MALE, 75, 24, 8, Primate.Food.SEEDS);
        sanctuary.addPrimateToENC(sickPrimate);
    }

    // check whether a primate can be transferred to enclosures after medical care
    @Test
    public void testMonkeyCanBeMovedFromIsolationToEnclosure() {
        Primate primate = sanctuary.addPrimateToSanctuary("HealthyMonkey", Primate.Species.SQUIRREL, Primate.Sex.FEMALE, 50, 18, 5, Primate.Food.NUTS);
        sanctuary.medicalCare(primate);

        // First, assert that the primate is in isolation
        assertTrue("Primate should be in isolation initially", sanctuary.isPrimateInIsolation(primate));

        sanctuary.removePrimateFromISO(primate);
        sanctuary.addPrimateToENC(primate);

        // Then, check that the primate is no longer in isolation
        assertFalse("Primate should not be in isolation after being moved", sanctuary.isPrimateInIsolation(primate));

        // Check that the primate is now in the enclosure
        Enclosure enclosure = sanctuary.getEnclosureForSpecies(Primate.Species.SQUIRREL);
        assertTrue("Primate should be in the enclosure after being moved", enclosure.getPrimates().contains(primate));
    }

    // check whether the enclosure list works
    @Test
    public void testEnclosureListIsCorrect() {
        Primate primate = sanctuary.addPrimateToSanctuary("EnclosureTestMonkey", Primate.Species.TAMARIN, Primate.Sex.MALE, 80, 25, 5, Primate.Food.FRUITS);
        sanctuary.medicalCare(primate);
        sanctuary.removePrimateFromISO(primate);
        sanctuary.addPrimateToENC(primate);
        List<String> enclosureList = sanctuary.getEnclosureList();
        assertTrue("Enclosure list should contain EnclosureTestMonkey", enclosureList.toString().contains("EnclosureTestMonkey"));
    }

    // check whether the all primate list works
    @Test
    public void testAllPrimateOrderedByName() {
        sanctuary.addPrimateToSanctuary("Amy", Primate.Species.DRILL, Primate.Sex.FEMALE, 60, 22, 6, Primate.Food.LEAVES);
        sanctuary.addPrimateToSanctuary("Zoe", Primate.Species.SQUIRREL, Primate.Sex.FEMALE, 50, 18, 5, Primate.Food.NUTS);
        sanctuary.addPrimateToSanctuary("Ben", Primate.Species.MANGABEY, Primate.Sex.MALE, 75, 24, 8, Primate.Food.SEEDS);

        List<String> primateDetails = sanctuary.getAllNames();
        assertEquals("All Primates Currently in the Sanctuary:", primateDetails.get(0));

        // Check for sorted order of names and presence of required details
        assertTrue("The list should include detailed info for Amy", primateDetails.get(1).contains("Amy"));
        assertTrue("The list should include detailed info for Ben", primateDetails.get(2).contains("Ben"));
        assertTrue("The list should include detailed info for Zoe", primateDetails.get(3).contains("Zoe"));
    }
}