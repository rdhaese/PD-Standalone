package be.rdhaese.packetdelivery.standalone.service.interfaces;


import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AuthenticationWebService;

/**
 *
 * @author Robin D'Haese
 */
public interface AuthenticationWebServiceExtended extends AuthenticationWebService {
    void logout();

    boolean isLoggedIn();

    String getLoggedInUser();
}
