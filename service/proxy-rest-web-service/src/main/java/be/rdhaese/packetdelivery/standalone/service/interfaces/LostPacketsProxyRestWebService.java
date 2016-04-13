package be.rdhaese.packetdelivery.standalone.service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.LostPacketsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created on 15/01/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class LostPacketsProxyRestWebService extends AbstractService implements LostPacketsWebService {


    @Override
    public Collection<PacketDTO> getLostPackets() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PacketDTO[]> response = restTemplate.getForEntity(getUris().getLostPacketsPath(), PacketDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Boolean markAsFound(Collection<String> foundPackets) {
        return getNewRestTemplate().postForEntity(getUris().getMarkLostPacketsAsFoundPath(), foundPackets, Boolean.class).getBody();
    }

    @Override
    public Boolean removeFromSystem(Collection<String> removedPackets) {
        return getNewRestTemplate().postForEntity(getUris().getRemoveLostPacketsFromSystemPath(), removedPackets, Boolean.class).getBody();
    }
}
