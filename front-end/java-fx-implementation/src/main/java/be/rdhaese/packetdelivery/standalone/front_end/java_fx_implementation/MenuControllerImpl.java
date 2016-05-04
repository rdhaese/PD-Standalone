package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;


import be.rdhaese.packetdelivery.standalone.front_end.interfaces.MenuController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.alert.AlertTool;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.service.interfaces.AuthenticationWebServiceExtended;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

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
    private AuthenticationWebServiceExtended authenticationService;

    @Autowired
    private AlertTool alertTool;

    public void options() {
        showInNewWindow(FXMLS.OPTIONS, "options.title", 550, 310, false);
    }

    public void about() {
        showInNewWindow(FXMLS.ABOUT, "about.title", 240, 290, false);
    }

    public void logout() {
        Optional<ButtonType> result = alertTool.getAlertUsingMessageSource(
                Alert.AlertType.CONFIRMATION,
                "logoutDialog.title",
                "logoutDialog.header",
                "logoutDialog.content")
                .showAndWait();

        if (result.get() == ButtonType.OK) {
            authenticationService.logout();
            showScene(menuBar.getScene(), FXMLS.LOGIN_FORM);
        }
    }

    public void exit() {
        Optional<ButtonType> result = alertTool.getAlertUsingMessageSource(
                Alert.AlertType.CONFIRMATION,
                "exitDialog.title",
                "exitDialog.header",
                "exitDialog.content")
                .showAndWait();

        if (result.get() == ButtonType.OK) {
            authenticationService.logout();
            Platform.exit();
        }
    }
}
