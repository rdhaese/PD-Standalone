package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.OptionsWebService;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import be.rdhaese.packetdelivery.dto.OptionsDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
public class OptionsProxyRestWebServiceTest extends AbstractProxyRestWebServiceTest {

    @Autowired
    private OptionsWebService optionsWebService;

    @Test
    public void testGetForUsername() throws Exception {
        OptionsDTO optionsDTO = new OptionsDTO("user", "lang", 1, true);

        server.expect(requestTo(getWithServerPath("options/user")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(optionsDTO), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(optionsDTO, optionsWebService.getFor("user"));
    }

    @Test
    public void testSave() throws Exception {
        OptionsDTO optionsDTO = new OptionsDTO("user", "lang", 1, true);

        server.expect(requestTo(getWithServerPath("options/save")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.user").value("user"))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON_UTF8));

        assertTrue(optionsWebService.save(optionsDTO));
    }
}
