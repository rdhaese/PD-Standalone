package be.rdhaese.packetdelivery.standalone.service.Impl;

import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.standalone.service.AbstractService;
import be.rdhaese.packetdelivery.standalone.service.ProblematicPacketsService;
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
public class ProblematicPacketsServiceImpl extends AbstractService implements ProblematicPacketsService {


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
    public void reSend(String packetId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.postForEntity(getUris().getReSendProblematicPacketPath(), packetId, Boolean.class);
    }

    @Override
    public void returnToSender(String packetId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.postForEntity(getUris().getReturnToSenderProblematicPacketPath(), packetId, Boolean.class);
    }
}
