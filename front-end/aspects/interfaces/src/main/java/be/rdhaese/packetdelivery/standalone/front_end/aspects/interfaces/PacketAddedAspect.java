package be.rdhaese.packetdelivery.standalone.front_end.aspects.interfaces;

/**
 *
 * @author Robin D'Haese
 */
public interface PacketAddedAspect {
    void afterAddingPacket(String packetId) throws Exception;
}
