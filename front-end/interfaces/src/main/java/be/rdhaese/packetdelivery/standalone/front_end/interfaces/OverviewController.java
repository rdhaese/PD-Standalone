package be.rdhaese.packetdelivery.standalone.front_end.interfaces;

/**
 *
 * @author Robin D'Haese
 */
public interface OverviewController extends UpdatableController{
    void addPacket();
    void editContactInformation();
    void lostPackets();
    void problematicDeliveries();
}
