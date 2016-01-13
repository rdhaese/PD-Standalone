package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.front_end.controller.AbstractController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.MenuController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
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
public class MenuControllerImpl extends AbstractController implements MenuController {

    @FXML
    private MenuBar menuBar;

    @Autowired
    private AuthenticationService authenticationService;

    public void about(){
        Parent root = (Parent) App.LOADER.load(FXMLS.ABOUT.toString());
        Stage stage = new Stage();
        stage.setTitle(getMessage("about.title"));
        stage.setScene(new Scene(root, 220, 270));
        stage.setResizable(false);
        stage.show();
    }

    public void logout(){
        authenticationService.logout();
        Stage stage = (Stage) menuBar.getScene().getWindow();
        Parent parent = (Parent) App.LOADER.load(FXMLS.LOGIN_FORM.toString());
        stage.setScene(new Scene(parent, 800, 800));
    }

    public void exit(){
        authenticationService.logout();
        Platform.exit();
    }
}
