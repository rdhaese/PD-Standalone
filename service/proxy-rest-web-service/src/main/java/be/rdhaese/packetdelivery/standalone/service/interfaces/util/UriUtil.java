package be.rdhaese.packetdelivery.standalone.service.interfaces.util;

import be.rdhaese.packetdelivery.standalone.service.interfaces.properties.BackEndProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
public class UriUtil {

    @Autowired
    private BackEndProperties backEndProperties;

    public String getServerPath(){
        return format("http://%s:%s", backEndProperties.getIp(), backEndProperties.getPort());
    }

    private BackEndProperties.Uris getUris(){
        return backEndProperties.getUris();
    }

    public String getWithServerPath(String path){
        return format("%s/%s", getServerPath(), path);
    }

    public String getAllRegionsPath(){
        return getWithServerPath(getUris().getAllRegions());
    }

    public String getAddPacketPath(){
        return getWithServerPath(getUris().getAddPacket());
    }

    public String getAuthenticatePath(){
        return getWithServerPath(getUris().getAuthenticate());
    }

    public String getContactInformationPath(){
        return getWithServerPath(getUris().getContactInformation());
    }

    public String getSaveContactInformationPath(){
        return getWithServerPath(getUris().getSaveContactInformation());
    }

    public String getCompanyNamePath() {
        return getWithServerPath(getUris().getCompanyName());
    }

    public String getLostPacketsPath() {
        return getWithServerPath(getUris().getLostPackets());
    }

    public String getMarkLostPacketsAsFoundPath() {
        return getWithServerPath(getUris().getMarkLostPacketsAsFound());
    }

    public String getRemoveLostPacketsFromSystemPath() {
        return getWithServerPath(getUris().getRemoveLostPacketsFromSystem());
    }

    public String getProblematicPacketsPath() {
        return getWithServerPath(getUris().getProblematicPackets());
    }

    public String getProblematicPacketPath() {
        return  getWithServerPath(getUris().getProblematicPacket());
    }

    public String getReSendProblematicPacketPath() {
        return getWithServerPath(getUris().getReSendProblematicPacket());
    }

    public String getReturnToSenderProblematicPacketPath() {
        return getWithServerPath(getUris().getReturnToSenderProblematicPacket());
    }

    public String getProblematicPacketDeliveryAddressPath() {
        return getWithServerPath(getUris().getProblematicPacketDeliveryAddress());
    }

    public String getSaveProblematicPacketDeliveryAddressPath() {
        return getWithServerPath(getUris().getSaveProblematicPacketDeliveryAddress());
    }
}
