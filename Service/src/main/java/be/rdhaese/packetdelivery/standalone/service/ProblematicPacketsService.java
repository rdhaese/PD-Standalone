package be.rdhaese.packetdelivery.standalone.service;

import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;

import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ProblematicPacketsService {
    Collection<PacketDTO> getProblematicPackets();

    PacketDTO getProblematicPacket(String packetId);

    void reSend(String packetId);

    void returnToSender(String packetId);

    Collection<RegionDTO> getRegions();

    DeliveryAddressDTO getDeliveryAddress(String packetId);

    void saveDeliveryAddress(DeliveryAddressDTO deliveryAddressDTO);
}
