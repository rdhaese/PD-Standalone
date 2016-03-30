package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.loader.SpringFxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 23/12/2015.
 *
 * @author Robin D'Haese
 */
public abstract class AbstractWithMenuAndStatusBarController extends AbstractInitializeableController {

    @Autowired
    private SpringFxmlLoader loader;

    @FXML
    protected Label lblLoggedInUsername;

    @FXML
    protected ToolBar menuPlaceHolder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuPlaceHolder.getItems().add((Node) loader.load(FXMLS.MENU_BAR.toString()));
        lblLoggedInUsername.setText(authenticationService.getLoggedInUser());
    }


}