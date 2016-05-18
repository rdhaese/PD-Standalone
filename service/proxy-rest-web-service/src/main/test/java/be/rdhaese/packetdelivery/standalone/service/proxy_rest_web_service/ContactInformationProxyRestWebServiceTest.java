package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
public class ContactInformationProxyRestWebServiceTest extends AbstractProxyRestWebServiceTest {

    @Autowired
    private ContactInformationWebService contactInformationWebService;

    @Test
    public void testGet() throws Exception {
        ContactDetailsDTO contactDetailsDTO = new ContactDetailsDTO();
        contactDetailsDTO.setCompanyName("companyName");

        server.expect(requestTo(getWithServerPath("contact-information/get")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(contactDetailsDTO), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(contactDetailsDTO, contactInformationWebService.get());
    }

    @Test
    public void testPost() throws Exception {
        ContactDetailsDTO contactDetailsDTO = new ContactDetailsDTO();
        contactDetailsDTO.setCompanyName("companyName");

        server.expect(requestTo(getWithServerPath("contact-information/post")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.companyName").value("companyName"))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON_UTF8));

        assertTrue(contactInformationWebService.post(contactDetailsDTO));
    }

    @Test
    public void testGetCompanyName() throws Exception {
        server.expect(requestTo(getWithServerPath("contact-information/company-name")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("companyName", MediaType.TEXT_PLAIN));

        assertEquals("companyName", contactInformationWebService.getCompanyName());
    }
}
