package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.front_end.controller.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.OverviewController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
        if (message != null){
            lblMessage.setText(message);
            lblMessage.setVisible(true);
            message = null;
        }
        super.initialize(location, resources);
    }

    public void addPacket(){
        Stage stage = (Stage) lblLoggedInUsername.getScene().getWindow();
        Parent parent = (Parent) App.LOADER.load(FXMLS.ADD_PACKET.toString());
        Scene scene = new Scene(parent, 800, 800);
        stage.setScene(scene);
    }

    public void editContactInformation(){
        Stage stage = (Stage) lblLoggedInUsername.getScene().getWindow();
        Parent parent = (Parent) App.LOADER.load(FXMLS.EDIT_CONTACT_INFORMATION.toString());
        stage.setScene(new Scene(parent, 800, 800));
    }

    public void lostPackets(){
        Stage stage = (Stage) lblLoggedInUsername.getScene().getWindow();
        Parent parent = (Parent) App.LOADER.load(FXMLS.LOST_PACKETS.toString());
        stage.setScene(new Scene(parent, 800, 800));
    }

    public void problematicDeliveries(){
        Stage stage = (Stage) lblLoggedInUsername.getScene().getWindow();
        Parent parent = (Parent) App.LOADER.load(FXMLS.PROBLEMATIC_DELIVERIES.toString());
        stage.setScene(new Scene(parent, 800, 800));
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        OverviewControllerImpl.message = message;
    }
}
