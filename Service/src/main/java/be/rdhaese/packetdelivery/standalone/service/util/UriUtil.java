package be.rdhaese.packetdelivery.standalone.service.util;

import be.rdhaese.packetdelivery.standalone.service.properties.BackEndProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;

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

    private BackEndProperties.Uris getUris(){
        return backEndProperties.getUris();
    }
}
