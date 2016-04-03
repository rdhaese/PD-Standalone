package be.rdhaese.packetdelivery.standalone.front_end.controller.abstract_impl;

import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 23/12/2015.
 *
 * @author Robin D'Haese
 */
public abstract class AbstractWithMenuAndStatusBarController extends AbstractInitializeableController {

    @FXML
    protected Label lblLoggedInUsername;

    @FXML
    protected ToolBar menuPlaceHolder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuPlaceHolder.getItems().add((Node) App.LOADER.load(FXMLS.MENU_BAR.toString()));
        lblLoggedInUsername.setText(authenticationService.getLoggedInUser());
    }


}