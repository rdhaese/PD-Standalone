package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.front_end.controller.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.ProblematicDeliveryController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

/**
 * Created on 14/01/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class ProblematicDeliveryControllerImpl extends AbstractWithMenuAndStatusBarController implements ProblematicDeliveryController {


    @Override
    public void cancel() {
        Stage stage = (Stage) lblLoggedInUsername.getScene().getWindow();
        Parent root = (Parent) App.LOADER.load(FXMLS.PROBLEMATIC_DELIVERIES.toString());
        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }
}
