package be.rdhaese.packetdelivery.standalone.front_end.controller;

import be.rdhaese.packetdelivery.standalone.front_end.App;
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
public class OverviewController extends AbstractController{
    @FXML
    private Label lblMessage;

    private static String message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize: " + message);
        if (message != null){
            lblMessage.setText(message);
            lblMessage.setVisible(true);
            message = null;
        }
        super.initialize(location, resources);
    }

    public void addPacket(){
        Stage stage = (Stage) lblLoggedInUsername.getScene().getWindow();
        Parent parent = (Parent) App.LOADER.load("add-packet");
        Scene scene = new Scene(parent, 800, 800);
        stage.setScene(scene);
    }

    public void editContactInformation(){
        Stage stage = (Stage) lblLoggedInUsername.getScene().getWindow();
        Parent parent = (Parent) App.LOADER.load("edit-contact-information");
        stage.setScene(new Scene(parent, 800, 800));
    }

    public void lostPackets(){
        Stage stage = (Stage) lblLoggedInUsername.getScene().getWindow();
        Parent parent = (Parent) App.LOADER.load("lost-packets");
        stage.setScene(new Scene(parent, 800, 800));
    }

    public void problematicDeliveries(){
        Stage stage = (Stage) lblLoggedInUsername.getScene().getWindow();
        Parent parent = (Parent) App.LOADER.load("problematic-deliveries");
        stage.setScene(new Scene(parent, 800, 800));
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        OverviewController.message = message;
    }
}
