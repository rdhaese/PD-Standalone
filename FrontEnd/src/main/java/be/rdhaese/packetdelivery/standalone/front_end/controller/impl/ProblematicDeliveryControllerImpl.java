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
        showScene(lblLoggedInUsername.getScene(), FXMLS.PROBLEMATIC_DELIVERIES);
    }
}
