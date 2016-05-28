package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collection;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 *
 * @author Robin D'Haese
 */
public class LostPacketProxyRestWebServiceTest extends AbstractProxyRestWebServiceTest {

    @Autowired
    private LostPacketsWebService lostPacketsWebService;

    @Test
    public void testGetLostPackets() throws Exception {
        PacketDTO packetDto1 = new PacketDTO();
        packetDto1.setPacketId("packetId1");
        PacketDTO packetDto2 = new PacketDTO();
        packetDto2.setPacketId("packetId2");
        Collection<PacketDTO> packetDTOs = Arrays.asList(packetDto1, packetDto2);

        server.expect(requestTo(getWithServerPath("lost-packets/all")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(packetDTOs), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(2, lostPacketsWebService.getLostPackets().size());
    }

    @Test
    public void testMarkAsFound(){
        Collection<String> foundPackets = Arrays.asList("packetId1", "packetId2");

        server.expect(requestTo(getWithServerPath("lost-packets/mark-as-found")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0]").value("packetId1"))
                .andExpect(jsonPath("$.[1]").value("packetId2"))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON_UTF8));

        assertTrue(lostPacketsWebService.markAsFound(foundPackets));
    }

    @Test
    public void testRemoveFromSystem(){
        Collection<String> packetToRemove = Arrays.asList("packetId1", "packetId2");

        server.expect(requestTo(getWithServerPath("lost-packets/remove-from-system")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0]").value("packetId1"))
                .andExpect(jsonPath("$.[1]").value("packetId2"))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON_UTF8));

        assertTrue(lostPacketsWebService.removeFromSystem(packetToRemove));
    }
}
