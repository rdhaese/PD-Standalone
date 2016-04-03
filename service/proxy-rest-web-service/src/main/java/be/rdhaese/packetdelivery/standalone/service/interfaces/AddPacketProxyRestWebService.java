package be.rdhaese.packetdelivery.standalone.service.interfaces;

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
        return getNewRestTemplate().postForObject(getUris().getAddPacketPath(), packetDTO, String.class);
    }
}
