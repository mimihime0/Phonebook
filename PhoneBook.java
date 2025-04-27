import java.util.List; 
import java.util.ArrayList; 

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
    public void addContact(Contact contact)
    {
        if (contact == null || contact.getContactName() == null || contact.getContactName().trim().isEmpty()
                || contact.getPhoneNumber() == null || contact.getPhoneNumber().trim().isEmpty()) {
            System.out.println("Error: Contact name and phone number cannot be empty.");
            return;
        }

        if (!isPhoneNumberUnique(contactBST.getRoot(), contact.getPhoneNumber())) {
             System.out.println("Couldn't add Contact! There exists a contact with the same phone number: " + contact.getPhoneNumber());
             return;
        }

        if (contactBST.add(contact.getContactName(), contact)) {
            System.out.println("Contact added successfully!");
        } else {
            System.out.println("Couldn't add Contact! There exists a contact with the same name: " + contact.getContactName());
        }
    }

    private boolean isPhoneNumberUnique(BSTNode<Contact> node, String phoneNumber) {
        if (node == null) return true;
        if (!isPhoneNumberUnique(node.getLeft(), phoneNumber)) return false;
        if (node.getData() != null && node.getData().getPhoneNumber().equals(phoneNumber)) return false;
        return isPhoneNumberUnique(node.getRight(), phoneNumber);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////2. Search for a contact
    public Contact searchContactBasedOnName(String name) {
        if (name == null || name.trim().isEmpty()) {

             return null;
        }

        return contactBST.search(name.trim());
    }

    public BST<Contact> searchContactInfo(String searchItem, int choice)
    {
        BST<Contact> result = new BST<>();
        if (searchItem == null || searchItem.trim().isEmpty()) {

             return result;
        }
        if (!contactBST.isEmpty()) {
            searchByAttributeInOrder(contactBST.getRoot(), choice, searchItem.trim(), result);
        }
        return result;
    }

    private void searchByAttributeInOrder(BSTNode<Contact> currentNode, int choice, String searchItem, BST<Contact> result)
    {
        if (currentNode != null) {
            searchByAttributeInOrder(currentNode.getLeft(), choice, searchItem, result);
            Contact data = currentNode.getData();
            if (data != null) {
                boolean match = false;
                switch (choice) {
                    case 2: match = data.getPhoneNumber() != null && data.getPhoneNumber().equals(searchItem); break;
                    case 3: match = data.getEmailAddress() != null && data.getEmailAddress().equalsIgnoreCase(searchItem); break;
                    case 4: match = data.getAddress() != null && data.getAddress().equalsIgnoreCase(searchItem); break;
                    case 5: match = data.getBirthday() != null && data.getBirthday().equals(searchItem); break;
                }
                if (match) {
                    result.add(currentNode.getKey(), data);
                }
            }
            searchByAttributeInOrder(currentNode.getRight(), choice, searchItem, result);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////3. Delete a contact

    public boolean deleteContact(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Contact name cannot be empty for deletion.");
            return false;
        }
        name = name.trim();

        Contact contactToDelete = contactBST.search(name);
        if (contactToDelete == null) {
            System.out.println("Error: Contact '" + name + "' not found.");
            return false;
        }

        boolean removed = contactBST.remove(name);

        if (removed) {
            System.out.println("Contact '" + name + "' deleted successfully.");


            removeContactFromAllEvents(name);

        } else {

            System.out.println("Error: Failed to remove contact '" + name + "' from phonebook BST.");
        }
        return removed;
    }

    private void removeContactFromAllEvents(String contactName) {
        if (eventBST.isEmpty()) {
            return; 
        }

        List<Event> allEvents = eventBST.getAllDataInOrder(); 

        for (Event event : allEvents) {
            if (event != null && event.getContacts() != null) {

                boolean removedFromEvent = event.getContacts().remove(contactName);
                if (removedFromEvent) {
                     System.out.println("  - Removed contact '" + contactName + "' from event '" + event.getEventTitle() + "'.");
    
                }
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////4. Schedule an event/appointment
    public void scheduleEvent(boolean isEvent, String title, String dateTime, String location, BST<Contact> contactsInEvent) {
        if (title == null || title.trim().isEmpty() || dateTime == null || dateTime.trim().isEmpty() || contactsInEvent == null || contactsInEvent.isEmpty()) {
             System.out.println("Error: Event title, date/time, and at least one contact are required.");
             return;
        }

        if (!isEvent && contactsInEvent.size() != 1) {
             System.out.println("Error: Appointments must involve exactly one contact.");
             return;
        }

        if (isTimeSlotAvailable(dateTime)) {
            Event newEvent = new Event(isEvent, title, dateTime, location, contactsInEvent);

            if (eventBST.add(title, newEvent)) {
                System.out.println((isEvent ? "Event" : "Appointment") + " scheduled successfully!");

            } else {
                 System.out.println("Error: An event with the same title already exists.");
            }
        } else {
            System.out.println("Error: Time slot " + dateTime + " already occupied. Choose another time.");
        }
    }

    private boolean isTimeSlotAvailable(String dateTime) {
        if (dateTime == null || dateTime.trim().isEmpty()) return false;
        return checkTimeAvailabilityRecursive(eventBST.getRoot(), dateTime.trim());
    }

    private boolean checkTimeAvailabilityRecursive(BSTNode<Event> node, String dateTime) {
        if (node == null) return true;
        if (!checkTimeAvailabilityRecursive(node.getLeft(), dateTime)) return false;
        Event currentEvent = node.getData();
        if (currentEvent != null && currentEvent.getDateTime() != null && currentEvent.getDateTime().equals(dateTime)) return false;
        return checkTimeAvailabilityRecursive(node.getRight(), dateTime);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////5. Print event details
    public Event searchEventsByTitleOrContactName(int searchCriteria, String value) {
         if (value == null || value.trim().isEmpty()) {

             return null;
         }
         value = value.trim();

        if (searchCriteria == 1) { 
            return findEventByContactNameRecursive(eventBST.getRoot(), value);
        } else if (searchCriteria == 2) { 

            return eventBST.search(value);
        }
        return null;
    }

    private Event findEventByContactNameRecursive(BSTNode<Event> eventNode, String contactName) {
         if (eventNode == null) return null;
         Event found = findEventByContactNameRecursive(eventNode.getLeft(), contactName);
         if (found != null) return found;
         Event currentEvent = eventNode.getData();

         if (currentEvent != null && currentEvent.getContacts() != null && currentEvent.getContacts().containsKey(contactName)) {
              return currentEvent;
         }
         return findEventByContactNameRecursive(eventNode.getRight(), contactName);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////6. Print contacts by first name
    public BST<Contact> findContactsByFirstName(String firstName) { 
        BST<Contact> contactsSameFirstName = new BST<>();
         if (firstName == null || firstName.trim().isEmpty()) {

             return contactsSameFirstName;
         }
         firstName = firstName.trim();
        findContactsByFirstNameRecursive(contactBST.getRoot(), firstName, contactsSameFirstName);
        return contactsSameFirstName;
    }

    private void findContactsByFirstNameRecursive(BSTNode<Contact> node, String firstName, BST<Contact> result) {
        if (node != null) {
            findContactsByFirstNameRecursive(node.getLeft(), firstName, result);
            Contact data = node.getData();
            if (data != null && data.getContactName() != null) {
                 String contactName = data.getContactName().trim();
                 int firstSpace = contactName.indexOf(" ");
                 String contactFirstName = (firstSpace == -1) ? contactName : contactName.substring(0, firstSpace);
                 if (contactFirstName.equalsIgnoreCase(firstName)) {
                    result.add(node.getKey(), data);
                 }
            }
            findContactsByFirstNameRecursive(node.getRight(), firstName, result);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7. Print all events alphabetically
    public void printAllEventsAlphabetically() {
         if (eventBST.isEmpty()) {
             System.out.println("No events scheduled.");
             return;
         }
        System.out.println("\nAll Events (Alphabetical by Title):");
        eventBST.printInOrder();
    }

}
