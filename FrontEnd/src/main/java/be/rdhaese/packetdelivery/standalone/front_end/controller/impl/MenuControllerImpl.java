package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.controller.abstract_impl.AbstractController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.MenuController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
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

    public void options(){
        showInNewWindow(FXMLS.OPTIONS, "options.title", 550,310, false);
    }

    public void about(){
        showInNewWindow(FXMLS.ABOUT, "about.title", 240, 290, false);
    }

    public void logout(){
        //TODO ask if user is sure
        authenticationService.logout();
        showScene(menuBar.getScene(), FXMLS.LOGIN_FORM);
    }

    public void exit(){
        //TODO ask if user is sure
        authenticationService.logout();
        Platform.exit();
    }
}
