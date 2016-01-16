package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.controller.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.ProblematicDeliveryController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 14/01/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class ProblematicDeliveryControllerImpl extends AbstractWithMenuAndStatusBarController implements ProblematicDeliveryController {

    private static String currentPacket;

    @FXML
    private Label lblCurrentPacket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if ((currentPacket == null) || ((currentPacket = currentPacket.trim()).isEmpty())){
            showOverview(lblLoggedInUsername.getScene(), getMessage("toolbar.message.noProblematicPacketToViewDetailsFrom"));
        }else {
            //TODO
            lblCurrentPacket.setText(currentPacket);
            super.initialize(location, resources);
        }
    }

    @Override
    public void cancel() {
        showScene(lblLoggedInUsername.getScene(), FXMLS.PROBLEMATIC_DELIVERIES);
    }

    public static void setCurrentPacket(String currentPacket) {
        ProblematicDeliveryControllerImpl.currentPacket = currentPacket;
    }
}
