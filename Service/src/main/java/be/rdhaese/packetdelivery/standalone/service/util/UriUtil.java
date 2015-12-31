package be.rdhaese.packetdelivery.standalone.service.util;

import be.rdhaese.packetdelivery.standalone.service.properties.BackEndProperties;
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

    public String getAllRegionsPath(){
        return format("%s/%s", getServerPath(), getUris().getAllRegions());
    }

    public String getAddPacketPath(){
        return format("%s/%s", getServerPath(), getUris().getAddPacket());
    }

    public String getAuthenticatePath(){
        return format("%s/%s", getServerPath(), getUris().getAuthenticate());
    }

    public String getContactInformationPath(){
        return format("%s/%s", getServerPath(), getUris().getContactInformation());
    }

    public String getSaveContactInformationPath(){
        return format("%s/%s", getServerPath(), getUris().getSaveContactInformation());
    }

    private BackEndProperties.Uris getUris(){
        return backEndProperties.getUris();
    }
}
