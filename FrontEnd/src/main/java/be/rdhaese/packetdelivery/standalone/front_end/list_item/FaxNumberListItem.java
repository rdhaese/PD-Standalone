package be.rdhaese.packetdelivery.standalone.front_end.list_item;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
public class FaxNumberListItem {
    private String faxNumberTitle;
    private String faxNumber;

    public FaxNumberListItem() {
    }

    public FaxNumberListItem(String faxNumberTitle, String faxNumber) {
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
        return String.format("Title: %s - Fax Number: %s", faxNumberTitle, faxNumber);
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
