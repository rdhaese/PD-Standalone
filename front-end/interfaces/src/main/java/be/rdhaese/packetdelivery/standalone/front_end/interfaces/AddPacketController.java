package be.rdhaese.packetdelivery.standalone.front_end.interfaces;

/**
 *
 * @author Robin D'Haese
 */
public interface AddPacketController extends CancelableController, UpdatableController {

    void addPacket();
    void informationFromURL();
}