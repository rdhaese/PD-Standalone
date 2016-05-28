package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.from_url;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Robin D'Haese
 */
@Component
public class FromUrlResultHolder {

    //Client properties
    private StringProperty clientName = new SimpleStringProperty();
    private StringProperty clientPhone = new SimpleStringProperty();
    private StringProperty clientEmail = new SimpleStringProperty();
    private StringProperty clientStreet = new SimpleStringProperty();
    private StringProperty clientNumber = new SimpleStringProperty();
    private StringProperty clientMailbox = new SimpleStringProperty();
    private StringProperty clientCity = new SimpleStringProperty();
    private StringProperty clientPostalCode = new SimpleStringProperty();

    //Delivery Properties
    private StringProperty deliveryName = new SimpleStringProperty();
    private StringProperty deliveryPhone = new SimpleStringProperty();
    private StringProperty deliveryEmail = new SimpleStringProperty();
    private StringProperty deliveryStreet = new SimpleStringProperty();
    private StringProperty deliveryNumber = new SimpleStringProperty();
    private StringProperty deliveryMailbox = new SimpleStringProperty();
    private StringProperty deliveryCity = new SimpleStringProperty();
    private StringProperty deliveryPostalCode = new SimpleStringProperty();

    public String getClientName() {
        return clientName.get();
    }

    public StringProperty clientNameProperty() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName.set(clientName);
    }

    public String getClientPhone() {
        return clientPhone.get();
    }

    public StringProperty clientPhoneProperty() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone.set(clientPhone);
    }

    public String getClientEmail() {
        return clientEmail.get();
    }

    public StringProperty clientEmailProperty() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail.set(clientEmail);
    }

    public String getClientStreet() {
        return clientStreet.get();
    }

    public StringProperty clientStreetProperty() {
        return clientStreet;
    }

    public void setClientStreet(String clientStreet) {
        this.clientStreet.set(clientStreet);
    }

    public String getClientNumber() {
        return clientNumber.get();
    }

    public StringProperty clientNumberProperty() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber.set(clientNumber);
    }

    public String getClientMailbox() {
        return clientMailbox.get();
    }

    public StringProperty clientMailboxProperty() {
        return clientMailbox;
    }

    public void setClientMailbox(String clientMailbox) {
        this.clientMailbox.set(clientMailbox);
    }

    public String getClientCity() {
        return clientCity.get();
    }

    public StringProperty clientCityProperty() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity.set(clientCity);
    }

    public String getClientPostalCode() {
        return clientPostalCode.get();
    }

    public StringProperty clientPostalCodeProperty() {
        return clientPostalCode;
    }

    public void setClientPostalCode(String clientPostalCode) {
        this.clientPostalCode.set(clientPostalCode);
    }

    public String getDeliveryName() {
        return deliveryName.get();
    }

    public StringProperty deliveryNameProperty() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName.set(deliveryName);
    }

    public String getDeliveryPhone() {
        return deliveryPhone.get();
    }

    public StringProperty deliveryPhoneProperty() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone.set(deliveryPhone);
    }

    public String getDeliveryEmail() {
        return deliveryEmail.get();
    }

    public StringProperty deliveryEmailProperty() {
        return deliveryEmail;
    }

    public void setDeliveryEmail(String deliveryEmail) {
        this.deliveryEmail.set(deliveryEmail);
    }

    public String getDeliveryStreet() {
        return deliveryStreet.get();
    }

    public StringProperty deliveryStreetProperty() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet.set(deliveryStreet);
    }

    public String getDeliveryNumber() {
        return deliveryNumber.get();
    }

    public StringProperty deliveryNumberProperty() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber.set(deliveryNumber);
    }

    public String getDeliveryMailbox() {
        return deliveryMailbox.get();
    }

    public StringProperty deliveryMailboxProperty() {
        return deliveryMailbox;
    }

    public void setDeliveryMailbox(String deliveryMailbox) {
        this.deliveryMailbox.set(deliveryMailbox);
    }

    public String getDeliveryCity() {
        return deliveryCity.get();
    }

    public StringProperty deliveryCityProperty() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity.set(deliveryCity);
    }

    public String getDeliveryPostalCode() {
        return deliveryPostalCode.get();
    }

    public StringProperty deliveryPostalCodeProperty() {
        return deliveryPostalCode;
    }

    public void setDeliveryPostalCode(String deliveryPostalCode) {
        this.deliveryPostalCode.set(deliveryPostalCode);
    }
}
