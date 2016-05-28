package be.rdhaese.packetdelivery.standalone.front_end.interfaces;

/**
 *
 * @author Robin D'Haese
 */
public interface EditProblematicDeliveryAddressController extends SaveableController, CancelableController {
    void setCurrentPacket(String packetId);
}
