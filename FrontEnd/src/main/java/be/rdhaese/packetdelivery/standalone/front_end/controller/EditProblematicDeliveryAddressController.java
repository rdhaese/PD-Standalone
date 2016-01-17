package be.rdhaese.packetdelivery.standalone.front_end.controller;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public interface EditProblematicDeliveryAddressController extends CancelableController {
    void setCurrentPacket(String packetId);
    void save();
}
