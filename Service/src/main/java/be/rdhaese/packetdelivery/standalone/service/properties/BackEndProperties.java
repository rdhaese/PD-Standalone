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
        private String lostPackets;
        private String markLostPacketsAsFound;
        private String removeLostPacketsFromSystem;
        private String problematicPackets;

        public String getLostPackets() {
            return lostPackets;
        }

        public void setLostPackets(String lostPackets) {
            this.lostPackets = lostPackets;
        }

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

        public String getMarkLostPacketsAsFound() {
            return markLostPacketsAsFound;
        }

        public void setMarkLostPacketsAsFound(String markLostPacketsAsFound) {
            this.markLostPacketsAsFound = markLostPacketsAsFound;
        }

        public String getRemoveLostPacketsFromSystem() {
            return removeLostPacketsFromSystem;
        }

        public void setRemoveLostPacketsFromSystem(String removeLostPacketsFromSystem) {
            this.removeLostPacketsFromSystem = removeLostPacketsFromSystem;
        }

        public String getProblematicPackets() {
            return problematicPackets;
        }

        public void setProblematicPackets(String problematicPackets) {
            this.problematicPackets = problematicPackets;
        }
    }
}
