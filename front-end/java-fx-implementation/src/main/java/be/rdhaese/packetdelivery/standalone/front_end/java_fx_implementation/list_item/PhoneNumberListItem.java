package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.list_item;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 *
 * @author Robin D'Haese
 */
public class PhoneNumberListItem{

    private MessageSource messageSource;

    private String phoneNumberTitle;
    private String phoneNumber;

    public PhoneNumberListItem(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public PhoneNumberListItem(MessageSource messageSource, String phoneNumberTitle, String phoneNumber) {
        this(messageSource);
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
        return messageSource.getMessage("listItem.phoneNumber", new Object[]{phoneNumberTitle, phoneNumber}, LocaleContextHolder.getLocale());
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
