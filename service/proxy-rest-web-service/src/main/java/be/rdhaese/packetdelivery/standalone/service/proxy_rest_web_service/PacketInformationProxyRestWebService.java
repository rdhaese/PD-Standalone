package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;


import be.rdhaese.packetdelivery.dto.PacketInformationDTO;
import be.rdhaese.packetdelivery.standalone.service.interfaces.PacketInformationWebService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robin D'Haese
 */
@Service
public class PacketInformationProxyRestWebService extends AbstractService implements PacketInformationWebService {

    @Override
    public PacketInformationDTO getPacketInformation(String url) {
        try {
            return getRestTemplate().getForObject(url, PacketInformationDTO.class);
        } catch (Exception e){
            return null;
        }
    }
}
