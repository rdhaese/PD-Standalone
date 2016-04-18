package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;


import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.OptionsWebService;
import be.rdhaese.packetdelivery.dto.OptionsDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.LoginFormController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created on 21/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class LoginFormControllerImpl extends AbstractInitializeableController implements LoginFormController {

    @Autowired
    private OptionsWebService optionsService;

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
        if (isInputValid()) {
            switch (authenticationService.authenticate(txtUsername.getText(), txtPassword.getText())){
                case "GRANTED":
                    //First load options for user
                    OptionsDTO optionsDTO = optionsService.getFor(txtUsername.getText());
                    if (OptionsWebService.NL.equals(optionsDTO.getLanguage())){
                        Locale.setDefault(Locale.forLanguageTag(OptionsWebService.TAG_NL));
                    } else if (OptionsWebService.FR.equals(optionsDTO.getLanguage())){
                        Locale.setDefault(Locale.FRENCH);
                    } else if (OptionsWebService.DE.equals(optionsDTO.getLanguage())){
                        Locale.setDefault(Locale.GERMAN);
                    } else {
                        Locale.setDefault(Locale.ENGLISH);
                    }
                    System.setProperty("print", optionsDTO.getPrint().toString());
                    System.setProperty("imageViewer", optionsDTO.getImageViewer().toString());
                    //Show the overview
                    showOverview(lblErrorMessage.getScene(), null);
                    break;
                case "WRONG_PASSWORD":
                    markForError(txtPassword, "login.tooltip.password");
                    showErrorMessage("login.wrongPassword");
                    break;
                case "NOT_KNOWN":
                    markForError(txtUsername, "login.tooltip.username");
                    showErrorMessage("login.unknownUsername");
                    break;
            }
        } else {
            showErrorMessage("login.invalidFields");
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

    private Boolean isInputValid() {
        return validateUsername() & validatePassword();
    }

    private Boolean validateUsername() {
        if (validator.isValidUserName(txtUsername.getText())){
            return true;
        }
        markForError(txtUsername);
        return false;
    }

    private Boolean validatePassword() {
        if (validator.isValidPassWord(txtPassword.getText())){
            return true;
        }
        markForError(txtPassword);
        return false;
    }

    @Override
    protected boolean hasValidInput(TextInputControl control){
        if (super.hasValidInput(control)){
            markForError(control);
            return true;
        }
        return false;
    }
}
