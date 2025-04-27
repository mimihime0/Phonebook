import java.util.*;

public class Event { 
    private boolean isEvent;  
    private String eventTitle;
    private String dateTime; 
    private String location;
    private BST<Contact> contacts;


    public Event() {

        this(true, "Untitled Event", "", "", new BST<>());
    }

    public Event(boolean isEvent, String eventTitle, String dateTime, String location, BST<Contact> contacts) {
        this.isEvent = isEvent;
        this.eventTitle = (eventTitle != null) ? eventTitle : "Untitled Event";
        this.dateTime = dateTime;
        this.location = location;

        this.contacts = (contacts != null) ? contacts : new BST<>();
    }


    public boolean isEvent() {
        return isEvent;
    }

    public String getEventType() {
        return isEvent ? "Event" : "Appointment";
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    public BST<Contact> getContacts() {
        return contacts;
    }


    public void setEvent(boolean event) {
        isEvent = event;
    }
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setContacts(BST<Contact> contacts) {
        this.contacts = contacts;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getEventType()).append(": ").append(eventTitle != null ? eventTitle : "N/A");
        sb.append("\nTime: ").append(dateTime != null ? dateTime : "N/A");
        sb.append("\nLocation: ").append(location != null ? location : "N/A");
        sb.append("\nContacts Involved:");
        if (contacts == null || contacts.isEmpty()) {
            sb.append("\n  None");
        } else {
            List<Contact> contactList = contacts.getAllDataInOrder(); 
             if (contactList.isEmpty()) {
                 sb.append("\n  None (Error retrieving contacts)"); 
             } else {
                 for(Contact c : contactList) {
                     sb.append("\n  - ").append(c.getContactName());
                 }
             }
        }
        return sb.toString();
    }


}
