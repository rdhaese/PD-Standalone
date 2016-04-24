package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;


import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService;
import be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service.AuthenticationWebServiceExtended;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 9/01/2016.
 *
 * @author Robin D'Haese
 */
public abstract class AbstractInitializeableController extends AbstractController implements Initializable {

    @Autowired
    protected AuthenticationWebServiceExtended authenticationService;
    @Autowired
    protected ContactInformationWebService contactInformationService;
}
