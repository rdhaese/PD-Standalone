package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;


import be.rdhaese.packetdelivery.standalone.service.interfaces.AuthenticationWebServiceExtended;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticationProxyRestWebServiceExtended extends AbstractService implements AuthenticationWebServiceExtended {

    private String loggedInUser = null;

    public String authenticate(String username, String password) {
        String authenticationResponse = getRestTemplate().postForObject(getBackEndProperties().getUris().getAuthenticate(), password, String.class, username);
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
