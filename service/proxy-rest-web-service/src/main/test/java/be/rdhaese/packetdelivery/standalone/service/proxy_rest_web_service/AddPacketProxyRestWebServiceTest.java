package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AddPacketWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service.properties.BackEndProperties;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/**
 *
 * @author Robin D'Haese
 */
public class AddPacketProxyRestWebServiceTest extends AbstractProxyRestWebServiceTest{

    @Autowired
    private AddPacketWebService addPacketWebService;

    @Test
    public void testAddPacket() throws Exception {
        PacketDTO packetDto = new PacketDTO();
        packetDto.setClientName("clientName");

        server.expect(requestTo(getWithServerPath("packet/add")))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.clientName").value("clientName"))
                        .andRespond(MockRestResponseCreators.withSuccess("packetId", MediaType.TEXT_PLAIN));

        assertEquals("packetId", addPacketWebService.addPacket(packetDto));
    }
}
