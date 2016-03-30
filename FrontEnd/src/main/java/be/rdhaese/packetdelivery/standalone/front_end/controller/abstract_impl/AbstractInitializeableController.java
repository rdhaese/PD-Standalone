package be.rdhaese.packetdelivery.standalone.front_end.controller.abstract_impl;

import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import be.rdhaese.packetdelivery.standalone.service.ContactInformationService;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 9/01/2016.
 *
 * @author Robin D'Haese
 */
public abstract class AbstractInitializeableController extends AbstractController implements Initializable {
    @Autowired
    protected AuthenticationService authenticationService;
    @Autowired
    protected ContactInformationService contactInformationService;
}
