package be.rdhaese.packetdelivery.standalone.service.Impl;

import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.standalone.service.AbstractService;
import be.rdhaese.packetdelivery.standalone.service.LostPacketsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created on 15/01/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class LostPacketsServiceImpl extends AbstractService implements LostPacketsService{


    @Override
    public Collection<PacketDTO> getLostPackets() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PacketDTO[]> response = restTemplate.getForEntity(getUris().getLostPacketsPath(), PacketDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public void markAsFound(Collection<String> foundPackets) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(getUris().getMarkLostPacketsAsFoundPath(), foundPackets, Boolean.class);
    }

    @Override
    public void removeFromSystem(Collection<String> removedPackets) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(getUris().getRemoveLostPacketsFromSystemPath(), removedPackets, Boolean.class);
    }
}
