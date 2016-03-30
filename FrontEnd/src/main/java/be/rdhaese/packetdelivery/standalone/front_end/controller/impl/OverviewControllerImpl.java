package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.controller.abstract_impl.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.OverviewController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 22/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class OverviewControllerImpl extends AbstractWithMenuAndStatusBarController implements OverviewController {
    @FXML
    private Label lblMessage;

    private static String message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (message != null) {
            lblMessage.setText(message);
            lblMessage.setVisible(true);
            message = null;
        }
        super.initialize(location, resources);
    }

    public void addPacket() {
        showScene(getScene(), FXMLS.ADD_PACKET);
    }

    public void editContactInformation() {
        showScene(getScene(), FXMLS.EDIT_CONTACT_INFORMATION);
    }

    public void lostPackets() {
        showScene(getScene(), FXMLS.LOST_PACKETS);
    }

    public void problematicDeliveries() {
        showScene(getScene(), FXMLS.PROBLEMATIC_DELIVERIES);
    }

    private Scene getScene() {
        return lblMessage.getScene();
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        OverviewControllerImpl.message = message;
    }
}
