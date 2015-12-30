package be.rdhaese.packetdelivery.standalone.service;


import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;

import java.util.Collection;

/**
 * Created on 24/12/2015.
 *
 * @author Robin D'Haese
 */
public interface AddPacketService {
    Collection<RegionDTO> getRegions();

    String addPacket(PacketDTO packetDTO);
}
