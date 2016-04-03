package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.list_item;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
public class EmailAddressListItem {

    private MessageSource messageSource;

    private String emailAddressTitle;
    private String emailAddress;

    public EmailAddressListItem(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public EmailAddressListItem(MessageSource messageSource, String emailAddress, String emailAddressTitle) {
        this(messageSource);
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
        return messageSource.getMessage("listItem.emailAddress", new Object[]{emailAddressTitle, emailAddress}, LocaleContextHolder.getLocale());
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
