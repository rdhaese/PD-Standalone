package be.rdhaese.packetdelivery.standalone.service.interfaces;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class ProblematicPacketsProxyRestWebService extends AbstractService implements ProblematicPacketsWebService {


    @Override
    public Collection<PacketDTO> getProblematicPackets() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PacketDTO[]> response = restTemplate.getForEntity(getUris().getProblematicPacketsPath(), PacketDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public PacketDTO getProblematicPacket(String packetId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PacketDTO> response = restTemplate.getForEntity(getUris().getProblematicPacketPath(), PacketDTO.class, packetId);
        return response.getBody();
    }

    @Override
    public Boolean reSend(String packetId) {
        return getNewRestTemplate().postForEntity(getUris().getReSendProblematicPacketPath(), packetId, Boolean.class).getBody();
    }

    @Override
    public Boolean returnToSender(String packetId) {
        return getNewRestTemplate().postForEntity(getUris().getReturnToSenderProblematicPacketPath(), packetId, Boolean.class).getBody();
    }

    @Override
    public DeliveryAddressDTO getDeliveryAddress(String packetId) {
        return getNewRestTemplate().getForEntity(getUris().getProblematicPacketDeliveryAddressPath(), DeliveryAddressDTO.class, packetId).getBody();
    }

    @Override
    public Boolean saveDeliveryAddress(DeliveryAddressDTO deliveryAddressDTO) {
        return getNewRestTemplate().postForObject(getUris().getSaveProblematicPacketDeliveryAddressPath(), deliveryAddressDTO, Boolean.class);
    }
}
