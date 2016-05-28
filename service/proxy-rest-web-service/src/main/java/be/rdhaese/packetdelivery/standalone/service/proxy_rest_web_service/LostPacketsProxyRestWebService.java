package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Robin D'Haese
 */
@Service
public class LostPacketsProxyRestWebService extends AbstractService implements LostPacketsWebService {


    @Override
    public Collection<PacketDTO> getLostPackets() {
        ResponseEntity<PacketDTO[]> response = getRestTemplate().getForEntity(getBackEndProperties().getUris().getLostPackets(), PacketDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Boolean markAsFound(Collection<String> foundPackets) {
        return getRestTemplate().postForEntity(getBackEndProperties().getUris().getMarkLostPacketsAsFound(), foundPackets, Boolean.class).getBody();
    }

    @Override
    public Boolean removeFromSystem(Collection<String> removedPackets) {
        return getRestTemplate().postForEntity(getBackEndProperties().getUris().getRemoveLostPacketsFromSystem(), removedPackets, Boolean.class).getBody();
    }
}
