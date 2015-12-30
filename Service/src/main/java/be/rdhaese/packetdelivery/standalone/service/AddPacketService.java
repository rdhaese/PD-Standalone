package be.rdhaese.packetdelivery.standalone.service;

import be.rdhaese.project.dto.PacketDTO;
import be.rdhaese.project.dto.RegionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * Created on 24/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AddPacketService {

    private static final String ADD_PACKET_URI = "http://localhost:8080/packet/add";
    private static final String GET_REGIONS_URI = "http://localhost:8080/regions/all";

    public Collection<RegionDTO> getRegions(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RegionDTO[]> response = restTemplate.getForEntity(GET_REGIONS_URI, RegionDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public String addPacket(PacketDTO packetDTO){
        packetDTO.setPacketStatus("NORMAL");
        packetDTO.setStatusChangedOn(new Date());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(ADD_PACKET_URI, packetDTO, String.class);
    }

}
