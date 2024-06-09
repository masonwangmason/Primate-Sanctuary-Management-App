package primates.View;

import primates.Model.Primate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Graphical user interface for managing the primate sanctuary.
 * This class handles all visual elements and interactions of this application
 */
public class SanctuaryView {
    private JFrame frame;
    private JTextArea isolationListTextArea, enclosureListTextArea, summaryListTextArea;
    private JTextField nameField, ageField, sizeField, weightField;
    private JComboBox<Primate.Species> speciesBox;
    private JComboBox<Primate.Sex> sexBox;
    private JComboBox<Primate.Food> foodBox;
    private JButton addButton, medicalCareButton, moveToENCButton;
    private JPanel mainPanel;


    // constructor
    /**
     * Class constructor: initializes the user interface components.
     */
    public SanctuaryView() {
        setupUI();
    }

    /**
     * Constructor helper function: sets up the user interface, including window, panels, and components
     */
    private void setupUI() {
        frame = new JFrame("Primate Sanctuary Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Set up the Complete JPanel layout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // add individual panels to the main panel
        mainPanel.add(createAddNewPrimatePanel());
        mainPanel.add(createIsolationListPanel());
        mainPanel.add(createEnclosurePanel());
        mainPanel.add(createSanctuaryMainPanel());

        // make all components scrollable
        JScrollPane scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    // panel creations
    /**
     * Creates and returns a panel for adding new primates to the sanctuary.
     * This panel contains input fields for the primate's name, species, sex, age, size, weight, and favorite food,
     * along with a button to submit the information.
     *
     * @return JPanel that allows user to input primate data.
     */
    private JPanel createAddNewPrimatePanel() {
        // create the individual panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Add New Primate"));

        // create the necessary TextFields, Text Box, and buttons
        nameField = new JTextField(10);
        ageField = new JTextField(5);
        sizeField = new JTextField(5);
        weightField = new JTextField(5);
        speciesBox = new JComboBox<>(Primate.Species.values());
        sexBox = new JComboBox<>(Primate.Sex.values());
        foodBox = new JComboBox<>(Primate.Food.values());
        addButton = new JButton("Add Primate");

        // add name label to all the input fields
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Species:"));
        panel.add(speciesBox);
        panel.add(new JLabel("Sex:"));
        panel.add(sexBox);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Weight:"));
        panel.add(weightField);
        panel.add(new JLabel("Size:"));
        panel.add(sizeField);
        panel.add(new JLabel("Food:"));
        panel.add(foodBox);
        panel.add(addButton);

        // return the initialized panel
        return panel;
    }

    /**
     * Creates and returns a panel for displaying primates currently in isolation.
     * The panel includes a text area for listing primates, and buttons for applying medical care
     * and moving a selected primate to an enclosure. The text area is not editable.
     *
     * @return JPanel that displays the list of isolated primates and interaction buttons.
     */
    private JPanel createIsolationListPanel() {
        // Initialize the individual panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Isolation Areas"));

        // create a text area to display isolation info
        isolationListTextArea = new JTextArea(10, 30);
        isolationListTextArea.setEditable(false);
        isolationListTextArea.setLineWrap(true);
        isolationListTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(isolationListTextArea);
        panel.add(scrollPane);

        // add the necessary buttons to the panel
        medicalCareButton = new JButton("Apply Medical Care to Selected Primate");
        panel.add(medicalCareButton);
        moveToENCButton = new JButton("Move the Selected Primate to Enclosures");
        panel.add(moveToENCButton);

        // return the initialized panel
        return panel;
    }

    /**
     * Creates and returns a panel for displaying primates currently in various enclosures.
     * The panel includes a text area for listing all primates by their enclosure, which is not editable
     * and formatted for easy reading.
     *
     * @return JPanel that displays primate details organized by enclosure.
     */
    private JPanel createEnclosurePanel() {
        // create the individual panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Enclosure Areas"));

        // create text area to display enclosure info
        enclosureListTextArea = new JTextArea(10, 30);
        enclosureListTextArea.setEditable(false);
        enclosureListTextArea.setLineWrap(true);
        enclosureListTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(enclosureListTextArea);
        panel.add(scrollPane);

        // return the result panel
        return panel;
    }

    /**
     * Creates and returns a panel that provides a comprehensive overview of all primates in the sanctuary.
     * This includes a text area that displays all primates, not editable and formatted for clarity.
     *
     * @return JPanel that offers a summary view of all sanctuary primates.
     */
    private JPanel createSanctuaryMainPanel() {
        // create the individual panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Sanctuary Overview"));

        // create text area to display enclosure info
        summaryListTextArea = new JTextArea(10, 30);
        summaryListTextArea.setEditable(false);
        summaryListTextArea.setLineWrap(true);
        summaryListTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(summaryListTextArea);
        panel.add(scrollPane);

        // return the result panel
        return panel;
    }


    // getters
    /**
     * Retrieves the text selected by the user in the isolation list text area.
     *
     * @return A string representing the selected text from the isolation list text area.
     */
    public String getSelectedTextFromIsolationList() {
        return isolationListTextArea.getSelectedText();
    }

    /**
     * Retrieves the name entered in the nameField text field.
     *
     * @return A string representing the name entered by the user.
     */
    public String getNameFieldValue() {
        return nameField.getText();
    }

    /**
     * Retrieves the species selected in the speciesBox combo box.
     *
     * @return The species of primate selected by the user.
     */
    public Primate.Species getSelectedSpecies() {
        return (Primate.Species) speciesBox.getSelectedItem();
    }

    /**
     * Retrieves the sex selected in the sexBox combo box.
     *
     * @return The sex of primate selected by the user.
     */
    public Primate.Sex getSelectedSex() {
        return (Primate.Sex) sexBox.getSelectedItem();
    }

    /**
     * Retrieves the age entered in the ageField text field.
     * Converts the input text to an integer.
     *
     * @return The age entered by the user as an integer.
     */
    public int getAgeFieldValue() {
        return Integer.parseInt(ageField.getText());
    }

    /**
     * Retrieves the size entered in the sizeField text field.
     * Converts the input text to an integer.
     *
     * @return The size entered by the user as an integer.
     */
    public int getSizeFieldValue() {
        return Integer.parseInt(sizeField.getText());
    }

    /**
     * Retrieves the weight entered in the weightField text field.
     * Converts the input text to an integer.
     *
     * @return The weight entered by the user as an integer.
     */
    public int getWeightFieldValue() {
        return Integer.parseInt(weightField.getText());
    }

    /**
     * Retrieves the food type selected in the foodBox combo box.
     *
     * @return The food preference of primate selected by the user.
     */
    public Primate.Food getSelectedFood() {
        return (Primate.Food) foodBox.getSelectedItem();
    }


    // add button listeners
    /**
     * Adds an ActionListener to the 'Add Primate' button.
     * This listener is triggered when the 'Add Primate' button is clicked.
     *
     * @param listener The ActionListener to attach to the 'Add Primate' button.
     */
    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the 'Apply Medical Care to Selected Primate' button.
     * This listener is triggered when the 'Apply Medical Care' button is clicked.
     *
     * @param listener The ActionListener to attach to the medical care button.
     */
    public void addMedicalCareButtonListener(ActionListener listener) {
        medicalCareButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the 'Move the Selected Primate to Enclosures' button.
     * This listener is triggered when the 'Move to Enclosure' button is clicked.
     *
     * @param listener The ActionListener to attach to the move to enclosure button.
     */
    public void addMoveToENCButtonListener(ActionListener listener){
        moveToENCButton.addActionListener(listener);
    }

    /**
     * Displays a list of primate details in the isolation list text area.
     * Each detail is separated by a newline.
     *
     * @param primateDetails A list of strings containing primate details.
     */
    // display information
    public void displayIsolatedPrimates(List<String> primateDetails) {
        String detailsText = String.join("\n", primateDetails);
        isolationListTextArea.setText(detailsText);
    }

    /**
     * Displays a list of primate details in the enclosure list text area.
     * Each detail is separated by a newline.
     *
     * @param primateDetails A list of strings containing primate details.
     */
    public void displayEnclosurePrimates(List<String> primateDetails){
        String detailsText = String.join("\n", primateDetails);
        enclosureListTextArea.setText(detailsText);
    }

    /**
     * Displays a list of all primate details in the sanctuary summary text area.
     * Each detail is separated by a newline.
     *
     * @param primateDetails A list of strings containing primate details for the entire sanctuary.
     */
    public void displaySummary(List<String> primateDetails){
        String detailsText = String.join("\n", primateDetails);
        summaryListTextArea.setText(detailsText);
    }

    // notifications
    /**
     * Displays a success notification with a custom message.
     *
     * @param message The message to display in the success notification.
     */
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays an error notification with a custom message.
     *
     * @param message The message to display in the error notification.
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}