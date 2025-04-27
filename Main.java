import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args)
    {
        PhoneBook phoneBook = new PhoneBook();

        try (Scanner input = new Scanner(System.in)) {

            System.out.println("Welcome to the BST Phonebook!");

            int choice = 0;
            do {

                System.out.println("\nPlease choose an option:");
                System.out.println("1. Add a contact");
                System.out.println("2. Search for a contact");
                System.out.println("3. Delete a contact");
                System.out.println("4. Schedule an event/appointment");
                System.out.println("5. Print event details");
                System.out.println("6. Print contacts by first name");
                System.out.println("7. Print all events alphabetically");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");

                try {
                    choice = input.nextInt();
                    input.nextLine();

                    switch (choice) {

                        case 1: {
                            System.out.print("Enter the contact's name: ");
                            String contactName = input.nextLine();
                            System.out.print("Enter the contact's phone number: ");
                            String phoneNumber = input.nextLine();
                            System.out.print("Enter the contact's email address: ");
                            String emailAddress = input.nextLine();
                            System.out.print("Enter the contact's address: ");
                            String address = input.nextLine();
                            System.out.print("Enter the contact's birthday (MM/DD/YYYY): ");
                            String birthday = input.nextLine();
                            System.out.print("Enter any notes for the contact: ");
                            String notes = input.nextLine();


                            if (contactName.trim().isEmpty() || phoneNumber.trim().isEmpty()) {
                                System.out.println("Error: Contact name and phone number cannot be empty.");
                            } else {
                                Contact contact = new Contact(contactName, phoneNumber, emailAddress, address, birthday, notes);
                                phoneBook.addContact(contact);
                            }
                            break;
                        }

                        case 2: {
                            System.out.println("\nEnter search criteria:");
                            System.out.println("1. Name");
                            System.out.println("2. Phone Number");
                            System.out.println("3. Email Address");
                            System.out.println("4. Address");
                            System.out.println("5. Birthday");
                            System.out.print("Enter your choice: ");
                            int searchChoice = input.nextInt();
                            input.nextLine();

                            String searchValue = "";
                            boolean validInput = true;
                            switch (searchChoice) {
                                case 1: System.out.print("Enter the contact's name: "); searchValue = input.nextLine(); break;
                                case 2: System.out.print("Enter the phone number: "); searchValue = input.nextLine(); break;
                                case 3: System.out.print("Enter the email address: "); searchValue = input.nextLine(); break;
                                case 4: System.out.print("Enter the address: "); searchValue = input.nextLine(); break;
                                case 5: System.out.print("Enter the birthday: "); searchValue = input.nextLine(); break;
                                default: System.out.println("Invalid search criteria."); validInput = false; break;
                            }

                            if (validInput && !searchValue.trim().isEmpty()) {
                                if (searchChoice == 1) { 
                                    Contact found = phoneBook.searchContactBasedOnName(searchValue);
                                    if (found != null) {
                                        System.out.println("\nContact found:");
                                        System.out.println(found);
                                    } else {
                                        System.out.println("Contact not found.");
                                    }
                                } else { 
                                    BST<Contact> results = phoneBook.searchContactInfo(searchValue, searchChoice);
                                    if (results.isEmpty()) {
                                        System.out.println("No contacts found matching the criteria.");
                                    } else {
                                        System.out.println("\nMatching contacts found:");
                                        results.printInOrder(); 
                                    }
                                }
                            } else if (validInput) {
                                System.out.println("Search value cannot be empty.");
                            }
                            break;
                        }

                        case 3: {
                            System.out.print("Enter the name of the contact to delete: ");
                            String nameToDelete = input.nextLine();
                            if (nameToDelete.trim().isEmpty()) {
                                 System.out.println("Contact name cannot be empty.");
                            } else {
                                phoneBook.deleteContact(nameToDelete); 
                            }
                            break;
                        }

                        case 4: {
                            System.out.println("\nEnter type:");
                            System.out.println("1. Event");
                            System.out.println("2. Appointment");
                            System.out.print("Enter your choice: ");
                            int eventTypeChoice = input.nextInt();
                            input.nextLine(); 

                            boolean isEvent = (eventTypeChoice == 1);
                            String typeStr = isEvent ? "event" : "appointment";

                            System.out.print("Enter " + typeStr + " title: ");
                            String title = input.nextLine();

                            BST<Contact> contactsInEventBST = new BST<>();
                            if (isEvent) {
                                System.out.print("Enter contacts' names separated by commas: ");
                                String contactNamesInput = input.nextLine();
                                String[] names = contactNamesInput.split(",");
                                boolean allContactsFound = true;
                                for (String name : names) {
                                    Contact contact = phoneBook.searchContactBasedOnName(name.trim());
                                    if (contact != null) {
                                        contactsInEventBST.add(contact.getContactName(), contact); 
                                    } else {
                                        System.out.println("Warning: Contact not found: '" + name.trim() + "'. Skipping.");
                                        allContactsFound = false; 
                                    }
                                }
                                if (contactsInEventBST.isEmpty() && names.length > 0) {
                                     System.out.println("Error: No valid contacts found for the event.");
                                     break; 
                                }

                            } else { // Appointment
                                System.out.print("Enter contact name: ");
                                String contactName = input.nextLine();
                                Contact contact = phoneBook.searchContactBasedOnName(contactName.trim());
                                if (contact != null) {
                                    contactsInEventBST.add(contact.getContactName(), contact);
                                } else {
                                    System.out.println("Error: Contact '" + contactName.trim() + "' not found. Cannot schedule appointment.");
                                    break; 
                                }
                            }

                            System.out.print("Enter " + typeStr + " date and time (MM/DD/YYYY HH:MM): ");
                            String dateTime = input.nextLine();
                            System.out.print("Enter " + typeStr + " location: ");
                            String location = input.nextLine();


                            if (!title.trim().isEmpty() && !dateTime.trim().isEmpty() && !contactsInEventBST.isEmpty()) {
                                phoneBook.scheduleEvent(isEvent, title, dateTime, location, contactsInEventBST);
                            } else {
                                 System.out.println("Scheduling cancelled due to missing information or contacts.");
                            }
                            break;
                        }

                        case 5: {
                            System.out.println("\nSearch for event by:");
                            System.out.println("1. Contact name");
                            System.out.println("2. Event title");
                            System.out.print("Enter your choice: ");
                            int searchChoice = input.nextInt();
                            input.nextLine(); 

                            String searchValue = "";
                            boolean validInput = true;
                            if (searchChoice == 1) {
                                System.out.print("Enter contact name: ");
                                searchValue = input.nextLine();
                            } else if (searchChoice == 2) {
                                System.out.print("Enter event title: ");
                                searchValue = input.nextLine();
                            } else {
                                System.out.println("Invalid choice.");
                                validInput = false;
                            }

                            if (validInput && !searchValue.trim().isEmpty()) {
                                Event foundEvent = phoneBook.searchEventsByTitleOrContactName(searchChoice, searchValue);
                                if (foundEvent != null) {
                                    System.out.println("\nEvent details:");
                                    System.out.println(foundEvent);
                                } else {
                                    System.out.println("No event found matching the criteria.");
                                }
                            } else if (validInput) {
                                System.out.println("Search value cannot be empty.");
                            }
                            break;
                        }

                        case 6: {
                            System.out.print("Enter the first name: ");
                            String firstName = input.nextLine();
                            if (firstName.trim().isEmpty()) {
                                System.out.println("First name cannot be empty.");
                            } else {
                                BST<Contact> contactsFound = phoneBook.findContactsByFirstName(firstName);
                                if (contactsFound.isEmpty()) {
                                    System.out.println("No contacts found with the first name '" + firstName + "'.");
                                } else {
                                    System.out.println("\nContacts found with first name '" + firstName + "':");
                                    contactsFound.printInOrder();
                                }
                            }
                            break;
                        }

                        case 7: {
                            phoneBook.printAllEventsAlphabetically();
                            break;
                        }

                        case 8: {
                            System.out.println("Goodbye!");
                            break;
                        }
                        default: {
                            System.out.println("Invalid choice. Please try again.");
                            break;
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    input.nextLine(); 
                    choice = 0;
                } catch (Exception e) {
                     System.out.println("An unexpected error occurred: " + e.getMessage());

                     e.printStackTrace(); 
                     choice = 0;
                }

                System.out.println(); 

            } while (choice != 8);

        } 
    }
}
