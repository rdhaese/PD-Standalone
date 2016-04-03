package be.rdhaese.packetdelivery.standalone.service.Impl;

import be.rdhaese.packetdelivery.standalone.service.AbstractService;
import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
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

    public String authenticate(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String authenticationResponse = restTemplate.getForObject(getUris().getAuthenticatePath(), String.class, username, password);
        if ("GRANTED".equals(authenticationResponse)) {
            loggedInUser = username;
        }
        return authenticationResponse;
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
