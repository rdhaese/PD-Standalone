package be.rdhaese.packetdelivery.standalone.service.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
@ConfigurationProperties(locations = "classpath:back-end.properties")
public class BackEndProperties {
    private String ip;
    private String port;
    private Uris uris;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Uris getUris() {
        return uris;
    }

    public void setUris(Uris uris) {
        this.uris = uris;
    }

    public static class Uris{
        private String allRegions;
        private String addPacket;
        private String authenticate;
        private String contactInformation;
        private String saveContactInformation;
        private String companyName;

        public String getAllRegions() {
            return allRegions;
        }

        public void setAllRegions(String allRegions) {
            this.allRegions = allRegions;
        }

        public String getAddPacket() {
            return addPacket;
        }

        public void setAddPacket(String addPacket) {
            this.addPacket = addPacket;
        }

        public String getAuthenticate() {
            return authenticate;
        }

        public void setAuthenticate(String authenticate) {
            this.authenticate = authenticate;
        }

        public String getContactInformation() {
            return contactInformation;
        }

        public void setContactInformation(String contactInformation) {
            this.contactInformation = contactInformation;
        }

        public String getSaveContactInformation() {
            return saveContactInformation;
        }

        public void setSaveContactInformation(String saveContactInformation) {
            this.saveContactInformation = saveContactInformation;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }
}
