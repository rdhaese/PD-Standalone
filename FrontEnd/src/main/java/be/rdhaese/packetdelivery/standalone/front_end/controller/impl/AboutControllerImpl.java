package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.controller.AboutController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

/**
 * Created on 22/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class AboutControllerImpl implements AboutController {

    @FXML
    private Button btnClose;

    public void close(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
