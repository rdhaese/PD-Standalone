package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;


import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService;
import be.rdhaese.packetdelivery.standalone.service.interfaces.AuthenticationWebServiceExtended;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Robin D'Haese
 */
public abstract class AbstractInitializeableController extends AbstractController implements Initializable {

    @Autowired
    protected AuthenticationWebServiceExtended authenticationService;
    @Autowired
    protected ContactInformationWebService contactInformationService;
}
