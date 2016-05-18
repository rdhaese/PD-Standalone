package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collection;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
public class ProblematicPacketsProxyRestWebServiceTest extends AbstractProxyRestWebServiceTest {

    @Autowired
    private ProblematicPacketsWebService problematicPacketsWebService;

    @Test
    public void testGetProblematicPackets() throws Exception {
        PacketDTO packetDto1 = new PacketDTO();
        packetDto1.setPacketId("packetId1");
        PacketDTO packetDto2 = new PacketDTO();
        packetDto2.setPacketId("packetId2");
        Collection<PacketDTO> packetDTOs = Arrays.asList(packetDto1, packetDto2);

        server.expect(requestTo(getWithServerPath("problematic-packets/all")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(packetDTOs), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(2, problematicPacketsWebService.getProblematicPackets().size());
    }

    @Test
    public void testGetProblematicPacketForPacketId() throws Exception {
        PacketDTO packetDto1 = new PacketDTO();
        packetDto1.setPacketId("packetId1");

        server.expect(requestTo(getWithServerPath("problematic-packets/for-id/packetId1")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(packetDto1), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(packetDto1, problematicPacketsWebService.getProblematicPacket("packetId1"));
    }

    @Test
    public void testReSend(){
        server.expect(requestTo(getWithServerPath("problematic-packets/re-send")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string("packetId"))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON_UTF8));

        assertTrue(problematicPacketsWebService.reSend("packetId"));
    }

    @Test
    public void testReturnToSender(){
        RegionDTO regionDTO = new RegionDTO("nl", "fr", "de", "en", "CODE");

        server.expect(requestTo(getWithServerPath("problematic-packets/return-to-sender/packetId")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(jsonPath("$.code").value("CODE"))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON_UTF8));

        assertTrue(problematicPacketsWebService.returnToSender("packetId", regionDTO));
    }

    @Test
    public void testGetDeliveryAddressForPacketId() throws Exception {
        DeliveryAddressDTO deliveryAddressDTO = new DeliveryAddressDTO();
        deliveryAddressDTO.setPacketId("packetId");

        server.expect(requestTo(getWithServerPath("problematic-packets/delivery-address/packetId")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(deliveryAddressDTO), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(deliveryAddressDTO, problematicPacketsWebService.getDeliveryAddress("packetId"));

    }

    @Test
    public void testSaveDeliveryAddress(){
        DeliveryAddressDTO deliveryAddressDto = new DeliveryAddressDTO();
        deliveryAddressDto.setPacketId("packetId");

        server.expect(requestTo(getWithServerPath("problematic-packets/save-delivery-address")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.packetId").value("packetId"))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON_UTF8));

        assertTrue(problematicPacketsWebService.saveDeliveryAddress(deliveryAddressDto));
    }
}
