package be.rdhaese.packetdelivery.standalone.service;

import be.rdhaese.packetdelivery.dto.PacketDTO;

import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ProblematicPacketsService {
    Collection<PacketDTO> getProblematicPackets();

    PacketDTO getProblematicPacket(String packetId);

    void reSend(String currentPacket);

    void returnToSender(String currentPacket);
}
