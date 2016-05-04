package be.rdhaese.packetdelivery.standalone.service.interfaces;

import be.rdhaese.packetdelivery.dto.PacketInformationDTO;

/**
 * Created on 30/04/2016.
 *
 * @author Robin D'Haese
 */
public interface PacketInformationWebService {

    PacketInformationDTO getPacketInformation(String url);
}
