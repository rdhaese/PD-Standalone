package be.rdhaese.packetdelivery.standalone.front_end.interfaces;

/**
 *
 * @author Robin D'Haese
 */
public interface ProblematicDeliveryController extends CancelableController, UpdatableController{

    void setCurrentPacket(String packetId);
    void editAddress();
    void reSend();
    void returnToSender();
}
