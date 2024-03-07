
public class PhoneBook {


    private BST<Contact> contactBST;
    private BST<Event> eventBST;


    public PhoneBook() {
        this.contactBST = new BST<>();
        this.eventBST = new BST<>();
    }

    public BST<Contact> getContactBST() {
        return contactBST;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////1. Add a contact
    //The contact is unique in their name and phone
    //number. You will use the name as the key in the BST, so that will already need to be unique. For the
    //phone number, you can ensure this by checking if a contact with the same phone number already
    //exists in the BST (using in order traversal) before adding a new contact.
    public void addContact(Contact contact)
    {
        if(! contactBST.isEmpty()) {
            if (searchContactInfo(contact.getPhoneNumber(), 2).isEmpty()) {
                if (contactBST.add(contact.getContactName(), contact)) {
                    if (contactBST.isEmpty())

                        System.out.println("Contact added  successfully!");
                        if (contactBST.isEmpty())
                            System.out.println("I'm in add Contact and empty");

                }
                else
                    System.out.println("Couldn't add Contact! There exists a contact with the same name!");
            } else
                System.out.println("Couldn't add Contact! There exists a contact with the same phone number!");
        }
        else
        {
            contactBST.add(contact.getContactName(), contact);
            System.out.println("Contact added  successfully!");
        }

        }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////2. Search for a contact
public Contact searchContactBasedOnName(String name) {
    if (!contactBST.isEmpty()) {
        BSTNode<Contact> foundNode = contactBST.findKeyNode(name);
        if (foundNode != null) {
            System.out.println("Contact Found");
            return foundNode.getData();
        }
        System.out.println("Contact not found!");
    }
    System.out.println("Hi I'm empty");
    return null;
}


    public BST<Contact> searchContactInfo(String searchItem, int choice)
    {
        BST<Contact> result = new BST<>();//creating BST to return contacts with given search criteria
        if (! contactBST.isEmpty()) {
            if (choice == 1)//name here we will search by key
            {
                Contact contact = new Contact(searchItem);
                result.add(searchItem, contact);
                return result;
            } else if (choice == 2 || choice == 3 || choice == 4 || choice == 5)
                searchByAttributeInOrder(contactBST.getRoot(), choice, searchItem, result);
            else
                System.out.println("Invalid choice");
        }
        return result;

    }
    public void searchByAttributeInOrder(BSTNode<Contact> currentNode, int choice, String searchItem, BST<Contact> result)
    {

            if (currentNode !=null)
            {
                switch (choice) {
                    case 2: // phone
                        if (currentNode.getData().getPhoneNumber().equals(searchItem)) {
                            result.add(currentNode.getKey(), currentNode.getData());
                        }
                        break;
                    case 3: // email
                        if (currentNode.getData().getEmailAddress().equals(searchItem)) {
                            result.add(currentNode.getKey(), currentNode.getData());
                        }
                        break;
                    case 4: // address
                        if (currentNode.getData().getAddress().equals(searchItem)) {
                            result.add(currentNode.getKey(), currentNode.getData());
                        }
                        break;
                    case 5: // birthday
                        if (currentNode.getData().getBirthday().equals(searchItem)) {
                            result.add(currentNode.getKey(), currentNode.getData());
                        }
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
                searchByAttributeInOrder(currentNode.getLeft(), choice, searchItem, result);
                searchByAttributeInOrder(currentNode.getRight(), choice, searchItem, result);
            }

    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////3. Delete a contact

    public void deleteContact(String name) {
        // Include removing associated events and appointments
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////4. Schedule an event/appointment
    public void scheduleEvent(boolean isEvent, String title, String dateTime, String location, BST<Contact> contacts) {
        if (isTimeSlotAvailable(dateTime)) {
            if (isEvent) {
                Event event = new Event(true, title, dateTime, location, contacts);
                eventBST.add(title,event);
                System.out.println("Event scheduled successfully!");
            } else {
                // Appointments only have 1 contact
                if (contacts.size() == 1) {
                    Event appointment = new Event(false, title,dateTime, location, contacts);
                    eventBST.add(title,appointment);
                    System.out.println("Appointment scheduled successfully!");
                } else {
                    System.out.println("Error: Appointments can only be scheduled with one contact.");
                }
            }
        } else {
            System.out.println("Error: Time slot already occupied. Choose another time.");
        }
    }

//    private boolean isTimeSlotAvailable(String dateTime) {
//        for (Event event : events) {
//            if (event.getDateTime().equals(dateTime)) {
//                return false;
//            }
//        }
//        return true;
//    }

    private boolean isTimeSlotAvailable(String dateTime) {
        BSTNode<Event> current = eventBST.getRoot();
        return isTimeSlotAvailableRecursive(current, dateTime);
    }

    private boolean isTimeSlotAvailableRecursive(BSTNode<Event> node, String dateTime) {
        if (node == null) {
            return true; // The tree is empty, so the time slot is available
        }

        int compareResult = dateTime.compareTo(node.getData().getDateTime());

        if (compareResult < 0) {
            // Check left subtree
            return isTimeSlotAvailableRecursive(node.getLeft(), dateTime);
        } else if (compareResult > 0) {
            // Check right subtree
            return isTimeSlotAvailableRecursive(node.getRight(), dateTime);
        } else {
            // The time slot is not available if the dateTime matches an existing event
            return false;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////5. Print event details

//    public Event searchEventsByTitleOrContactName(int searchCriteria, String value) {
//
//        Event searchedEvent = new Event();
//        if(searchCriteria==1 && ! contactBST.isEmpty())//contactName
//        {
//            Contact foundContact;
//            BST<Event> eventsForContact;
//            foundContact=searchContactBasedOnName(value);
//            eventsForContact= getEventsForContact();
//            searchedEvent=eventsForContact.findKey(value);
//        }
//        else if(searchCriteria==2 && ! eventBST.isEmpty())
//        {
//            searchedEvent=eventBST.findKey(value).;
//        }
//        return searchedEvent;
//    }

    public Event searchEventsByTitleOrContactName(int searchCriteria, String value) {
        Event searchedEvent = null;

        if (searchCriteria == 1 && ! contactBST.isEmpty()) {
            // Search by contact name
            Contact foundContact = searchContactBasedOnName(value);

            if (foundContact != null) {//the contact exists
                BST<Event> eventsForContact = foundContact.getEventsForContact();
                if (!eventsForContact.isEmpty()) {
                    eventsForContact.findKeyNode(value);
                    searchedEvent=eventsForContact.retrieve();
                }
            }
        } else if (searchCriteria == 2 && !eventBST.isEmpty()) {
            // Search by event title
            eventBST.findKey(value);
            searchedEvent=eventBST.retrieve();
        }

        return searchedEvent;
    }



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////6. Print contacts by first name

    public BST<Contact> printContactsByFirstName(String firstName) {
        BST<Contact> contactsSameFirstName = new BST<>();
        printContactsByFirstNameRecursive(contactBST.getRoot(), firstName,contactsSameFirstName);
        return contactsSameFirstName;
    }

    private void   printContactsByFirstNameRecursive(BSTNode<Contact> node, String firstName,BST<Contact> result) {
        if (node != null) {
            // Traverse the left subtree
            printContactsByFirstNameRecursive(node.getLeft(), firstName,result);

            // Check if the contact's first name matches the given first name
            if (node.getData().getFirstName().equals(firstName)) {
                result.add(node.getKey(), node.getData()); // Add the contact
            }

            // Traverse the right subtree
            printContactsByFirstNameRecursive(node.getRight(), firstName,result);
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7. Print all events alphabetically

    public void printAllEventsAlphabetically() {
        eventBST.printInOrder();
    }

}
