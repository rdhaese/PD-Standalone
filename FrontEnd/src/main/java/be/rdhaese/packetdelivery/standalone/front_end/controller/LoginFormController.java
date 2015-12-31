package be.rdhaese.packetdelivery.standalone.front_end.controller;


import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created on 21/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class LoginFormController {

    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
    Label errorMessage;

    @Autowired
    private AuthenticationService authenticationService;


    public void authenticate() throws IOException {
        if(noEmptyFields()){
            if (authenticationService.authenticate(txtUsername.getText(), txtUsername.getText())){
                errorMessage.setVisible(false);
                Stage stage = (Stage) txtUsername.getScene().getWindow();
                Parent parent = (Parent) App.LOADER.load("overview");
                stage.setScene(new Scene(parent, 800, 800));
            } else {
                showUnableToAuthenticateNotification();
            }
        } else {
            errorMessage.setText("Please fill in all fields.");
            errorMessage.setVisible(true);
        }
    }

    private void showUnableToAuthenticateNotification() {
        errorMessage.setText("Unable to authenticate, please check your credentials");
        errorMessage.setVisible(true);
    }

    private boolean noEmptyFields() {
        return !isEmpty(txtUsername) & !isEmpty(txtPassword);
    }

    private boolean isEmpty(TextField control){
        if (control.getText().trim().isEmpty()){
            markForError(control);
            return true;
        }
        removeErrorStyleIfNeeded(control);
        return false;
    }

    private void markForError(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (!styleClass.contains("tferror")) {
            styleClass.add("tferror");
        }
    }

    private void removeErrorStyleIfNeeded(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (styleClass.contains("tferror")) {
            styleClass.remove("tferror");
        }
    }
}
