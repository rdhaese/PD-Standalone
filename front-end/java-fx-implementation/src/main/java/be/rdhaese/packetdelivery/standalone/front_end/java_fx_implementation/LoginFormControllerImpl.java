package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;


import be.rdhaese.packetdelivery.back_end.web_service.interfaces.OptionsWebService;
import be.rdhaese.packetdelivery.dto.OptionsDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.LoginFormController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.context.Theme;

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

    public static final String AUTHENTICATION_RESULT_GRANTED = "GRANTED";
    public static final String AUTHENTICATION_RESULT_WRONG_PASSWORD = "WRONG_PASSWORD";
    public static final String AUTHENTICATION_RESULT_NOT_KNOWN = "NOT_KNOWN";
    public static final String PROPERTY_KEY_PRINT = "print";
    public static final String PROPERTY_KEY_IMAGE_VIEWER = "imageViewer";

    @Autowired
    private OptionsWebService optionsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        String companyName = null;
        try {
            companyName = contactInformationService.getCompanyName();
        } catch (Exception e) {
            //Do nothing, exception should already be logged and handled by aspects
            //Throw a runtime exception so this exception is not swallowed if for some reason the aspects are not working
            throw new RuntimeException(e);
        }
        lblCompanyName.setText(companyName);
    }

    public void authenticate() throws Exception{
        clearErrors();
        if (isInputValid()) {
            String encryptedPassword = passwordEncoder.encode(txtPassword.getText());
            switch (authenticationService.authenticate(txtUsername.getText(), encryptedPassword)) {
                case AUTHENTICATION_RESULT_GRANTED:
                    //First load options for user
                    OptionsDTO optionsDTO = optionsService.getFor(txtUsername.getText());
                    if (OptionsWebService.NL.equals(optionsDTO.getLanguage())) {
                        Locale.setDefault(Locale.forLanguageTag(OptionsWebService.TAG_NL));
                    } else if (OptionsWebService.FR.equals(optionsDTO.getLanguage())) {
                        Locale.setDefault(Locale.FRENCH);
                    } else if (OptionsWebService.DE.equals(optionsDTO.getLanguage())) {
                        Locale.setDefault(Locale.GERMAN);
                    } else {
                        Locale.setDefault(Locale.ENGLISH);
                    }
                    System.setProperty(PROPERTY_KEY_PRINT, optionsDTO.getPrint().toString());
                    System.setProperty(PROPERTY_KEY_IMAGE_VIEWER, optionsDTO.getImageViewer().toString());
                    //Show the overview
                    showOverview(lblErrorMessage.getScene(), null);
                    break;
                case AUTHENTICATION_RESULT_WRONG_PASSWORD:
                    markForError(txtPassword, "login.tooltip.password");
                    showErrorMessage("login.wrongPassword");
                    break;
                case AUTHENTICATION_RESULT_NOT_KNOWN:
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

    private void showErrorMessage(String messageKey) {
        lblErrorMessage.setText(getMessage(messageKey));
        lblErrorMessage.setVisible(true);
    }

    private Boolean isInputValid() {
        return validateUsername() & validatePassword();
    }

    private Boolean validateUsername() {
        if (validator.isValidUserName(txtUsername.getText())) {
            return true;
        }
        markForError(txtUsername);
        return false;
    }

    private Boolean validatePassword() {
        if (validator.isValidPassWord(txtPassword.getText())) {
            return true;
        }
        markForError(txtPassword);
        return false;
    }

    @Override
    protected boolean hasValidInput(TextInputControl control) {
        if (super.hasValidInput(control)) {
            markForError(control);
            return true;
        }
        return false;
    }
}
