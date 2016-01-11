package be.rdhaese.packetdelivery.standalone.front_end.controller;

import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.front_end.controller.impl.OverviewControllerImpl;
import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import be.rdhaese.packetdelivery.standalone.service.ContactInformationService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 23/12/2015.
 *
 * @author Robin D'Haese
 */
public abstract class AbstractWithMenuAndStatusBarController extends AbstractInitializableController {

    @FXML
    protected Label lblLoggedInUsername;

    @FXML
    protected ToolBar menuPlaceHolder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuPlaceHolder.getItems().add((Node) App.LOADER.load("menubar"));
        lblLoggedInUsername.setText(authenticationService.getLoggedInUser());
    }

    protected void showOverview(Scene oldScene, String message) {
        if (message != null) {
            OverviewControllerImpl.setMessage(message);
        }
        Parent parent = (Parent) App.LOADER.load("overview");
        ((Stage) oldScene.getWindow()).setScene(new Scene(parent, 800, 800));
    }

    protected boolean isEmpty(TextField textField) {
        return textField.getText().trim().isEmpty();
    }

    protected void markForError(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (!styleClass.contains("tferror")) {
            styleClass.add("tferror");
        }
    }

    protected void removeErrorStyleIfNeeded(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (styleClass.contains("tferror")) {
            styleClass.remove("tferror");
        }
    }
}
