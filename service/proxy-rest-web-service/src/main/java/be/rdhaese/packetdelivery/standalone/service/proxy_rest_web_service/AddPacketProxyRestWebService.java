package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AddPacketWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.stereotype.Service;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AddPacketProxyRestWebService extends AbstractService implements AddPacketWebService {

    public String addPacket(PacketDTO packetDTO){
        return getRestTemplate().postForObject(getUris().getAddPacketPath(), packetDTO, String.class);
    }
}
