# Phonebook Project (BST Implementation)

## Description

This project is a Java-based phonebook application that manages contacts and schedules events or appointments using Binary Search Trees (BSTs). It provides a command-line interface for users to interact with the phonebook.

The core functionalities include:
* Adding new contacts, ensuring uniqueness based on both name and phone number.
* Searching for contacts by various criteria (name, phone number, email, address, birthday).
* Deleting contacts, including handling their removal from associated events.
* Scheduling events (with multiple contacts) or appointments (with a single contact).
* Checking for exact time conflicts when scheduling new events/appointments.
* Printing details of specific events.
* Listing contacts based on first name.
* Listing all scheduled events/appointments alphabetically by title.

This implementation utilizes BSTs to store contacts (keyed by name) and events (keyed by title), offering efficient searching, insertion, and deletion based on these keys.

## Features

* **Contact Management:**
    * Add new contacts with details (name, phone, email, address, birthday, notes).
    * Prevents adding contacts with duplicate names (case-insensitive) or duplicate phone numbers.
    * Search for contacts efficiently by full name (O(log N) average).
    * Search for contacts by phone number, email, address, or birthday (O(N)).
    * Delete contacts by name, automatically removing them from associated events.
    * List contacts sharing the same first name (O(N)).
* **Event/Appointment Scheduling:**
    * Schedule events (multiple contacts) or appointments (single contact) with title, date/time, and location.
    * Performs conflict checking based on exact date/time string match before scheduling (O(M), where M is the number of events).
    * Associates contacts with the events/appointments they are part of.
* **Information Retrieval:**
    * Search for and print event/appointment details by title (O(log M)) or by associated contact name (O(M log K)).
    * Print a list of all scheduled events/appointments alphabetically by title (O(M)).
* **Data Structure:** Uses custom Binary Search Tree (`BST.java`) implementation for storing contacts and events.

## Requirements

* Java Development Kit (JDK) 8 or higher (for compiling and running).
* No external libraries are required beyond the standard Java SE platform.

## Input

The program operates interactively through the command-line console. It prompts the user for input based on the menu options selected. It does not read initial data from files.

## Usage

1.  **Save Files:** Ensure all the required Java source files are in the same directory:
    * `Main.java`
    * `PhoneBook.java`
    * `BST.java`
    * `BSTNode.java`
    * `Contact.java`
    * `Event.java`
    *(Note: `Order.java` and `Relative.java` are not used by the current code and can be removed.)*

2.  **Compile:** Open a terminal or command prompt, navigate to the directory containing the files, and compile all Java source files:
    ```
    javac *.java
    ```
    *(If using PowerShell, you might need to use `javac .\*.java` or ensure your execution policy allows running scripts if you chain commands with `;`)*

3.  **Run:** Execute the main class:
    ```
    java Main
    ```
    *(If you encounter `ClassNotFoundException`, try explicitly setting the classpath to the current directory: `java -cp . Main`)*

4.  **Interact:** Follow the menu prompts displayed in the console to use the phonebook features.

## Code Structure

* **`Main.java`:** Provides the command-line user interface, handles user input, and calls methods in the `PhoneBook` class.
* **`PhoneBook.java`:** Acts as the main controller, managing the BSTs for contacts and events. Contains the core logic for adding, searching, deleting, scheduling, and printing.
* **`BST.java`:** Implements a generic Binary Search Tree, including methods for adding, searching, removing nodes, size tracking, and in-order traversal.
* **`BSTNode.java`:** Represents a node within the BST, holding a key (String), data (generic type `T`), and references to left/right children.
* **`Contact.java`:** Represents a contact with attributes like name, phone number, email, etc.
* **`Event.java`:** Represents an event or appointment, storing its details and a BST of associated contacts.

## Notes & Limitations

* **Search Efficiency:** Searching contacts/events by their key (name/title) is efficient (O(log N) average). However, searching by other attributes (phone, email, first name, associated contact in event) requires traversing the entire corresponding data structure (O(N) or O(M)), which can be slow for large datasets.
* **Duplicate Phone Check:** Checking for duplicate phone numbers during contact addition is an O(N) operation.
* **Time Conflict Check:** The check for event time conflicts (`isTimeSlotAvailable`) is O(M) and only detects conflicts based on an *exact match* of the date/time string. It does not handle overlapping time ranges (e.g., 10:00-11:00 vs 10:30). Implementing true overlap detection would require parsing date/time strings and using a different checking mechanism.
* **Contact Deletion:** Deleting a contact also involves iterating through all events (O(M)) to remove the contact from their associated lists. If an event becomes empty after removing the contact, it is currently *not* automatically deleted (this could be added as an enhancement).
* **Event-Contact Link:** The link is primarily one-way. Events store the contacts involved. Contacts do *not* store a list of events they are part of, simplifying consistency management, especially during deletion.

