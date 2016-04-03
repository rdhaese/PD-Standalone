package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

<<<<<<< HEAD:front-end/java-fx-implementation/src/main/java/be/rdhaese/packetdelivery/standalone/front_end/java_fx_implementation/OverviewControllerImpl.java

import be.rdhaese.packetdelivery.standalone.front_end.interfaces.OverviewController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
=======
import be.rdhaese.packetdelivery.standalone.front_end.controller.abstract_impl.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.OverviewController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
>>>>>>> 7362763c2cab397bd2f065faa67fbe81a313c24b:FrontEnd/src/main/java/be/rdhaese/packetdelivery/standalone/front_end/controller/impl/OverviewControllerImpl.java
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
