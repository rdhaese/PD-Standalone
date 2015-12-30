package be.rdhaese.packetdelivery.standalone.front_end.controller;

import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created on 22/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class MenuController {

    @FXML
    private MenuBar menuBar;

    @Autowired
    private AuthenticationService authenticationService;

    public void about(){
        Parent root = (Parent) App.LOADER.load("about");
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setScene(new Scene(root, 200, 450));
        stage.show();
    }

    public void logout(){
        authenticationService.logout();
        Stage stage = (Stage) menuBar.getScene().getWindow();
        Parent parent = (Parent) App.LOADER.load("login-form");
        stage.setScene(new Scene(parent, 800, 800));
    }

    public void exit(){
        authenticationService.logout();
        Platform.exit();
    }
}
