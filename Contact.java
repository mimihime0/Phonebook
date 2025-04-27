import java.util.*;

public class Contact { 
    private String contactName;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String birthday;
    private String notes;

    public Contact(String contactName, String phoneNumber, String emailAddress, String address, String birthday, String notes) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;

    }

    public Contact() {
        this("", "", "", "", "", "");
    }


    public String getContactName() {
        return contactName;
    }


    public String getFirstName() {
        if (contactName == null || contactName.trim().isEmpty()) {
            return "";
        }
        String trimmedName = contactName.trim();
        int firstSpaceIndex = trimmedName.indexOf(" ");
        if (firstSpaceIndex == -1) {
            return trimmedName; 
        } else {
            return trimmedName.substring(0, firstSpaceIndex);
        }
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

    public String getNotes() {
        return notes;
    }


    public void setContactName(String contactName) {
        this.contactName = contactName;
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
    public String toString() {
        return "Name: " + (contactName != null ? contactName : "N/A") +
                "\nPhone Number: " + (phoneNumber != null ? phoneNumber : "N/A") +
                "\nEmail Address: " + (emailAddress != null ? emailAddress : "N/A") +
                "\nAddress: " + (address != null ? address : "N/A") +
                "\nBirthday: " + (birthday != null ? birthday : "N/A") +
                "\nNotes: " + (notes != null ? notes : "N/A");
    }
}
