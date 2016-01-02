package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.controller.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.FromUrlController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 24/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class FromUrlControllerImpl extends AbstractWithMenuAndStatusBarController implements FromUrlController {

    @FXML
    private TextField txtUrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Do nothing
        //Super wants to initialize a menu and status bar, other functions are similar
    }

    public void loadInformation(){
        if (!isEmpty(txtUrl)){
            //TODO get information from standalone_service
            //TODO map to form in other scene
        } else {
            //TODO mark field for error
        }
        //Do nothing
    }

    public void cancel(){
        Stage stage = (Stage) txtUrl.getScene().getWindow();
        stage.close();
    }
}
