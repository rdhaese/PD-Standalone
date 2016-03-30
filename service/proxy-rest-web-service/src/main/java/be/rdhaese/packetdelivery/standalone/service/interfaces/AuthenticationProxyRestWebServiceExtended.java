package be.rdhaese.packetdelivery.standalone.service.interfaces;


import org.springframework.stereotype.Service;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticationProxyRestWebServiceExtended extends AbstractService implements AuthenticationWebServiceExtended {
    private String loggedInUser = null;

    public String authenticate(String username, String password) {
        String authenticationResponse = getNewRestTemplate().getForObject(getUris().getAuthenticatePath(), String.class, username, password);
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
