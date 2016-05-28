package be.rdhaese.packetdelivery.standalone.service.interfaces;

import be.rdhaese.packetdelivery.dto.PacketInformationDTO;

/**
 *
 * @author Robin D'Haese
 */
public interface PacketInformationWebService {

    PacketInformationDTO getPacketInformation(String url);
}
