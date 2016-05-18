package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.standalone.service.interfaces.AuthenticationWebServiceExtended;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
public class AuthenticationProxyRestWebServiceExtendedTest extends AbstractProxyRestWebServiceTest {

    @Autowired
    private AuthenticationWebServiceExtended authenticationWebServiceExtended;

    @Test
    public void testAuthenticate(){
        assertFalse(authenticationWebServiceExtended.isLoggedIn());

        server.expect(requestTo(getWithServerPath("authenticate/username")))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andExpect(content().string("password"))
                .andRespond(MockRestResponseCreators.withSuccess("GRANTED", MediaType.TEXT_PLAIN));

        assertEquals("GRANTED", authenticationWebServiceExtended.authenticate("username", "password"));

        //Is logged in user kept?
        assertEquals("username", authenticationWebServiceExtended.getLoggedInUser());
        assertTrue(authenticationWebServiceExtended.isLoggedIn());

        //Can log out?
        authenticationWebServiceExtended.logout();
        assertFalse(authenticationWebServiceExtended.isLoggedIn());
    }
}
