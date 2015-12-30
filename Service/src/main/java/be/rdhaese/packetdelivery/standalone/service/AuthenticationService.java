package be.rdhaese.packetdelivery.standalone.service;

import org.springframework.stereotype.Service;

/**
 * Created on 21/12/2015.
 *
 * @author Robin D'Haese
 */
public interface AuthenticationService {
    boolean authenticate(String username, String password);

    void logout();

    boolean isLoggedIn();

    String getLoggedInUser();
}
