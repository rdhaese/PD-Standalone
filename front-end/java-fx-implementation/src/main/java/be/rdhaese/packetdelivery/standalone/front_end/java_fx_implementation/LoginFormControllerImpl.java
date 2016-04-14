package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;


import be.rdhaese.packetdelivery.standalone.front_end.interfaces.LoginFormController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 21/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class LoginFormControllerImpl extends AbstractInitializeableController implements LoginFormController {

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
        clearErrors();
        if (noEmptyFields()) {
            switch (authenticationService.authenticate(txtUsername.getText(), txtPassword.getText())){
                case "GRANTED":
                    showOverview(lblErrorMessage.getScene(), null);
                    break;
                case "WRONG_PASSWORD":
                    markForError(txtPassword, "login.tooltip.password");
                    showErrorMessage("login.wrongPassword");
                    break;
                case "NOT_KNOWN":
                    markForError(txtUsername);
                    showErrorMessage("login.unknownUsername");
                    break;
            }
        } else {
            showErrorMessage("login.emptyFields");
        }
    }

    private void clearErrors() {
        lblErrorMessage.setVisible(false);
        removeErrorStyleIfNeeded(txtUsername);
        removeErrorStyleIfNeeded(txtPassword);
    }

    private void showErrorMessage(String messageKey){
        lblErrorMessage.setText(getMessage(messageKey));
        lblErrorMessage.setVisible(true);
    }

    private boolean noEmptyFields() {
        return !isEmpty(txtUsername) & !isEmpty(txtPassword);
    }

    @Override
    protected boolean isEmpty(TextInputControl control){
        if (super.isEmpty(control)){
            markForError(control);
            return true;
        }
        return false;
    }
}
