package be.rdhaese.packetdelivery.standalone.front_end.controller;

import be.rdhaese.packetdelivery.standalone.front_end.App;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Created on 2/01/2016.
 *
 * @author Robin D'Haese
 */
public interface OverviewController {
    void addPacket();
    void editContactInformation();
    void lostPackets();
    void problematicDeliveries();
}
