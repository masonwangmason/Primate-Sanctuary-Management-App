package primates;
import primates.Controller.SanctuaryController;
import primates.Model.Sanctuary;
import primates.View.SanctuaryView;

import javax.swing.SwingUtilities;

/**
 * The Main class for the Primate Sanctuary Management System.
 * This class initializes the application by setting up the model, view, and controller components.
 */
public class Main {
    /**
     * The main method to start the application. It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT),
     * which is the proper way to launch a Swing application to ensure thread safety.
     *
     * @param args Command line arguments passed to the program (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the model component
            Sanctuary model = new Sanctuary();
            // Create the view component
            SanctuaryView view = new SanctuaryView();
            // Create the controller component, connecting the model and view of the MVC architecture
            new SanctuaryController(view, model);
        });
    }
}