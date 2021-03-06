package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.table_item;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Robin D'Haese
 */
public class LostPacketTableItem {
    private SimpleStringProperty packetId = new SimpleStringProperty();
    private SimpleStringProperty dateMarkedAsLost = new SimpleStringProperty();
    private SimpleStringProperty client = new SimpleStringProperty();
    private SimpleStringProperty delivery = new SimpleStringProperty();
    private SimpleBooleanProperty found = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty remove = new SimpleBooleanProperty(false);

    public LostPacketTableItem() {
        this.found.addListener(cl -> {if (this.found.getValue().equals(true)){this.remove.set(false);}});
        this.remove.addListener(cl -> {if (this.remove.getValue().equals(true)){this.found.set(false);}});
    }

    public LostPacketTableItem(String packetId, String dateMarkedAsLost, String client, String delivery, boolean found, boolean remove) {
        this();
        this.packetId.set(packetId);
        this.dateMarkedAsLost.set(dateMarkedAsLost);
        this.client.set(client);
        this.delivery.set(delivery);
        this.found.set(found);
        this.remove.set(remove);
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

    public String getDateMarkedAsLost() {
        return dateMarkedAsLost.get();
    }

    public SimpleStringProperty dateMarkedAsLostProperty() {
        return dateMarkedAsLost;
    }

    public void setDateMarkedAsLost(String dateMarkedAsLost) {
        this.dateMarkedAsLost.set(dateMarkedAsLost);
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

    public boolean getFound() {
        return found.get();
    }

    public SimpleBooleanProperty foundProperty() {
        return found;
    }

    public void setFound(boolean found) {
        this.found.set(found);
    }

    public boolean getRemove() {
        return remove.get();
    }

    public SimpleBooleanProperty removeProperty() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove.set(remove);
    }
}
