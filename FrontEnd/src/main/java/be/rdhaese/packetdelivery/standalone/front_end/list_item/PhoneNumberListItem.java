package be.rdhaese.packetdelivery.standalone.front_end.list_item;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
public class PhoneNumberListItem {
    private String phoneNumberTitle;
    private String phoneNumber;

    public PhoneNumberListItem() {
    }

    public PhoneNumberListItem(String phoneNumberTitle, String phoneNumber) {
        this.phoneNumberTitle = phoneNumberTitle;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumberListItem that = (PhoneNumberListItem) o;

        return !(getPhoneNumberTitle() != null ? !getPhoneNumberTitle().equals(that.getPhoneNumberTitle()) : that.getPhoneNumberTitle() != null);

    }

    @Override
    public int hashCode() {
        return getPhoneNumberTitle() != null ? getPhoneNumberTitle().hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("Title: %s - Phone Number: %s", phoneNumberTitle, phoneNumber);
    }

    public String getPhoneNumberTitle() {
        return phoneNumberTitle;
    }

    public void setPhoneNumberTitle(String phoneNumberTitle) {
        this.phoneNumberTitle = phoneNumberTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
