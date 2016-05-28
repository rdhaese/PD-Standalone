package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;


import be.rdhaese.packetdelivery.standalone.front_end.interfaces.OverviewController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Robin D'Haese
 */
@Controller
public class OverviewControllerImpl extends AbstractWithMenuAndStatusBarController implements OverviewController {

    @FXML
    private Button btnAddPacket;

    @FXML
    private Button btnLostPackets;

    @FXML
    private Button btnEditContactInformation;

    @FXML
    private Button btnProblematicDeliveries;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        //Set key event for characters
        btnAddPacket.getParent().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case A:
                        btnAddPacket.fire();
                        break;
                    case L:
                        btnLostPackets.fire();
                        break;
                    case E:
                        btnEditContactInformation.fire();
                        break;
                    case P:
                        btnProblematicDeliveries.fire();
                }
            }
        });
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
        return btnAddPacket.getScene();
    }

    @Override
    public void update(){
        showScene(getScene(), FXMLS.OVERVIEW);
    }
}
