# Primate Sanctuary Management System

## Overview
This Primate Sanctuary Management System is a Java-based application designed to help manage the care and housing of primates in a sanctuary setting. It provides tools for adding new primates to the sanctuary, tracking their medical care status, and managing their transition from isolation to enclosure areas.

## Features
- **Add New Primates:** Allows users to input primate details and add new primates to the sanctuary.
- **Medical Care Application:** Users can select primates in isolation and apply medical care.
- **Move Primates to Enclosures:** Once primates receive medical care, they can be moved to species-specific enclosures.
- **View Isolation and Enclosure Lists:** Displays lists of all primates currently in isolation and enclosures.
- **Sanctuary Summary:** Provides an overview of all primates in the sanctuary.

## Running the Program
To run the program, you will need Java installed on your system. Follow these steps:
1. Download the `PrimateSanctuary.jar` file from the provided link.
2. Right click on the file and use JavaLauncher to run it.

## Interacting with the Program
Upon launching the application, you will see a graphical user interface with several sections:
- **Add New Primate:** Fill out the form fields and click 'Add Primate' to register a new primate into the system.
- **Isolation Areas:** Select a primate by highlighting its name, then use the 'Apply Medical Care' or 'Move to Enclosures' buttons as needed.
- **Enclosure Areas:** View primates that have been moved to their respective enclosures.
- **Sanctuary Overview:** Displays a summary of all primates in the sanctuary.

## Original Design and Changes
The initial design only included basic functionality for adding primates and viewing lists. The final implementation expanded to include:
- Medical care tracking.
- Specific enclosures for different species.
- Enhanced UI for better interaction.

## Assumptions
- All primates must undergo medical examination before being moved to an enclosure.
- The sanctuary has unlimited capacity for each type of primate species in its enclosures.

## Limitations
- The system does not support deletion of primate records.
- The system does not handle situations where multiple primates share the same name.

## Citations
This project uses Java Swing for its graphical user interface. Documentation and tutorials from Oracle's official Java documentation were used as a reference.
https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html
