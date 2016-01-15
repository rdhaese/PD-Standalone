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
        showInNewWindow(FXMLS.ABOUT, "about.title", 220, 270, false);
    }

    public void logout(){
        authenticationService.logout();
        showScene(menuBar.getScene(), FXMLS.LOGIN_FORM);
    }

    public void exit(){
        authenticationService.logout();
        Platform.exit();
    }
}
