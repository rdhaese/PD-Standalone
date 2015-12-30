package be.rdhaese.packetdelivery.standalone.service.Impl;

import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String URI = "http://localhost:8080/authenticate/?username={username}&password={password}";

    private String loggedInUser = null;

    public boolean authenticate(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        boolean isAuthenticated = false;
        if (isAuthenticated = restTemplate.getForObject(URI, boolean.class, username, password)) {
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
