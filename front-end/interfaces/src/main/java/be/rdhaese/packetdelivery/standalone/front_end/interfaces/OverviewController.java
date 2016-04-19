package be.rdhaese.packetdelivery.standalone.front_end.interfaces;

/**
 * Created on 2/01/2016.
 *
 * @author Robin D'Haese
 */
public interface OverviewController extends UpdatableController{
    void addPacket();
    void editContactInformation();
    void lostPackets();
    void problematicDeliveries();
}
