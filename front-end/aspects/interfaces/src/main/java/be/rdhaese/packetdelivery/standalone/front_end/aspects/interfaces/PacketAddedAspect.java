package be.rdhaese.packetdelivery.standalone.front_end.aspects.interfaces;

/**
 * Created on 2/01/2016.
 *
 * @author Robin D'Haese
 */
public interface PacketAddedAspect {
    void afterAddingPacket(String packetId) throws Exception;
}
