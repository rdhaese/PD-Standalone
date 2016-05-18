package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.dto.PacketInformationDTO;
import be.rdhaese.packetdelivery.standalone.service.interfaces.PacketInformationWebService;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
public class PacketInformationProxyRestWebServiceTest extends AbstractProxyRestWebServiceTest {

    @Autowired
    private PacketInformationWebService packetInformationWebService;

    @Test
    public void testGetPacketInformation() throws Exception {
        PacketInformationDTO packetInformationDto = new PacketInformationDTO();
        packetInformationDto.setClientName("clientName");

        server.expect(requestTo("http://just-a-url.com/test-service"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(packetInformationDto), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(packetInformationDto, packetInformationWebService.getPacketInformation("http://just-a-url.com/test-service"));
    }

    @Test
    public void testGetPacketInformationException(){
        assertNull(packetInformationWebService.getPacketInformation(null));
    }
}
