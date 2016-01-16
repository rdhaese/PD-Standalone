package be.rdhaese.packetdelivery.standalone.service.Impl;

import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.standalone.service.AbstractService;
import be.rdhaese.packetdelivery.standalone.service.ProblematicPacketsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public class ProblematicPacketsServiceImpl extends AbstractService implements ProblematicPacketsService {


    @Override
    public Collection<PacketDTO> getProblematicPackets() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PacketDTO[]> response = restTemplate.getForEntity(getUris().getProblematicPacketsPath(), PacketDTO[].class);
        return Arrays.asList(response.getBody());
    }
}
