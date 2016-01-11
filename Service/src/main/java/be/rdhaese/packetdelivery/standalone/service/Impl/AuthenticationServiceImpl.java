package be.rdhaese.packetdelivery.standalone.service.Impl;

import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticationServiceImpl extends AbstractService implements AuthenticationService {
    private String loggedInUser = null;

    public boolean authenticate(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        boolean isAuthenticated;
        if (isAuthenticated = restTemplate.getForObject(getUris().getAuthenticatePath(), boolean.class, username, password)) {
            loggedInUser = username;
        }
        return isAuthenticated;
    }

    public void logout() {
        loggedInUser = null;
    }

    public boolean isLoggedIn() {
        return (loggedInUser == null) ? false : true;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }
}
