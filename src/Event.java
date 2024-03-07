import java.util.*;

public class Event implements Comparable<String> {
    private boolean isEvent;  // true for event, false for appointment
    private String eventTitle;
    private String dateTime;
    private String location;
    private BST<Contact> contacts;

    public Event() {
        this.isEvent=false;/** what should we put here?**/
        this.eventTitle = null;
        this.dateTime = null;
        this.location = null;
        this.contacts = new BST<>();
    }

    public Event(boolean isEvent, String eventTitle, String dateTime, String location, BST<Contact> contacts) {
        this.isEvent = isEvent;
        this.eventTitle = eventTitle;
        this.dateTime = dateTime;
        this.location = location;
        this.contacts = contacts;
    }
    public String getEventTitle() {
        return eventTitle;
    }


    public String getDateTime() {
        return dateTime;
    }

    public String getEventLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Event{" +
                "isEvent=" + isEvent +
                ", eventTitle='" + eventTitle + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", location='" + location + '\'' +
                ", contacts=" + contacts +
                '}';
    }

    @Override
    public int compareTo(String otherEvent) {
        return this.eventTitle.compareTo(otherEvent);
    }
}
