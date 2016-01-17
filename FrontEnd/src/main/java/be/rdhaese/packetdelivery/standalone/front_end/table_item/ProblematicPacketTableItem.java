package be.rdhaese.packetdelivery.standalone.front_end.table_item;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public class ProblematicPacketTableItem {
    private SimpleStringProperty packetId = new SimpleStringProperty();
    private SimpleStringProperty dateMarkedAsProblematic = new SimpleStringProperty();
    private SimpleStringProperty client = new SimpleStringProperty();
    private SimpleStringProperty delivery = new SimpleStringProperty();

    public ProblematicPacketTableItem() {
    }

    public ProblematicPacketTableItem(String packetId, String dateMarkedAsProblematic, String client, String delivery) {
        this.packetId.set(packetId);
        this.dateMarkedAsProblematic.set(dateMarkedAsProblematic);
        this.client.set(client);
        this.delivery.set(delivery);
    }

    public String getPacketId() {
        return packetId.get();
    }

    public SimpleStringProperty packetIdProperty() {
        return packetId;
    }

    public void setPacketId(String packetId) {
        this.packetId.set(packetId);
    }

    public String getDateMarkedAsProblematic() {
        return dateMarkedAsProblematic.get();
    }

    public SimpleStringProperty dateMarkedAsProblematicProperty() {
        return dateMarkedAsProblematic;
    }

    public void setDateMarkedAsProblematic(String dateMarkedAsProblematic) {
        this.dateMarkedAsProblematic.set(dateMarkedAsProblematic);
    }

    public String getClient() {
        return client.get();
    }

    public SimpleStringProperty clientProperty() {
        return client;
    }

    public void setClient(String client) {
        this.client.set(client);
    }

    public String getDelivery() {
        return delivery.get();
    }

    public SimpleStringProperty deliveryProperty() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery.set(delivery);
    }
}
