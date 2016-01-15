package be.rdhaese.packetdelivery.standalone.service;

import be.rdhaese.packetdelivery.dto.PacketDTO;

import java.util.Collection;
import java.util.List;

/**
 * Created on 15/01/2016.
 *
 * @author Robin D'Haese
 */
public interface LostPacketsService {
    Collection<PacketDTO> getLostPackets();

    void markAsFound(Collection<String> foundPackets);

    void removeFromSystem(Collection<String> removedPackets);
}
