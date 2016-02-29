package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;


import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.front_end.controller.AbstractInitializableController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.LoginFormController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import be.rdhaese.packetdelivery.standalone.service.ContactInformationService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 21/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class LoginFormControllerImpl extends AbstractInitializableController implements LoginFormController {

    @FXML
    Label lblCompanyName;
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
    Label lblErrorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String companyName = contactInformationService.getCompanyName();
        lblCompanyName.setText(companyName);
    }

    public void authenticate() {
        if (noEmptyFields()) {
            if (authenticationService.authenticate(txtUsername.getText(), txtUsername.getText())) {
                lblErrorMessage.setVisible(false);
                showOverview(lblErrorMessage.getScene(), null);
            } else {
                markForError(txtUsername);
                markForError(txtPassword);
                showUnableToAuthenticateNotification();
            }
        } else {
            lblErrorMessage.setText(getMessage("login.emptyFields"));
            lblErrorMessage.setVisible(true);
        }
    }

    private void showUnableToAuthenticateNotification() {
        lblErrorMessage.setText(getMessage("login.unableToAuthenticate"));
        lblErrorMessage.setVisible(true);
    }

    private boolean noEmptyFields() {
        return !isEmpty(txtUsername) & !isEmpty(txtPassword);
    }
}
