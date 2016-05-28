package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Robin D'Haese
 */
@Service
public class ProblematicPacketsProxyRestWebService extends AbstractService implements ProblematicPacketsWebService {

    @Override
    public Collection<PacketDTO> getProblematicPackets() {
        ResponseEntity<PacketDTO[]> response = getRestTemplate().getForEntity(getBackEndProperties().getUris().getProblematicPackets(), PacketDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public PacketDTO getProblematicPacket(String packetId) {
        ResponseEntity<PacketDTO> response = getRestTemplate().getForEntity(getBackEndProperties().getUris().getProblematicPacket(), PacketDTO.class, packetId);
        return response.getBody();
    }

    @Override
    public Boolean reSend(String packetId) {
        return getRestTemplate().postForEntity(getBackEndProperties().getUris().getReSendProblematicPacket(), packetId, Boolean.class).getBody();
    }

    @Override
    public Boolean returnToSender(String packetId, RegionDTO region) {
        return getRestTemplate().postForEntity(getBackEndProperties().getUris().getReturnToSenderProblematicPacket(), region, Boolean.class, packetId).getBody();
    }

    @Override
    public DeliveryAddressDTO getDeliveryAddress(String packetId) {
        return getRestTemplate().getForEntity(getBackEndProperties().getUris().getProblematicPacketDeliveryAddress(), DeliveryAddressDTO.class, packetId).getBody();
    }

    @Override
    public Boolean saveDeliveryAddress(DeliveryAddressDTO deliveryAddressDTO) {
        return getRestTemplate().postForObject(getBackEndProperties().getUris().getSaveProblematicPacketDeliveryAddress(), deliveryAddressDTO, Boolean.class);
    }
}
