package be.rdhaese.packetdelivery.standalone.front_end.controller;

/**
 * Created on 2/01/2016.
 *
 * @author Robin D'Haese
 */
public interface AddPacketController extends CancelableController {

    void addPacket();
    void informationFromURL();
}