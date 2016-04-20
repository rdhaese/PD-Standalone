package be.rdhaese.packetdelivery.standalone.service.interfaces;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticationProxyRestWebServiceExtended extends AbstractService implements AuthenticationWebServiceExtended {

    private String loggedInUser = null;

    public String authenticate(String username, String password) {
        String authenticationResponse = getRestTemplate().postForObject(getUris().getAuthenticatePath(), password, String.class, username);
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
