package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.front_end.controller.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.ProblematicDeliveriesController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 14/01/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class ProblematicDeliveriesControllerImpl extends AbstractWithMenuAndStatusBarController implements ProblematicDeliveriesController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO

        super.initialize(location, resources);
    }

    @Override
    public void cancel() {
        showOverview(lblLoggedInUsername.getScene(), null);
    }
}
