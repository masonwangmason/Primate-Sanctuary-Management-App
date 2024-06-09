package primates.Controller;
import primates.Model.Primate;
import primates.Model.Sanctuary;
import primates.View.SanctuaryView;

import javax.xml.crypto.dsig.spec.RSAPSSParameterSpec;
import java.util.List;
import java.util.stream.Collectors;

public class SanctuaryController {
    private SanctuaryView view;
    private Sanctuary model;

    /**
     * Initializes a controller for managing the sanctuary.
     * Connects the SanctuaryView and the Sanctuary model, setting up action listeners and initializing the view.
     *
     * @param view  The SanctuaryView instance used for user interaction.
     * @param model The Sanctuary model instance used for primate data management.
     */
    public SanctuaryController(SanctuaryView view, Sanctuary model) {
        this.view = view;
        this.model = model;
        initController();
    }

    /**
     * Constructor helper function: sets up action listeners and initializes the view by refreshing the lists of primates.
     */
    private void initController() {
        // connect actions to their associated button listeners
        view.addAddButtonListener(e -> addPrimate());
        view.addMedicalCareButtonListener(e -> applyMedicalCare());
        view.addMoveToENCButtonListener(e -> movePrimateToENC());

        // refresh all the primate lists
        refreshIsolationList();
        refreshEnclosureList();
        refreshSummaryList();
    }

    /**
     * Handles adding a new primate based on user input from the view.
     * Validates input and updates the model and view accordingly.
     */
    private void addPrimate() {
        try {
            // store user inputs from view
            String name = view.getNameFieldValue();
            Primate.Species species = view.getSelectedSpecies();
            Primate.Sex sex = view.getSelectedSex();
            int age = view.getAgeFieldValue();
            int size = view.getSizeFieldValue();
            int weight = view.getWeightFieldValue();
            Primate.Food food = view.getSelectedFood();

            // initialize a new primate object based on the inputs
            Primate newPrimate = model.addPrimateToSanctuary(name, species, sex, size, weight, age, food);

            // show user a success message
            view.showSuccess("Primate added successfully!");

            // refresh the updated isolation list
            refreshIsolationList();

        } catch (IllegalArgumentException e) {
            // if an IllegalArgumentException was raised, show user an error message
            view.showError(e.getMessage());
        }
        // refresh the isolation list and summary list
        refreshIsolationList();
        refreshSummaryList();
    }

    // Button Actions
    /**
     * Applies medical care to a selected primate.
     * Validates the selection and updates the primate's medical status.
     */
    private void applyMedicalCare() {
        // get the target primate's name
        String selectedText = view.getSelectedTextFromIsolationList();

        // if the user has forgotten any input, show an error
        if (selectedText == null || selectedText.isEmpty()) {
            view.showError("No primate selected.");
            return;
        }

        // get the target primate by its name
        // show user the associated message
        Primate primate = model.findPrimateByName(selectedText);
        if (primate != null) {
            model.medicalCare(primate);
            view.showSuccess("Medical care applied to " + primate.getName());
            refreshIsolationList();  // refresh list to reflect the updated medicated status
        } else {
            view.showError("Invalid selection or primate not found.");
        }
    }

    /**
     * Moves a medicated primate from isolation to an enclosure.
     * Validates the primate's medication status before moving.
     */
    private void movePrimateToENC() {
        // get the target primate's name
        String selectedText = view.getSelectedTextFromIsolationList();

        // if the user does not select anything, show an error
        if (selectedText == null || selectedText.isEmpty()) {
            view.showError("No primate selected.");
            return;
        }

        // find the target primate by its name
        Primate primate = model.findPrimateByName(selectedText);

        // if a primate is found and was medicated, move it to is enclosure
        // otherwise, show an error message
        if (primate != null && primate.medicatedBefore()) {
            model.removePrimateFromISO(primate);
            model.addPrimateToENC(primate);
            view.showSuccess(primate.getName() + " has been successfully moved to its enclosure.");
            refreshIsolationList();
        } else {
            view.showError("Invalid selection, primate not found, or not medicated.");
        }
        // refresh the enclosure list to reflect the update
        refreshEnclosureList();
    }



    // Refresh Methods
    /**
     * Refreshes the isolation list view to reflect current data in the model.
     */
    public void refreshIsolationList() {
        List<Primate> isolatedPrimates = model.getIsolatedPrimates();
        // get all the isolation primate details
        List<String> primateDetails = isolatedPrimates.stream()
                .map(Primate::getDetails)
                .collect(Collectors.toList());
        view.displayIsolatedPrimates(primateDetails);
    }

    /**
     * Refreshes the enclosure list view to reflect current data in the model.
     */
    public void refreshEnclosureList() {
        view.displayEnclosurePrimates(model.getEnclosureList());
    }

    /**
     * Refreshes the summary view to reflect all primates currently in the sanctuary.
     */
    public void refreshSummaryList() {
        view.displaySummary(model.getAllNames());
    }
}