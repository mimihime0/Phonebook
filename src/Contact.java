import java.util.*;

public class Contact implements Comparable<String> {
    private String contactName;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String birthday;
    private String notes;
    private BST<Event> eventsForContact;



    public Contact(String contactName, String phoneNumber, String emailAddress, String address, String birthday, String notes) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
        eventsForContact=new BST<>();
    }

    public Contact() {
        contactName = "";
        this.phoneNumber = "";
        this.emailAddress = "";
        this.address = "";
        this.birthday = "";
        this.notes = "";
        this.eventsForContact=new BST<>();
    }

    public Contact(String contactName) {
        contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getFirstName()
    {
        String nameWithoutExtraSpaces=getContactName().trim();
        return nameWithoutExtraSpaces.substring(0,nameWithoutExtraSpaces.indexOf(" "));

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }


    public BST<Event> getEventsForContact() {
        return eventsForContact;
    }

    public String getNotes() {
        return notes;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int compareTo(String otherContactName) {

        return contactName.compareTo(otherContactName);
    }

    @Override
    public String toString() {
        return "Name: " + contactName +
                "\nPhone Number: " + phoneNumber +
                "\nEmail Address: " + emailAddress +
                "\nAddress: " + address +
                "\nBirthday: " + birthday +
                "\nNotes: " + notes;
    }
}
