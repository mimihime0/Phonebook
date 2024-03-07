import java.util.*;
public class Main {
    public static void main(String[] args)
    {
        PhoneBook phoneBook=new PhoneBook();
        Scanner input=new Scanner(System.in);



        System.out.println("Welcome to the Linked Tree Phonebook!\n" +
                "Please choose an option:");
        int choice=0;
        do
        {
            System.out.println("1. Add a contact\n" +
                    "2. Search for a contact\n" +
                    "3. Delete a contact\n" +
                    "4. Schedule an event\n" +
                    "5. Print event details\n" +
                    "6. Print contacts by first name\n" +
                    "7. Print all events alphabetically\n" +
                    "8. Exit");
            System.out.println("Enter your choice:");
            choice=input.nextInt();

            switch (choice) {
                //1. Add a contact
                case (1): {
                    input.nextLine();
                    System.out.println("Enter the contact's name: ");
                    String contactName = input.nextLine();
                    System.out.println("Enter the contact's phone number:");
                    String phoneNumber = input.nextLine();
                    System.out.println("Enter the contact's email address:");
                    String emailAddress = input.nextLine();
                    System.out.println("Enter the contact's address:");
                    String address = input.nextLine();
                    System.out.println("Enter the contact's birthday:");
                    String birthday = input.nextLine();
                    System.out.println("Enter any notes for the contact:");
                    String notes = input.nextLine();

                    Contact contact = new Contact(contactName, phoneNumber, emailAddress, address, birthday, notes);
                    phoneBook.addContact(contact);
                    break;
                }
                //2. Search for a contact
                case (2): {
                    System.out.println("Enter search criteria:");
                    System.out.println("1. Name\n" +
                            "2. Phone Number\n" +
                            "3. Email Address\n" +
                            "4. Address\n" +
                            "5. Birthday");
                    System.out.println("Enter your choice");
                    int choiceInChoice2 = input.nextInt();
                    input.nextLine();
                    input.nextLine();

                    switch (choiceInChoice2) {
                        //search for contacts based on a criteria and add them to a list
                        //display the list
                        case 1: {
                            System.out.println("Enter the contact's name:");
                            String contactNameSearched = input.nextLine();
                            Contact matchingContact = phoneBook.searchContactBasedOnName(contactNameSearched);

                            if (matchingContact != null) {
                                System.out.println("Contact found:");
                                System.out.println(matchingContact);
                            } else {
                                System.out.println("No contact found with the given name.");
                            }
                        }
                        break;
                        case (2): {
                            System.out.println("Enter the contact's phone number");
                            String contactNameSearched = input.nextLine();
                            phoneBook.searchContactInfo(contactNameSearched,choiceInChoice2).printInOrder();
                        }
                        break;
                        case (3): {
                            System.out.println("Enter the contact's email address");
                            String contactNameSearched = input.nextLine();
                            phoneBook.searchContactInfo(contactNameSearched,choiceInChoice2).printInOrder();
                        }
                        break;
                        case (4): {
                            System.out.println("Enter the contact's address");
                            String contactNameSearched = input.nextLine();
                            phoneBook.searchContactInfo(contactNameSearched,choiceInChoice2).printInOrder();
                        }
                        break;
                        case (5): {
                            System.out.println("Enter the contact's birthday");
                            String contactNameSearched = input.nextLine();
                            phoneBook.searchContactInfo(contactNameSearched,choiceInChoice2).printInOrder();
                        }
                        break;
                    }
                    break;
                }
                //3. Delete a contact
                case (3): {
//                    input.nextLine();
//                    System.out.println("Enter the contact's name:");
//                    String deletedContactName = input.nextLine();
//                    BST<Contact> deletedContactList =phoneBook.searchContactInfo(deletedContactName,1);//returnl ist
//                    if (!deletedContactList.isEmpty())
//                        phoneBook.deleteContact(deletedContactList.retrieve());
                }
                break;
                // Schedule an event
                case (4):
                {
                    System.out.println("Enter type:\n" +
                            "1. event\n" +
                            "2. appointment\n");
                    System.out.println("Enter your choice: ");
                    int eventOrAppointment =input.nextInt();
                    input.nextLine();
                    BST<Contact> contactsInEventBST = new BST<>();
                    if (eventOrAppointment==1)
                    {
                        System.out.println("Enter event title:");
                        String eventTitle=input.nextLine();
                        System.out.println("Enter contacts name separated by a comma:");
                        String contactNames=input.nextLine();
                        String[] names = contactNames.split(",");

                        for (String name : names) {
                            Contact contact = phoneBook.searchContactBasedOnName(name.trim());
                            if (contact != null) {
                                contactsInEventBST.add(contact.getContactName(), contact);
                            } else {
                                System.out.println("Contact not found: " + name);
                            }
                        }

                        System.out.println("Enter event date and time (MM/DD/YYYY HH:MM):");
                        String dateTime=input.nextLine();
                        System.out.println("Enter event location:");
                        String eventLocation=input.nextLine();

                        phoneBook.scheduleEvent(true, eventTitle, dateTime, eventLocation, contactsInEventBST);
                    }
                    else if (eventOrAppointment==2)// Appointment
                    {
                        System.out.println("Enter appointment title:");
                        String eventTitle=input.nextLine();
                        System.out.println("Enter contact name: ");
                        String contactName=input.nextLine();

                        Contact contact = phoneBook.searchContactBasedOnName(contactName);

                        System.out.println("Enter appointment  date and time (MM/DD/YYYY HH:MM):");
                        String dateTime=input.nextLine();
                        System.out.println("Enter appointment  location:");
                        String eventLocation=input.nextLine();

                        if (contact != null) {
                            contactsInEventBST.add(contact.getContactName(), contact);
                            phoneBook.scheduleEvent(false, eventTitle, dateTime, eventLocation, contactsInEventBST);
                        } else {
                            System.out.println("Contact not found: " + contactName);
                        }
                    }
                }
                break;
                //Print event details
                case (5):
                {
                    input.nextLine();
                    System.out.println("Enter search criteria:");
                    System.out.println("1. contact name\n" +
                            "2. Event tittle");
                    int search=input.nextInt();
                    input.nextLine();
                    if (search==1)//Contact name
                    {
                        input.nextLine();
                        System.out.println("Enter the contact name:");
                        String contactName = input.nextLine();
                        Event searchedEvent = phoneBook.searchEventsByTitleOrContactName(1, contactName);

                        if (searchedEvent != null)
                        {
                            System.out.println("Event found:");
                            System.out.println(searchedEvent);
                        }
                        else
                            System.out.println("No event found for the given contact name.");
                    }

                    else if(search==2)//Event
                    {
                        input.nextLine();
                        System.out.println("Enter the event title:");
                        String eventTitle=input.nextLine();
                        Event searchedEvent = phoneBook.searchEventsByTitleOrContactName(2, eventTitle);

                        if (searchedEvent != null) {
                            System.out.println("Event found:");
                            System.out.println(searchedEvent);
                        } else {
                            System.out.println("No event found with the given title.");
                        }
                    }
//
                }
                break;

                //Print contacts by first name
                case (6):
                {
                    input.nextLine();
                    System.out.println("Enter the first name:");
                    String firstName=input.nextLine();
                    BST<Contact> contactsSameFirstName;
                    contactsSameFirstName=phoneBook.printContactsByFirstName(firstName);
                    if (!contactsSameFirstName.isEmpty())//you have found similar names
                    {
                        System.out.println("Contacts found!");
                        contactsSameFirstName.printInOrder();
                    }
                    else
                        System.out.println("Sorry couldn't find contacts with the same name!");
                }
                break;
                //Print all events alphabetically
                case (7):
                {
                    phoneBook.printAllEventsAlphabetically();
                }
                break;
            }

        }while (choice!=8);//8 exit

        System.out.println("Goodbye!");
        input.close();
    }
}
