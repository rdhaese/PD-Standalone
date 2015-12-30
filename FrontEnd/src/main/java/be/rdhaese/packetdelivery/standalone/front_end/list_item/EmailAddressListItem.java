package be.rdhaese.packetdelivery.standalone.front_end.list_item;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
public class EmailAddressListItem {

    private String emailAddressTitle;
    private String emailAddress;

    public EmailAddressListItem() {
    }

    public EmailAddressListItem(String emailAddress, String emailAddressTitle) {
        this.emailAddress = emailAddress;
        this.emailAddressTitle = emailAddressTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailAddressListItem that = (EmailAddressListItem) o;

        return !(emailAddressTitle != null ? !emailAddressTitle.equals(that.emailAddressTitle) : that.emailAddressTitle != null);

    }

    @Override
    public int hashCode() {
        return emailAddressTitle != null ? emailAddressTitle.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("Title: %s - Email Address: %s", emailAddressTitle, emailAddress);
    }

    public String getEmailAddressTitle() {
        return emailAddressTitle;
    }

    public void setEmailAddressTitle(String emailAddressTitle) {
        this.emailAddressTitle = emailAddressTitle;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
