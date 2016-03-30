package be.rdhaese.packetdelivery.standalone.service.interfaces;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AuthenticationWebService;

/**
 * Created on 28/03/2016.
 *
 * @author Robin D'Haese
 */
public interface AuthenticationWebServiceExtended extends AuthenticationWebService {
    void logout();

    boolean isLoggedIn();

    String getLoggedInUser();
}
