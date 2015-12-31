package be.rdhaese.packetdelivery.standalone.service.Impl;

import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import be.rdhaese.packetdelivery.standalone.service.AddPacketService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AddPacketServiceImpl extends AbstractService implements AddPacketService {

    public Collection<RegionDTO> getRegions(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RegionDTO[]> response = restTemplate.getForEntity(getUris().getAllRegionsPath(), RegionDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public String addPacket(PacketDTO packetDTO){
        packetDTO.setPacketStatus("NORMAL");
        packetDTO.setStatusChangedOn(new Date());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(getUris().getAddPacketPath(), packetDTO, String.class);
    }
}
