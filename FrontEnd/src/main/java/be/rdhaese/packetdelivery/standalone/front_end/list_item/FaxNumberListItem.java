package be.rdhaese.packetdelivery.standalone.front_end.list_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
public class FaxNumberListItem {

    private MessageSource messageSource;

    private String faxNumberTitle;
    private String faxNumber;

    public FaxNumberListItem(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public FaxNumberListItem(MessageSource messageSource, String faxNumberTitle, String faxNumber) {
        this(messageSource);
        this.faxNumberTitle = faxNumberTitle;
        this.faxNumber = faxNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FaxNumberListItem that = (FaxNumberListItem) o;

        return !(faxNumberTitle != null ? !faxNumberTitle.equals(that.faxNumberTitle) : that.faxNumberTitle != null);

    }

    @Override
    public int hashCode() {
        return faxNumberTitle != null ? faxNumberTitle.hashCode() : 0;
    }

    @Override
    public String toString() {
        return messageSource.getMessage("listItem.faxNumber", new Object[]{faxNumberTitle, faxNumber}, LocaleContextHolder.getLocale());
    }

    public String getFaxNumberTitle() {
        return faxNumberTitle;
    }

    public void setFaxNumberTitle(String faxNumberTitle) {
        this.faxNumberTitle = faxNumberTitle;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }
}
